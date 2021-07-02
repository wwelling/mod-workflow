package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.folio.spring.domain.model.AbstractBaseEntity;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "deserializeAs")
@JsonSubTypes({

    @JsonSubTypes.Type(value = StartEvent.class, name = "StartEvent"),

    @JsonSubTypes.Type(value = EndEvent.class, name = "EndEvent"),

    @JsonSubTypes.Type(value = ConditionalGateway.class, name = "ConditionalGateway"),

    @JsonSubTypes.Type(value = ParallelGateway.class, name = "ParallelGateway"),

    @JsonSubTypes.Type(value = ConnectTo.class, name = "ConnectTo"),

    @JsonSubTypes.Type(value = MoveToNode.class, name = "MoveToNode"),

    @JsonSubTypes.Type(value = CompressFileTask.class, name = "CompressFileTask"),

    @JsonSubTypes.Type(value = DatabaseConnectionTask.class, name = "DatabaseConnectionTask"),

    @JsonSubTypes.Type(value = DatabaseDisconnectTask.class, name = "DatabaseDisconnectTask"),

    @JsonSubTypes.Type(value = DatabaseQueryTask.class, name = "DatabaseQueryTask"),

    @JsonSubTypes.Type(value = EmailTask.class, name = "EmailTask"),

    @JsonSubTypes.Type(value = FileTask.class, name = "FileTask"),

    @JsonSubTypes.Type(value = RequestTask.class, name = "RequestTask"),

    @JsonSubTypes.Type(value = DirectoryTask.class, name = "DirectoryTask"),

    @JsonSubTypes.Type(value = ReceiveTask.class, name = "ReceiveTask"),

    @JsonSubTypes.Type(value = ProcessorTask.class, name = "ProcessorTask"),

    @JsonSubTypes.Type(value = ScriptTask.class, name = "ScriptTask"),

    @JsonSubTypes.Type(value = Subprocess.class, name = "Subprocess"),

    @JsonSubTypes.Type(value = EventSubprocess.class, name = "EventSubprocess")

})
public abstract class Node extends AbstractBaseEntity {

  @NotNull
  @Size(min = 3, max = 64)
  @Column(nullable = false)
  private String name;

  @Size(min = 0, max = 512)
  @Column(nullable = true)
  private String description;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String deserializeAs = this.getClass().getSimpleName();

  public Node() {
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

  public String getIdentifier() {
    String regex = "([a-z])([A-Z]+)";
    String replacement = "$1_$2";
    String name = getClass().getSimpleName();
    return String.format("%s_%s", name.replaceAll(regex, replacement).toLowerCase(), getId().replace("-", "_"));
  }

}
