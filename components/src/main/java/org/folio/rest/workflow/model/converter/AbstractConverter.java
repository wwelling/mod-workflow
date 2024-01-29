package org.folio.rest.workflow.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

public abstract class AbstractConverter<T> implements AttributeConverter<T, String> {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(T attribute) {
    try {
      return objectMapper.writeValueAsString(attribute);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public T convertToEntityAttribute(String dbData) {
    try {
      return objectMapper.readValue(dbData, getTypeReference());
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public abstract TypeReference<T> getTypeReference();

}
