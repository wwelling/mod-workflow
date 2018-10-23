package org.folio.rest.jms;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventProducer {

  private final static Logger logger = LoggerFactory.getLogger(EventConsumer.class);

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

  public void send(Event event) throws JMSException {
    logger.info("Send [" + this.queue.getQueueName() + "]: " + event.getTenant() + " - " + event.getBody());
    try {
      this.jmsMessagingTemplate.convertAndSend(this.queue, mapper.writeValueAsString(event));
    } catch (MessagingException | JsonProcessingException e) {
      e.printStackTrace();
    }
  }

}