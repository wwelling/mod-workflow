package org.folio.rest.controller;

import java.io.IOException;
import java.util.Optional;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;

import org.folio.rest.jms.Event;
import org.folio.rest.jms.EventProducer;
import org.folio.rest.model.Trigger;
import org.folio.rest.model.repo.TriggerRepo;
import org.folio.rest.tenant.annotation.TenantHeader;
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

  private static final String FILTER_PATH_PATTERN_PREFIX = "/events";

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
  ) throws JMSException, IOException {
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
      String triggerName = trigger.get().getName();
      String triggerDescription = trigger.get().getDescription();
      logger.debug("Publishing event: {}: {}", triggerName, triggerDescription);
      eventProducer.send(new Event(triggerName, triggerDescription, tenant, path, method, body, headers));
    }
    return body;
  }

  private Optional<Trigger> checkTrigger(HttpMethod method, String path) {
    Optional<Trigger> trigger = Optional.empty();
    for (Trigger currentTrigger : triggerRepo.findByMethod(method)) {
      String pathPattern = FILTER_PATH_PATTERN_PREFIX + currentTrigger.getPathPattern();
      if (pathMatcher.match(pathPattern, path)) {
        trigger = Optional.of(currentTrigger);
        break;
      }
    }
    return trigger;
  }

}
