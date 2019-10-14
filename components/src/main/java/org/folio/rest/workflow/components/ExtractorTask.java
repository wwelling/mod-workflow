package org.folio.rest.workflow.components;

import javax.persistence.Entity;

@Entity
public class ExtractorTask extends Task {

  private String comparisonProperties;

  private String enhancementProperty;

  private MergeStrategy mergeStrategy;

  public ExtractorTask() {
    super();
  }

  public ExtractorTask(String comparisonProperties, MergeStrategy mergeStrategy) {
    this();
    setComparisonProperties(comparisonProperties);
    setMergeStrategy(mergeStrategy);
  }

  @Override
  public String getDelegate() {
    switch(getMergeStrategy()) {
      case MERGE:
        return "orderedMergingExtractorDelegate";
      case ENHANCE:
        return "enhancingExtractorDelegate";
      case CONCAT:
      default:
        return "concatenatingExtractorDelegate";
    }
  }

  public String getComparisonProperties() {
    return comparisonProperties;
  }

  public void setComparisonProperties(String comparisonProperties) {
    this.comparisonProperties = comparisonProperties;
  }

  public String getEnhancementProperty() {
    return enhancementProperty;
  }

  public void setEnhancementProperty(String enhancementProperty) {
    this.enhancementProperty = enhancementProperty;
  }

  public MergeStrategy getMergeStrategy() {
    return mergeStrategy;
  }

  public void setMergeStrategy(MergeStrategy mergeStrategy) {
    this.mergeStrategy = mergeStrategy;
  }

}
