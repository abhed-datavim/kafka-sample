package com.datavim.producer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.datavim.domain.Driver;

@Service
public class KafkaConsumer {
	
	/**
	 * Recieves the Driver object from Reply Queue
	 * @param driver
	 */
	@KafkaListener(topics = "${kafka.topic.reply-topic}", group = "${kafka.group-id}", containerFactory = "kafkaListenerContainerFactory")
	public void consumeJson(Driver driver) {
		System.out.println(driver);
	}
}