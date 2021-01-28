package org.folio.rest.workflow.model.converter;

import javax.persistence.Converter;

import org.folio.rest.workflow.dto.Request;

import com.fasterxml.jackson.core.type.TypeReference;

@Converter
public class RequestConverter extends AbstractConverter<Request> {

  @Override
  public TypeReference<Request> getTypeReference() {
    // @formatter:off
    return new TypeReference<Request>() {};
    // @formatter:on
  }

}
