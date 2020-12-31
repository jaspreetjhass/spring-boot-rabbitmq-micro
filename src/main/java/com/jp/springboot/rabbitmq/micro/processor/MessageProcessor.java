package com.jp.springboot.rabbitmq.micro.processor;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageProcessor {

	public void receiveMessage(final String messagePayload) {
		log.info("message received : {}", messagePayload);
	}

}
