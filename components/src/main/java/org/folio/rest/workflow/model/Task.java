package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.spring.domain.model.AbstractBaseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "deserializeAs")
@JsonSubTypes({

    @JsonSubTypes.Type(value = EmailTask.class, name = "EmailTask"),

    @JsonSubTypes.Type(value = ProcessorTask.class, name = "ProcessorTask"),

    @JsonSubTypes.Type(value = RequestTask.class, name = "RequestTask")

})
public abstract class Task extends AbstractBaseEntity {

  @NotNull
  @Size(min = 4, max = 64)
  @Column(unique = true)
  private String name;

  @NotNull
  @Size(min = 4, max = 512)
  @Column(nullable = false)
  private String description;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String deserializeAs = this.getClass().getSimpleName();

  public Task() {
    super();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDeserializeAs() {
    return deserializeAs;
  }

  public void setDeserializeAs(String deserializeAs) {
    this.deserializeAs = deserializeAs;
  }

  public abstract String id(int index);

}
