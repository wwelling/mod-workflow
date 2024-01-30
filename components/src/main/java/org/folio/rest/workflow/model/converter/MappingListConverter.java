package org.folio.rest.workflow.model.converter;

import java.util.List;

import jakarta.persistence.Converter;

import org.folio.rest.workflow.dto.Mapping;

import com.fasterxml.jackson.core.type.TypeReference;

@Converter
public class MappingListConverter extends AbstractConverter<List<Mapping>> {

  @Override
  public TypeReference<List<Mapping>> getTypeReference() {
    // @formatter:off
    return new TypeReference<List<Mapping>>() {};
    // @formatter:on
  }

}
