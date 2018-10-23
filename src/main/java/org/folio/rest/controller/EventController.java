package org.folio.rest.controller;

import java.io.IOException;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;

import org.folio.rest.jms.Event;
import org.folio.rest.jms.EventProducer;
import org.folio.rest.tenant.annotation.TenantHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/")
public class EventController {

  private static final Logger logger = LoggerFactory.getLogger(EventController.class);

  @Autowired
  private EventProducer eventProducer;

  // @formatter:off
  @RequestMapping("**")
  public JsonNode postHandleEvents(
    @TenantHeader String tenant,
    @RequestBody JsonNode body,
    @RequestHeader HttpHeaders headers,
    HttpServletRequest request
  ) throws JMSException, IOException {
  // @formatter:on
    String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
    logger.info("Tenant: {}", tenant);
    logger.info("Request path: {}", path);
    logger.info("Request headers: {}", headers);
    logger.info("Request body: {}", body);
    eventProducer.send(new Event(tenant, path, body, headers));
    return body;
  }

}