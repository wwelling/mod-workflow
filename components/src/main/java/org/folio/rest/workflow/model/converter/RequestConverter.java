package org.folio.rest.workflow.model.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.Converter;
import org.folio.rest.workflow.dto.Request;

@Converter
public class RequestConverter extends AbstractConverter<Request> {

  @Override
  public TypeReference<Request> getTypeReference() {
    return new TypeReference<Request>() {};
  }

}
