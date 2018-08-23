package com.datavim.consumer.listener;

import java.util.List;
import java.util.Random;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.datavim.domain.Customer;
import com.datavim.domain.Driver;

/**
 * 
 * @author abhed
 *
 */
@Service
public class KafkaConsumer {

	@Autowired
	private KafkaTemplate<String, Driver> kafkaTemplate;
	
	@Value("${kafka.topic.reply-topic}")
	String REPLY_TOPIC;
	
	@Value("${redis.uri}")
	String REDIS_URI;

	/**
	 * Consumes Customer object
	 * then check if there are any drivers available,
	 * if available, selects a driver randomly and 
	 * send driver object as a response.
	 * if no drivers are availble, 
	 * send an error saying No more drivers available
	 * @param customer
	 */
	@KafkaListener(topics = "${kafka.topic.request-topic}", group = "${kafka.group-id}", containerFactory = "userKafkaListenerFactory")
	public void consumeJson(Customer customer) {
		
		System.out.println("Consumed Customer Object: " + customer);
		
		Config config = new Config();
		config.useSingleServer().setAddress(REDIS_URI);

		RedissonClient redisson = Redisson.create(config);
		
		//Get List of available drivers from Redis 
		List<Driver> list = redisson.getList("driverList");
		
		//If no drivers available, send error
		if (list.size() == 0) {
			
			Driver nodrivererror =new Driver();
			nodrivererror.setAdditionalProperty("error", "No more drivers available!");	
			kafkaTemplate.send(REPLY_TOPIC, nodrivererror);
		
		}else {
			
			//Get Random Driver object 
			Random random = new Random();
			int randomValue = random.nextInt(list.size());
			Driver driver = list.get(randomValue);
			
			//assign customer to selected driver
			driver.setCustomername(customer.getName());
			
			//Send back the driver object to Producer 
			kafkaTemplate.send(REPLY_TOPIC, driver);
			
			//Remove selected driver from the list of available driver
			list.remove(randomValue);
		}
	}
}
