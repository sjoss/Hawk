#!/bin/bash
kafka-console-producer.sh --broker-list 0.0.0.0:9092 --topic tweets < /data/tweets-v2.json