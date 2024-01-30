package org.folio.rest.workflow.model.converter;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Converter;

@Converter
public class JsonNodeConverter extends AbstractConverter<JsonNode> {

  @Override
  public TypeReference<JsonNode> getTypeReference() {
    // @formatter:off
    return new TypeReference<JsonNode>() {};
    // @formatter:on
  }

}
