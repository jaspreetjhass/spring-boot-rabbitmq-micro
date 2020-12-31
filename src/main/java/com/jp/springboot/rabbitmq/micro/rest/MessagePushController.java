package com.jp.springboot.rabbitmq.micro.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.springboot.rabbitmq.micro.constant.ApplicationConstant;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("rabbit-micro")
public class MessagePushController {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@ApiOperation(consumes = "text/plain", httpMethod = "POST", value = "push message to rabbitMq queue")
	@PostMapping("push/{exchange}/{routing-key}")
	public String pushMessage(@PathVariable("exchange") final String exchange,
			@PathVariable("routing-key") final String routingKey, @RequestBody final String messagePayload) {
		log.info("push request received");
		rabbitTemplate.convertAndSend(exchange, routingKey, messagePayload);
		log.info("message push to {} exchange with routing-key {}", exchange, routingKey);
		return ApplicationConstant.OK;
	}
}
