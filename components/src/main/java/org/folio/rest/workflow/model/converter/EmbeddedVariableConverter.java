package org.folio.rest.workflow.model.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.Converter;
import org.folio.rest.workflow.model.EmbeddedVariable;

/**
 * Store an EmbeddedVariable in the database as JSON.
 */
@Converter
public class EmbeddedVariableConverter extends AbstractConverter<EmbeddedVariable> {

  @Override
  public TypeReference<EmbeddedVariable> getTypeReference() {
    return new TypeReference<EmbeddedVariable>() {};
  }

}
