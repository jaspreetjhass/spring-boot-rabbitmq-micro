package com.jp.springboot.rabbitmq.micro.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("annotation")
@Configuration
public class RabbitMqAnnotationConfig {

	@Autowired
	private RabbitMqProperties rabbitMqProperties;

	@Bean
	public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
			final CachingConnectionFactory cachingConnectionFactory) {
		final SimpleRabbitListenerContainerFactory simpleMessageListenerContainer = new SimpleRabbitListenerContainerFactory();
		simpleMessageListenerContainer.setConnectionFactory(cachingConnectionFactory);
		simpleMessageListenerContainer.setConcurrentConsumers(rabbitMqProperties.getConcurrentConsumers());
		simpleMessageListenerContainer.setMaxConcurrentConsumers(rabbitMqProperties.getMaxConcurrentConsumers());
		return simpleMessageListenerContainer;
	}

}
