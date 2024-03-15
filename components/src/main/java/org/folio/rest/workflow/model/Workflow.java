package org.folio.rest.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.converter.JsonNodeConverter;
import org.folio.rest.workflow.model.has.HasDeploymentId;
import org.folio.rest.workflow.model.has.HasId;
import org.folio.rest.workflow.model.has.HasInformational;
import org.folio.rest.workflow.model.has.HasName;
import org.folio.rest.workflow.model.has.HasNodes;
import org.folio.rest.workflow.model.has.HasVersionTag;
import org.folio.rest.workflow.model.has.common.HasWorkflowCommon;
import org.folio.spring.domain.model.AbstractBaseEntity;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Workflow extends AbstractBaseEntity implements HasDeploymentId, HasId, HasInformational, HasName, HasNodes, HasVersionTag, HasWorkflowCommon {

  @Getter
  @Setter
  @NotNull
  @Size(min = 4, max = 64)
  @Column(unique = true)
  private String name;

  @Getter
  @Setter
  @Size(min = 0, max = 512)
  @Column(nullable = true)
  private String description;

  @Getter
  @Setter
  @NotNull
  @Size(min = 1, max = 64)
  @Column(nullable = false)
  private String versionTag;

  @Getter
  @Setter
  @Min(0)
  @Column(nullable = false)
  private Integer historyTimeToLive;

  @Getter
  @Setter
  @Column(nullable = false)
  private boolean active;

  @Getter
  @Setter
  @Column(unique = true)
  private String deploymentId;

  @Getter
  @Setter
  @Embedded
  private Setup setup;

  @Getter
  @Setter
  @OneToMany
  @OrderColumn
  private List<Node> nodes;

  @Getter
  @Setter
  @ElementCollection
  @CollectionTable(name = "workflow_initial_context", joinColumns = @JoinColumn(name = "workflow_id"))
  @MapKeyColumn(name = "context_key")
  @Column(name = "context_value")
  @Convert(converter = JsonNodeConverter.class, attributeName = "value")
  private Map<String, JsonNode> initialContext;

  public Workflow() {
    super();

    active = false;
    nodes = new ArrayList<Node>();
    initialContext = new HashMap<String, JsonNode>();
    historyTimeToLive = 0;
  }

}
