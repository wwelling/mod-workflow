package org.folio.rest.workflow.model.converter;

import java.util.List;

import javax.persistence.Converter;

import org.folio.rest.workflow.dto.Comparison;

import com.fasterxml.jackson.core.type.TypeReference;

@Converter
public class ComparisonListConverter extends AbstractConverter<List<Comparison>> {

  @Override
  public TypeReference<List<Comparison>> getTypeReference() {
    // @formatter:off
    return new TypeReference<List<Comparison>>() {};
    // @formatter:on
  }

}
