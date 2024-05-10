package org.folio.rest.workflow.model.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.Converter;
import java.util.List;
import org.folio.rest.workflow.dto.Comparison;

@Converter
public class ComparisonListConverter extends AbstractConverter<List<Comparison>> {

  @Override
  public TypeReference<List<Comparison>> getTypeReference() {
    return new TypeReference<List<Comparison>>() {};
  }

}
