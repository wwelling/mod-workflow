package org.folio.rest.workflow.components;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class ExtractorTask extends Task {

  @ElementCollection
  private List<EnhancementComparison> enhancementComparisons;

  @ElementCollection
  private List<EnhancementMapping> enhancementMappings;

  private MergeStrategy mergeStrategy;

  public ExtractorTask() {
    super();
    enhancementComparisons = new ArrayList<EnhancementComparison>();
    enhancementMappings = new ArrayList<EnhancementMapping>();
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

  public MergeStrategy getMergeStrategy() {
    return mergeStrategy;
  }

  public void setMergeStrategy(MergeStrategy mergeStrategy) {
    this.mergeStrategy = mergeStrategy;
  }

  public List<EnhancementComparison> getEnhancementComparisons() {
    return enhancementComparisons;
  }

  public void setEnhancementComparisons(List<EnhancementComparison> enhancementComparisons) {
    this.enhancementComparisons = enhancementComparisons;
  }

  public List<EnhancementMapping> getEnhancementMappings() {
    return enhancementMappings;
  }

  public void setEnhancementMappings(List<EnhancementMapping> enhancementMappings) {
    this.enhancementMappings = enhancementMappings;
  }

}
