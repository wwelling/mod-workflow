package org.folio.rest.jms;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventProducer {

  private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

  @Autowired
  private JmsMessagingTemplate jmsMessagingTemplate;

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private Queue queue;

  @PostConstruct
  private void init() {
    this.mapper.writerWithDefaultPrettyPrinter();
  }

  public void send(Event event) throws JMSException, IOException {
    String message = mapper.writeValueAsString(event);
    logger.info("Send [{}]: {}", this.queue.getQueueName(), message);
    this.jmsMessagingTemplate.convertAndSend(this.queue, message);
  }

}