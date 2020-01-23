package org.folio.rest.workflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.folio.rest.workflow.dto.Comparison;
import org.folio.rest.workflow.dto.Mapping;
import org.folio.rest.workflow.dto.Request;
import org.folio.rest.workflow.model.converter.ComparisonListConverter;
import org.folio.rest.workflow.model.converter.MappingListConverter;
import org.folio.rest.workflow.model.converter.RequestConverter;

@Embeddable
public class EmbeddedExtractor {

  @Column(nullable = false)
  @Convert(converter = RequestConverter.class)
  private Request request;

  @NotNull
  @Column(nullable = false)
  private StreamMergeStrategy mergeStrategy;

  @Column(columnDefinition = "TEXT", nullable = false)
  @Convert(converter = ComparisonListConverter.class)
  private List<Comparison> comparisons;

  @Column(columnDefinition = "TEXT", nullable = false)
  @Convert(converter = MappingListConverter.class)
  private List<Mapping> mappings;

  public EmbeddedExtractor() {
    super();
    comparisons = new ArrayList<Comparison>();
    mappings = new ArrayList<Mapping>();
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

  public List<Mapping> getMappings() {
    return mappings;
  }

  public void setMappings(List<Mapping> mappings) {
    this.mappings = mappings;
  }

}
