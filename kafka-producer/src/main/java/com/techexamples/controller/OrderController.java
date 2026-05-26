package com.techexamples.controller;

import com.techexamples.dto.Order;
import com.techexamples.service.KafkaProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class OrderController {

    KafkaProducer kafkaProducer;
    OrderController(KafkaProducer kafkaProducer){
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/produce")
    public ResponseEntity<String> sendOrderToKafka(@RequestBody Order order){
            kafkaProducer.sendOrderMessage(order);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order Created Sucessfully");
    }
}
