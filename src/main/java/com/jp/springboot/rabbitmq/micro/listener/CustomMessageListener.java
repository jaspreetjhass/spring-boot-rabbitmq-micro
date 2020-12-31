package com.jp.springboot.rabbitmq.micro.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@ConditionalOnBean(value = SimpleMessageListenerContainer.class)
@Slf4j
@Component
public class CustomMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		log.info("message received : {}", new String(message.getBody()));
	}
}
