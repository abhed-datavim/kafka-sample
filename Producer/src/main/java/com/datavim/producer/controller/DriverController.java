package com.datavim.producer.controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.datavim.domain.Customer;
import com.datavim.producer.util.Driverutil;

/**
 * 
 * @author abhed
 *
 */
@RestController
public class DriverController {
	
    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    @Value("${kafka.topic.request-topic}")
	String REQUEST_TOPIC;
    
    @Value("${redis.uri}")
	String REDIS_URI;
    
    private final Logger log = LoggerFactory.getLogger(DriverController.class);
    
    @ResponseBody
	@PostMapping(value="/assigndriver",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String assigndriver(@RequestBody Customer customer) throws InterruptedException, ExecutionException {
    	log.debug("POST Request for assigndriver", kafkaTemplate);
        kafkaTemplate.send(REQUEST_TOPIC, customer);
        
        return "Published successfully";
    }
    
    /**
	 * GET /adddriver : Add 10 Drivers
	 * @return Status 200 upon successful execution
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@GetMapping(value="/adddriver")
	public String adddriver() throws InterruptedException, ExecutionException {
		Driverutil.addDrivers(REDIS_URI);
		return "Added 10 Drivers Successfully";
	}
	
	/**
	 * GET /removedriver : Removes all available drivers
	 * @return Status 200 upon successful execution
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@GetMapping(value="/removedriver")
	public String removedriver() throws InterruptedException, ExecutionException {
		Driverutil.removeallDrivers(REDIS_URI);
		return "Removed all Available Drivers";
	}
    
}
