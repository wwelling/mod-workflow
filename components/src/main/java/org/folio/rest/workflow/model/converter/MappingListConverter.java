package org.folio.rest.workflow.model.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.Converter;
import java.util.List;
import org.folio.rest.workflow.dto.Mapping;

@Converter
public class MappingListConverter extends AbstractConverter<List<Mapping>> {

  @Override
  public TypeReference<List<Mapping>> getTypeReference() {
    return new TypeReference<List<Mapping>>() {};
  }

}
