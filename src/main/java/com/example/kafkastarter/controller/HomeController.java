package com.example.kafkastarter.controller;

import com.example.kafkastarter.CustomMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    KafkaTemplate kafkaTemplate;

    @PostMapping(path="/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String sendMessage(@RequestBody CustomMessage message){
        String topic = "test";
        ProducerRecord producerRecord = new ProducerRecord(topic, message.getKey(), message.getValue());

        System.out.println("sending request");
        kafkaTemplate.send(producerRecord);
        return "success" ;
    }
}
