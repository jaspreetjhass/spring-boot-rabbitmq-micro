package com.jp.springboot.rabbitmq.micro.rest.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jp.springboot.rabbitmq.micro.config.RabbitMqProperties;

@Component
public class PushCommandRunner implements CommandLineRunner {

	@Autowired
	private RabbitMqProperties rabbitMqProperties;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void run(final String... args) throws Exception {
		final List<String> messageList = init();
		messageList.forEach(message -> rabbitTemplate.convertAndSend(rabbitMqProperties.getExchange(),
				rabbitMqProperties.getRoutingKey(), message));
	}

	private List<String> init() {
		final List<String> messageList = new ArrayList<String>();
		IntStream.range(0, 10).forEach(n -> messageList.add("message " + n));
		return messageList;
	}
}
