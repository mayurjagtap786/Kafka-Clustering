package com.techexamples;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class KafkaProducerApplicationTests {

    @Container
    KafkaContainer container = new KafkaContainer(DockerImageName.parse("confluentic/cp-kafka:latest"));

    @DynamicPropertySource
    public void initKafkaProperties(DynamicPropertyRegistry registry){
        registry.add("spring.kafka.bootstrap.servers",container::getBootstrapServers);
    }

}
