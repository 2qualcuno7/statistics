package com.nuvalence.jcsm.statistics;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


@SpringBootApplication
public class StatisticsApplication {
	private static final Logger log = LoggerFactory.getLogger(StatisticsApplication.class);

	public static void main(String[] args) {
		log.info("Statics application started");

		String bootstrapServers = "kafka-poc.kafka.svc.cluster.local:9092";
		String topic = "first_topic";

		// create consumer configs
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(Arrays.asList(topic));

		log.info("Consumer created");

		while(true){
			ConsumerRecords<String, String> records =
					consumer.poll(Duration.ofMillis(10000));

			for (ConsumerRecord<String, String> record : records){
				log.info("Key: " + record.key() + ", Value: " + record.value());
				log.info("Partition: " + record.partition() + ", Offset:" + record.offset());
			}
		}

		//SpringApplication.run(StatisticsApplication.class, args);
	}

}
