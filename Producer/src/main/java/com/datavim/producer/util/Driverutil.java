package com.datavim.producer.util;


import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import com.datavim.domain.Driver;

/**
 * 
 * @author abhed
 *
 */
public class Driverutil {
	
	/**
	 * Add 10 Drivers to Redis
	 * @param REDIS_URI
	 */
	public static void addDrivers(String REDIS_URI) {
		Config config = new Config();
		config.useSingleServer().setAddress(REDIS_URI);
		RedissonClient redisson = Redisson.create(config);
		
		List<Driver> list = redisson.getList("driverList");
		for (int i = 1; i <= 10; i++) {
			
			String chars = "0123456789";
			String str = new Random().ints(4, 0, chars.length())
			                         .mapToObj(j -> "" + chars.charAt(j))
			                         .collect(Collectors.joining());
			String contact = "+1-541-754-" + str;
			
			list.add(new Driver("Driver-" + i, contact));
		}
	}
	
	/**
	 * Remove all available drivers from Redis
	 * @param REDIS_URI
	 */
	public static void removeallDrivers(String REDIS_URI) {
		Config config = new Config();
		config.useSingleServer().setAddress(REDIS_URI);
		RedissonClient redisson = Redisson.create(config);
		
		//Get List of available drivers from Redis and removes all avialble drivers
		List<Driver> list = redisson.getList("driverList");
		list.removeAll(list);
	}
}
