package com.nuvalence.jcsm.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StatisticsApplication {
	private static final Logger log = LoggerFactory.getLogger(StatisticsApplication.class);

	public static void main(String[] args) {
		log.info("Statics application started");
		SpringApplication.run(StatisticsApplication.class, args);
	}

}
