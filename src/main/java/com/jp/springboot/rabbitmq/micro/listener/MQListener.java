package com.jp.springboot.rabbitmq.micro.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@ConditionalOnBean(value = SimpleRabbitListenerContainerFactory.class)
@Slf4j
@Component
public class MQListener {

	@RabbitListener(queues = "${rabbitmq.micro.prop.queue-name}", containerFactory = "simpleRabbitListenerContainerFactory")
	public void receiveMessage(final Message message) {
		log.info("Message received : {}", new String(message.getBody()));
	}
}
