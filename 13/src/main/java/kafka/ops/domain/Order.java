package kafka.ops.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    private String uuid;

    private String itemTitle;

    private BigDecimal price;
}
