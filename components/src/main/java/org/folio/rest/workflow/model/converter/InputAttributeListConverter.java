package org.folio.rest.workflow.model.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.Converter;
import java.util.List;
import org.folio.rest.workflow.enums.InputAttribute;

/**
 * Store a list of strings in the database as JSON. 
 */
@Converter
public class InputAttributeListConverter extends AbstractConverter<List<InputAttribute>> {

  @Override
  public TypeReference<List<InputAttribute>> getTypeReference() {
    return new TypeReference<List<InputAttribute>>() {};
  }

}
