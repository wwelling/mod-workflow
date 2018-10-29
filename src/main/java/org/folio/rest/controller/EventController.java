package org.folio.rest.controller;

import java.io.IOException;

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
    logger.info("Tenant: {}", tenant);
    logger.info("Request path: {}", path);
    logger.info("Request method: {}", method);
    logger.info("Request headers: {}", headers);
    if (isTriggered(tenant, path, method)) {
      logger.info("Request body: {}", body);
      eventProducer.send(new Event(tenant, path, method, body, headers));
    }
    return body;
  }

  private boolean isTriggered(String tenant, String path, HttpMethod method) {
    boolean match = false;
    for (Trigger trigger : triggerRepo.findByTenantAndMethod(tenant, method)) {
      match = pathMatcher.match(trigger.getPathPattern(), path);
      if (match) {
        break;
      }
    }
    return match;
  }

}
