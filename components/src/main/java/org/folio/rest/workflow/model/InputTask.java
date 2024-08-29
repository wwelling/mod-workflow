package org.folio.rest.workflow.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
public class InputTask extends AbstractTask {

  @Getter
  @Setter
  @ElementCollection
  private List<EmbeddedInput> inputs;

  public InputTask() {
    super();

    inputs = new ArrayList<>();
  }

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();

    if (inputs == null) {
      inputs = new ArrayList<>();
    }
  }

}
