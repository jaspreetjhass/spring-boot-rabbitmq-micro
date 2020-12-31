package com.jp.springboot.rabbitmq.micro.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties("rabbitmq.micro.prop")
@Data
@NoArgsConstructor
public class RabbitMqProperties {

	private String queueName;
	private String exchange;
	private String routingKey;
	private Integer concurrentConsumers;
	private Integer maxConcurrentConsumers;

	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(directExchange()).with(routingKey);
	}

}
