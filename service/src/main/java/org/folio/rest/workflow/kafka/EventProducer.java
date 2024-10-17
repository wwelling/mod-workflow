package org.folio.rest.workflow.kafka;

import org.folio.spring.messaging.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {

  private static final Logger logger = LoggerFactory.getLogger(EventProducer.class);

  private KafkaTemplate<String, Event> eventTemplate;

  @Autowired
  public EventProducer(KafkaTemplate<String, Event> eventTemplate) {
    this.eventTemplate = eventTemplate;
  }

  public void send(Event event) {
    // CAUTION: send not implemented
    logger.info("Send []: {}, {}", event.getMethod(), event.getPath());
    this.eventTemplate.send("workflow.events", event);
  }

}
