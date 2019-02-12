package com.example.kafkastarter;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Properties;

@SpringBootApplication
@EnableKafka
public class KafkaStarterApplication implements CommandLineRunner {


	@Autowired
	KafkaTemplate kafkaTemplate;

	public static void main(String[] args) {
		SpringApplication.run(KafkaStarterApplication.class, args);
	}


	@Bean
	public ProducerFactory getKafkaProducer(){
		JsonDeserializer x = new JsonDeserializer();
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:2181");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		return new DefaultKafkaProducerFactory(properties);

	}

	@Bean
	public KafkaTemplate getKafkaTemplate(ProducerFactory producerFactory){
		KafkaTemplate kafkaTemplate = new KafkaTemplate(producerFactory);
		return kafkaTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		ProducerRecord producerRecord = new ProducerRecord("test", "key1", "value1");

		System.out.println("sending request");
		kafkaTemplate.send(producerRecord);

	}
}
