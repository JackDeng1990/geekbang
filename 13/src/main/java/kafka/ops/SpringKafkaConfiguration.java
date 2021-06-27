package kafka.ops;

import com.alibaba.fastjson.JSON;
import kafka.ops.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@Configuration
@Slf4j
public class SpringKafkaConfiguration {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(id = "orderGroup", topics = "order")
    public void listen(String message) {
        log.info("message in...{}", message);
        try {
            Order order = JSON.parseObject(
                    StringEscapeUtils.unescapeJava(message).replaceAll("(^\"|\"$)",""),
                    Order.class);
            log.info("order => {}", order);
        } catch (Exception e) {
            log.error("json parse error with value -> {}", message);
        }

    }

    @Scheduled(cron = "0/5 * * * * *")
    public void producer() {
        final Order order = new Order();
        order.setUuid(UUID.randomUUID().toString());
        order.setItemTitle("Random title");
        order.setPrice(BigDecimal.valueOf(Math.abs(new Random().nextFloat())));

        final String data = JSON.toJSONString(order);
        final ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("order", data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //TODO: retry
                throwable.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                log.debug("send result:  {}", stringStringSendResult.getRecordMetadata());
                final ProducerRecord<String, String> record = stringStringSendResult.getProducerRecord();
                log.info("key -> {}. val -> {}, partition -> {}", record.key(), record.value(), record.partition());
            }
        });
    }
}
