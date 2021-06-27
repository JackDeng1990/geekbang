极客时间Java训练营第十三周作业
=========================

1.（必做）搭建一个3节点Kafka集群，测试功能和性能；实现spring kafka下对kafka集群的操作，将代码提交到github。 

2.（选做）安装kafka-manager工具，监控kafka集群状态。 

3.（挑战☆）演练本课提及的各种生产者和消费者特性。 

4.（挑战☆☆☆）Kafka金融领域实战：在证券或者外汇、数字货币类金融核心交易系统里， 对于订单的处理，大概可以分为收单、定序、撮合、清算等步骤。其中我们一般可以用mq来 实现订单定序，然后将订单发送给撮合模块。

1）收单：请实现一个订单的rest接口，能够接收一个订单Order对象;

2）定序：将Order对象写入到kafka集群的order.usd2cny队列，要求数据有序并且不丢失;

3）撮合：模拟撮合程序（不需要实现撮合逻辑），从kafka获取order数据，并打印订单信息， 要求可重放, 顺序消费, 消息仅处理一次.

----

### 0. Zookeeper集群搭建
需要配置以下必需参数：
- 配置文件zoo.cfg中包含0-n的实例主机名端口信息，
    * server.0=HOST:PORT:PORT
    * server.n=HOST:PORT:PORT
- myid文件包含ID

### 1. Kafka集群搭建
需要配置以下必需参数：
* broker.id=ID
* zookeeper.connect=STRING
* advertised.listeners=STRING

以docker-compose方式启动：

配置文件[docker-compose.yml](./docker-compose.yml)，启动一个3zookeeper+3kafka的集群：
```shell
docker-compose up -d
```

### 2. kafka集群性能测试
创建topic，8分区，3副本：
```shell
./bin/kafka-topics.sh --bootstrap-server 10.0.0.33:19094,10.0.0.33:29094,10.0.0.33:39094 --create --topic perf-test --partitions 8 --replication-factor 3

./bin/kafka-producer-perf-test.sh --topic perf-test --num-records 100000 --record-size 1000 --throughput 2000 --producer-props bootstrap.servers=10.0.0.33:19094,10.0.0.33:29094,10.0.0.33:39094
```
```text
10002 records sent, 1999.6 records/sec (1.91 MB/sec), 6.0 ms avg latency, 290.0 ms max latency.
10006 records sent, 2000.4 records/sec (1.91 MB/sec), 1.6 ms avg latency, 17.0 ms max latency.
9985 records sent, 1997.0 records/sec (1.90 MB/sec), 1.6 ms avg latency, 15.0 ms max latency.
10017 records sent, 2003.4 records/sec (1.91 MB/sec), 1.4 ms avg latency, 11.0 ms max latency.
10004 records sent, 2000.4 records/sec (1.91 MB/sec), 1.3 ms avg latency, 17.0 ms max latency.
10000 records sent, 1999.6 records/sec (1.91 MB/sec), 1.2 ms avg latency, 12.0 ms max latency.
10000 records sent, 2000.0 records/sec (1.91 MB/sec), 1.3 ms avg latency, 23.0 ms max latency.
10004 records sent, 2000.4 records/sec (1.91 MB/sec), 1.2 ms avg latency, 13.0 ms max latency.
10001 records sent, 2000.2 records/sec (1.91 MB/sec), 1.1 ms avg latency, 11.0 ms max latency.
100000 records sent, 1999.720039 records/sec (1.91 MB/sec), 1.79 ms avg latency, 290.00 ms max latency, 1 ms 50th, 3 ms 95th, 9 ms 99th, 66 ms 99.9th.
```
在压力较低时，可以吞吐量可以保证，延时也保证在1-2ms内。接下来进行增加压力测试：
保持记录数量500000，记录大小为2000B，增加吞吐量，直到吞吐量和延时明显偏离预期。详细记录如下：

* throughput=50000
```text
./bin/kafka-producer-perf-test.sh --topic perf-test --num-records 500000 --record-size 2000 --throughput 50000 --producer-props bootstrap.servers=10.0.0.33:19094,10.0.0.33:29094,10.0.0.33:39094
76633 records sent, 15326.6 records/sec (29.23 MB/sec), 909.0 ms avg latency, 1117.0 ms max latency.
100488 records sent, 20097.6 records/sec (38.33 MB/sec), 819.8 ms avg latency, 885.0 ms max latency.
111248 records sent, 22249.6 records/sec (42.44 MB/sec), 743.6 ms avg latency, 888.0 ms max latency.
112432 records sent, 22486.4 records/sec (42.89 MB/sec), 721.7 ms avg latency, 779.0 ms max latency.
500000 records sent, 20032.051282 records/sec (38.21 MB/sec), 791.49 ms avg latency, 1117.00 ms max latency, 783 ms 50th, 1056 ms 95th, 1099 ms 99th, 1111 ms 99.9th.
```
延时很大，而且吞吐不到预期的一半，需要减小吞吐设定。

* throughput=10000
```text
./bin/kafka-producer-perf-test.sh --topic perf-test --num-records 500000 --record-size 2000 --throughput 10000 --producer-props bootstrap.servers=10.0.0.33:19094,10.0.0.33:29094,10.0.0.33:39094
49992 records sent, 9996.4 records/sec (19.07 MB/sec), 20.8 ms avg latency, 268.0 ms max latency.
50020 records sent, 10000.0 records/sec (19.07 MB/sec), 1.6 ms avg latency, 15.0 ms max latency.
50016 records sent, 10003.2 records/sec (19.08 MB/sec), 1.7 ms avg latency, 17.0 ms max latency.
49994 records sent, 9998.8 records/sec (19.07 MB/sec), 1.7 ms avg latency, 19.0 ms max latency.
50018 records sent, 10003.6 records/sec (19.08 MB/sec), 1.6 ms avg latency, 10.0 ms max latency.
50002 records sent, 10000.4 records/sec (19.07 MB/sec), 1.6 ms avg latency, 17.0 ms max latency.
50010 records sent, 9998.0 records/sec (19.07 MB/sec), 1.5 ms avg latency, 9.0 ms max latency.
50030 records sent, 10006.0 records/sec (19.08 MB/sec), 1.6 ms avg latency, 10.0 ms max latency.
50000 records sent, 10000.0 records/sec (19.07 MB/sec), 1.6 ms avg latency, 10.0 ms max latency.
500000 records sent, 9998.600196 records/sec (19.07 MB/sec), 3.53 ms avg latency, 268.00 ms max latency, 2 ms 50th, 3 ms 95th, 98 ms 99th, 191 ms 99.9th.
```
延时很小，而且吞吐量完全满足预期，需要增大吞吐量设定。

* throughput=20000
```text
./bin/kafka-producer-perf-test.sh --topic perf-test --num-records 500000 --record-size 2000 --throughput 20000 --producer-props bootstrap.servers=10.0.0.33:19094,10.0.0.33:29094,10.0.0.33:39094
76169 records sent, 15233.8 records/sec (29.06 MB/sec), 730.4 ms avg latency, 1112.0 ms max latency.
103656 records sent, 20731.2 records/sec (39.54 MB/sec), 800.5 ms avg latency, 925.0 ms max latency.
104360 records sent, 20872.0 records/sec (39.81 MB/sec), 782.2 ms avg latency, 842.0 ms max latency.
108360 records sent, 21672.0 records/sec (41.34 MB/sec), 569.3 ms avg latency, 773.0 ms max latency.
84544 records sent, 16784.6 records/sec (32.01 MB/sec), 456.1 ms avg latency, 1191.0 ms max latency.
500000 records sent, 19154.152620 records/sec (36.53 MB/sec), 677.59 ms avg latency, 1191.00 ms max latency, 759 ms 50th, 1076 ms 95th, 1176 ms 99th, 1189 ms 99.9th.
```
延时很大，而且吞吐不到预期的一半，需要减小吞吐设定。

* throughput=15000
```text
$ ./bin/kafka-producer-perf-test.sh --topic perf-test --num-records 500000 --record-size 2000 --throughput 15000 --producer-props bootstrap.servers=10.0.0.33:19094,10.0.0.33:29094,10.0.0.33:39094
74904 records sent, 14980.8 records/sec (28.57 MB/sec), 172.2 ms avg latency, 331.0 ms max latency.
75069 records sent, 15007.8 records/sec (28.63 MB/sec), 3.1 ms avg latency, 16.0 ms max latency.
75011 records sent, 15002.2 records/sec (28.61 MB/sec), 3.0 ms avg latency, 18.0 ms max latency.
74980 records sent, 14996.0 records/sec (28.60 MB/sec), 2.8 ms avg latency, 13.0 ms max latency.
75041 records sent, 15008.2 records/sec (28.63 MB/sec), 2.9 ms avg latency, 17.0 ms max latency.
74983 records sent, 14996.6 records/sec (28.60 MB/sec), 2.6 ms avg latency, 13.0 ms max latency.
500000 records sent, 14996.101014 records/sec (28.60 MB/sec), 28.25 ms avg latency, 331.00 ms max latency, 3 ms 50th, 210 ms 95th, 275 ms 99th, 327 ms 99.9th.
```

* throughput=17000
```text
./bin/kafka-producer-perf-test.sh --topic perf-test --num-records 500000 --record-size 2000 --throughput 17000 --producer-props bootstrap.servers=10.0.0.33:19094,10.0.0.33:29094,10.0.0.33:39094
77777 records sent, 15555.4 records/sec (29.67 MB/sec), 405.3 ms avg latency, 521.0 ms max latency.
92189 records sent, 18437.8 records/sec (35.17 MB/sec), 82.2 ms avg latency, 425.0 ms max latency.
85002 records sent, 17000.4 records/sec (32.43 MB/sec), 3.6 ms avg latency, 27.0 ms max latency.
84984 records sent, 16996.8 records/sec (32.42 MB/sec), 3.4 ms avg latency, 21.0 ms max latency.
84985 records sent, 16997.0 records/sec (32.42 MB/sec), 3.8 ms avg latency, 34.0 ms max latency.
500000 records sent, 16994.663676 records/sec (32.41 MB/sec), 81.24 ms avg latency, 521.00 ms max latency, 3 ms 50th, 457 ms 95th, 509 ms 99th, 518 ms 99.9th.
```

* throughput=18000
```text
./bin/kafka-producer-perf-test.sh --topic perf-test --num-records 500000 --record-size 2000 --throughput 18000 --producer-props bootstrap.servers=10.0.0.33:19094,10.0.0.33:29094,10.0.0.33:39094
77273 records sent, 15454.6 records/sec (29.48 MB/sec), 556.8 ms avg latency, 779.0 ms max latency.
102694 records sent, 20538.8 records/sec (39.17 MB/sec), 280.0 ms avg latency, 710.0 ms max latency.
90010 records sent, 18002.0 records/sec (34.34 MB/sec), 3.2 ms avg latency, 20.0 ms max latency.
90012 records sent, 18002.4 records/sec (34.34 MB/sec), 3.4 ms avg latency, 20.0 ms max latency.
90038 records sent, 18007.6 records/sec (34.35 MB/sec), 4.0 ms avg latency, 18.0 ms max latency.
500000 records sent, 17994.673577 records/sec (34.32 MB/sec), 146.05 ms avg latency, 779.00 ms max latency, 4 ms 50th, 674 ms 95th, 760 ms 99th, 776 ms 99.9th.
```
基本可以稳定在15000-18000之间。

**结论：在当前三节点的集群环境下，对于平均2000B大小的记录，可以处理的峰值吞吐量为18000记录/秒，等效数据传输34MB/S。**


### 3. 使用spring-kafka简单操作kafka读写

见 [SpringKafkaConfiguration.java](./src/main/java/kafka/ops/SpringKafkaConfiguration.java)