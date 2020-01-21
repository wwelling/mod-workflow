package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.folio.spring.domain.model.AbstractBaseEntity;

@Entity
public class Stream extends AbstractBaseEntity {

  @NotNull
  @Embedded
  private Request request;

  @NotNull
  @Column(nullable = false)
  private StreamMergeStrategy mergeStrategy;

  @ElementCollection
  private List<Comparison> comparisons;

  @ElementCollection
  private List<EnhancementMapping> enhancementMappings;

  public Stream() {
    super();
    comparisons = new ArrayList<Comparison>();
    enhancementMappings = new ArrayList<EnhancementMapping>();
  }

  public Request getRequest() {
    return request;
  }

  public void setRequest(Request request) {
    this.request = request;
  }

  public StreamMergeStrategy getMergeStrategy() {
    return mergeStrategy;
  }

  public void setMergeStrategy(StreamMergeStrategy mergeStrategy) {
    this.mergeStrategy = mergeStrategy;
  }

  public List<Comparison> getComparisons() {
    return comparisons;
  }

  public void settComparisons(List<Comparison> comparisons) {
    this.comparisons = comparisons;
  }

  public List<EnhancementMapping> getEnhancementMappings() {
    return enhancementMappings;
  }

  public void setEnhancementMappings(List<EnhancementMapping> enhancementMappings) {
    this.enhancementMappings = enhancementMappings;
  }

}
