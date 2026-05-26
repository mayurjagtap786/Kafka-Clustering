package com.techexamples.service;


import com.techexamples.dto.EmployeeDTO;
import com.techexamples.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {

    Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final Environment environment;


    KafkaProducer(KafkaTemplate<String,Object> kafkaTemplate,Environment environment){
        this.kafkaTemplate = kafkaTemplate;
        this.environment = environment;
    }


    public void sendOrderMessage(Order order){
        CompletableFuture<SendResult<String,Object>> completableFuture = kafkaTemplate.send("order-topic",2,null,order);

        completableFuture.whenComplete((result,ex)-> {
            if(ex ==null){
                    LOG.info("Message: {} is sent to partition: {} with offset: {} ",
                            order,result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset());
            }else{
                LOG.error("Message could not be send to Topic {}",ex.getMessage());
            }
        });
    }

    public void sendEmployeeMessage(EmployeeDTO employee){
        CompletableFuture<SendResult<String,Object>> completableFuture = kafkaTemplate.send("avro-employee", UUID.randomUUID().toString(),employee);

        completableFuture.whenComplete((result,ex)-> {
            if(ex ==null){
                LOG.info("Message: {} is sent to partition: {} with offset: {} ",
                        employee,result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            }else{
                LOG.error("Message could not be send to Topic {}",ex.getMessage());
            }
        });
    }
}
