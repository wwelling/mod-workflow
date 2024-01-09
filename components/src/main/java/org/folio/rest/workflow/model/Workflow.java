package org.folio.rest.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.folio.rest.workflow.model.converter.JsonNodeConverter;
import org.folio.rest.workflow.model.has.HasId;
import org.folio.rest.workflow.model.has.HasInformational;
import org.folio.rest.workflow.model.has.HasName;
import org.folio.rest.workflow.model.has.HasNodes;
import org.folio.rest.workflow.model.has.HasVersionTag;
import org.folio.rest.workflow.model.has.common.HasWorkflowCommon;
import org.folio.spring.domain.model.AbstractBaseEntity;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Workflow extends AbstractBaseEntity implements HasId, HasInformational, HasName, HasNodes, HasVersionTag, HasWorkflowCommon {

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
