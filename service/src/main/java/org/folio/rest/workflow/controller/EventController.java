package org.folio.rest.workflow.controller;

import java.io.IOException;
import java.util.Optional;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;

import org.folio.rest.workflow.exception.EventPublishException;
import org.folio.rest.workflow.jms.EventProducer;
import org.folio.rest.workflow.jms.model.Event;
import org.folio.rest.workflow.model.Trigger;
import org.folio.rest.workflow.model.repo.TriggerRepo;
import org.folio.spring.tenant.annotation.TenantHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/events")
public class EventController {

  private static final Logger logger = LoggerFactory.getLogger(EventController.class);

  @Autowired
  private EventProducer eventProducer;

  @Autowired
  private TriggerRepo triggerRepo;

  @Autowired
  private PathMatcher pathMatcher;

  // @formatter:off
  @RequestMapping("/**")
  public JsonNode postHandleEvents(
    @TenantHeader String tenant,
    @RequestBody(required = false) JsonNode body,
    @RequestHeader HttpHeaders headers,
    HttpServletRequest request,
    HttpServletRequest response
  ) throws EventPublishException {
  // @formatter:on
    String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
    HttpMethod method = HttpMethod.valueOf(request.getMethod());
    logger.debug("Tenant: {}", tenant);
    logger.debug("Request path: {}", path);
    logger.debug("Request method: {}", method);
    logger.debug("Request headers: {}", headers);
    logger.debug("Request body: {}", body);
    Optional<Trigger> trigger = checkTrigger(method, path);
    if (trigger.isPresent()) {
      logger.debug("Publishing event: {}: {}", trigger.get().getName(), trigger.get().getDescription());
      try {
        // @formatter:off
        eventProducer.send(
          new Event(
            trigger.get().getId(),
            trigger.get().getPathPattern(),
            trigger.get().getMethod().toString(),
            tenant,
            path,
            headers.toSingleValueMap(),
            body
          )
        );
        // @formatter:on
      } catch (JMSException | IOException e) {
        throw new EventPublishException("Unable to publish event!", e);
      }
    }
    return body;
  }

  private Optional<Trigger> checkTrigger(HttpMethod method, String path) {
    return triggerRepo.findAll().stream().filter(t -> {
      return method.equals(t.getMethod()) && pathMatcher.match(t.getPathPattern(), path);
    }).findAny();
  }

}
