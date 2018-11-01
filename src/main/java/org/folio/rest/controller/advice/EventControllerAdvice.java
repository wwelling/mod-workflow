package org.folio.rest.controller.advice;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.jms.JmsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EventControllerAdvice {

  private static final Logger logger = LoggerFactory.getLogger(EventControllerAdvice.class);

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(JMSException.class)
  public String handleJMSException(JmsException exception) {
    logger.debug(exception.getMessage(), exception);
    return exception.getMessage();
  }

}
