redis主从复制配置及验证
====

1. 启动三个redis实例, [docker-compose.yml](./docker-compose.yml)
```shell
docker-compose up -d
```
docker会启动三个实例，默认如下参数：
* redis1 172.17.0.1 63790
* redis2 172.17.0.1 63791
* redis3 172.17.0.1 63792

2. 配置主从

redis-cli@redis2
```shell
SLAVEOF 172.17.0.1 63790

INFO
```
信息如下
```text
...
# Replication
role:slave
master_host:172.17.0.1
master_port:63790
master_link_status:up
master_last_io_seconds_ago:8
master_sync_in_progress:0
slave_repl_offset:694
slave_priority:100
slave_read_only:1
connected_slaves:0
master_replid:c00414c2e26cc135ac15980e22574aa603045010
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:694
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:694
...
```

redis-cli@redis3
```shell
SLAVEOF 172.17.0.1 63790

INFO
```
信息如下：
```text
# Replication
role:slave
master_host:172.17.0.1
master_port:63790
master_link_status:up
master_last_io_seconds_ago:7
master_sync_in_progress:0
slave_repl_offset:70
slave_priority:100
slave_read_only:1
connected_slaves:0
master_replid:c00414c2e26cc135ac15980e22574aa603045010
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:70
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:71
repl_backlog_histlen:0
```

3. 验证设置

redis-cli@redis1
```shell
SET foo bar

INCR bar
```

redis-cli@redis2
```shell
GET foo
# "bar"
GET bar
# "1"
```

redis-cli@redis3
```shell
GET foo
# "bar"
GET bar
# "1"
```
同步已经完成设置

4. 从redis已经变成只读模式

redis-cli@redis2
```shell
SET foofoo barbar
# "READONLY You can't write against a read only replica."
```
