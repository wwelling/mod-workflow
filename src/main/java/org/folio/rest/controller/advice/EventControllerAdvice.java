package org.folio.rest.controller.advice;

import java.io.IOException;

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

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IOException.class)
  public String handleIOException(IOException exception) {
    logger.debug(exception.getMessage(), exception);
    return exception.getMessage();
  }

}
