package org.folio.rest.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

  @Value("${event.queue.name}")
  private String eventQueueName;

  @Bean
  public Queue queue() {
    return new ActiveMQQueue(eventQueueName);
  }

}
