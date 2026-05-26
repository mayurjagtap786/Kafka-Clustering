package com.techexamples.service;


import com.techexamples.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);
    @Autowired
    private Environment environment;

    @KafkaListener(groupId = "order-grp-1",
            topicPartitions = { @TopicPartition(topic = "order-topic",partitions = {"2"} ) }
    )
    public void consumeOrders(Order order, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition){
        LOG.info("{} received message {} in partition {}", environment.getProperty("consumer.name"),order,partition);
    }
}