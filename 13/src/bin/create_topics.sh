#!/usr/bin/env bash

./bin/kafka-topics.sh --zookeeper localhost:21810 --create --topic order --partitions 4 --replication-factor 3