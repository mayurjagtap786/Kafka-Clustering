package com.techexamples.controller;

import com.techexamples.dto.EmployeeDTO;
import com.techexamples.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    KafkaProducer kafkaProducer;
    @PostMapping("/produce/employee")
    public ResponseEntity<String> sendEmployeeToKafka(@RequestBody EmployeeDTO employeeDTO){
        kafkaProducer.sendEmployeeMessage(employeeDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Employee message is sent");

    }

}
