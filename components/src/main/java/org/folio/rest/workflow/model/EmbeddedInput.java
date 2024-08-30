package org.folio.rest.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.enums.InputAttribute;
import org.folio.rest.workflow.enums.InputType;
import org.folio.rest.workflow.model.converter.InputAttributeListConverter;
import org.folio.rest.workflow.model.converter.StringListConverter;
import org.folio.rest.workflow.model.has.common.HasEmbeddedInputCommon;
import org.hibernate.annotations.ColumnDefault;

@Embeddable
public class EmbeddedInput implements HasEmbeddedInputCommon {

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
  @Column(nullable = false)
  private String fieldId;

  @Getter
  @Setter
  @Column(nullable = false)
  private String fieldLabel;

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

  public EmbeddedInput() {
    super();

    attributes = new ArrayList<>();
    fieldId = "";
    fieldLabel = "";
    inputType = InputType.TEXT;
    options = new ArrayList<>();
    required = false;
  }

  public void prePersist() {
    if (attributes == null) {
      attributes = new ArrayList<>();
    }

    if (fieldId == null) {
      fieldId = "";
    }

    if (fieldLabel == null) {
      fieldLabel = "";
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
