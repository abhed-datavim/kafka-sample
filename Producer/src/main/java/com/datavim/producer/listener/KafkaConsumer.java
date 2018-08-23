package com.datavim.producer.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.datavim.domain.Customer;
import com.datavim.domain.Driver;

@Service
public class KafkaConsumer {
	
	/**
	 * Recieves the Driver object from Reply Queue
	 * @param driver
	 */
	@KafkaListener(topics = "${kafka.topic.reply-topic}", group = "${kafka.group-id}", containerFactory = "userKafkaListenerFactory")
	public void consumeJson(Driver driver) {
		System.out.println(driver);
	}
}
