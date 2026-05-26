package com.techexamples.service;

import com.techexamples.dto.EmployeeDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    Logger LOG = LoggerFactory.getLogger(EmployeeConsumer.class);
    @KafkaListener(topics = "avro-employee", groupId = "employee-grp-1")
    public void consumeEmployee(ConsumerRecord<String, EmployeeDTO> consumerRecord){

        LOG.info("avro message received key : {} and message : {}",consumerRecord.key(),consumerRecord.value());

    }

}
