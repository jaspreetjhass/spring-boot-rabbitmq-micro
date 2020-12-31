package com.jp.springboot.rabbitmq.micro.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jp.springboot.rabbitmq.micro.constant.ApplicationConstant;
import com.jp.springboot.rabbitmq.micro.listener.CustomMessageListener;
import com.jp.springboot.rabbitmq.micro.processor.MessageProcessor;

@Profile("bean")
@Configuration
public class RabbitMqConfig {

	@Autowired
	private RabbitMqProperties rabbitMqProperties;

	@Bean
	public MessageListenerAdapter messageListenerAdapter(final MessageProcessor messageProcessor) {
		return new MessageListenerAdapter(messageProcessor, ApplicationConstant.RECEIVE_MESSAGE);
	}

	@Bean
	public SimpleMessageListenerContainer simpleMessageListenerContainer(
			final CachingConnectionFactory cachingConnectionFactory,
			final MessageListenerAdapter messageListenerAdapter, final Queue queue) {
		final SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(
				cachingConnectionFactory);
		simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
		simpleMessageListenerContainer.addQueues(queue);
		simpleMessageListenerContainer.setConcurrentConsumers(rabbitMqProperties.getConcurrentConsumers());
		simpleMessageListenerContainer.setMaxConcurrentConsumers(rabbitMqProperties.getMaxConcurrentConsumers());
		return simpleMessageListenerContainer;
	}

	//@Bean
	public SimpleMessageListenerContainer simpleMessageListenerContainer2(
			final CachingConnectionFactory cachingConnectionFactory, final CustomMessageListener customMessageListener,
			final Queue queue) {
		final SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(
				cachingConnectionFactory);
		simpleMessageListenerContainer.setMessageListener(customMessageListener);
		simpleMessageListenerContainer.addQueues(queue);
		simpleMessageListenerContainer.setConcurrentConsumers(rabbitMqProperties.getConcurrentConsumers());
		simpleMessageListenerContainer.setMaxConcurrentConsumers(rabbitMqProperties.getMaxConcurrentConsumers());
		return simpleMessageListenerContainer;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final CachingConnectionFactory cachingConnectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
		return rabbitTemplate;
	}

}
