package com.bigc.pointtransbatchservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
@SpringBootApplication
@EnableBatchProcessing
@ConfigurationPropertiesScan
@Slf4j
public class PointTransBatchServiceApplication {

	public static void main(String[] args) {
		try {
			//MDC.put(Constant.CORRELATION_ID, GeneratorUtils.uuid)
			SpringApplication.run(PointTransBatchServiceApplication.class, args);
		} catch (Exception e) {
			log.error("error while start application with: {}", e.getMessage(), e);
		}
	}

}
