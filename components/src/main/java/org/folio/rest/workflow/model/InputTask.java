package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.InputAttribute;
import org.folio.rest.workflow.enums.InputType;
import org.folio.rest.workflow.model.converter.InputAttributeListConverter;
import org.folio.rest.workflow.model.converter.StringListConverter;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class InputTask extends AbstractDelegateTaskNode {

  @Getter
  @Setter
  @Column(columnDefinition = "TEXT", nullable = false)
  @Convert(converter = InputAttributeListConverter.class)
  private List<InputAttribute> attributes;

  @Getter
  @Setter
  @Column(nullable = true)
  private String defaultValue;

  @Getter
  @Setter
  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private InputType inputType;

  @Getter
  @Setter
  @Column(columnDefinition = "TEXT", nullable = false)
  @Convert(converter = StringListConverter.class)
  private List<String> options;

  @Getter
  @Setter
  @Column(nullable = false)
  @ColumnDefault("false")
  private Boolean required;

  public InputTask() {
    super();

    attributes = new ArrayList<>();
    inputType = InputType.TEXT;
    options = new ArrayList<>();
    required = false;
  }

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();

    if (attributes == null) {
      attributes = new ArrayList<>();
    }

    if (inputType == null) {
      inputType = InputType.TEXT;
    }

    if (options == null) {
      options = new ArrayList<>();
    }

    if (required == null) {
      required = false;
    }
  }

}
