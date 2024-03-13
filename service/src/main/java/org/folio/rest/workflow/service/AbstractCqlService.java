package org.folio.rest.workflow.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import java.util.stream.Collector;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Provide common CQL-specific service functionality.
 *
 * @param <T> The class type being searched for.
 */
public abstract class AbstractCqlService<T> {

  /**
   * String used in JSON representing the total records.
   */
  public static final String TOTAL_RECORDS = "TotalRecords";

  @Autowired
  protected ObjectMapper mapper;

  /**
   * Use CQL to find a list of Workflows.
   *
   * @param query The CQL query where if empty then all Workflows are returned.
   * @param offset The query offset.
   * @param limit The query limit.
   *
   * @return Paginated Workflow results.
   */
  abstract public ObjectNode findByCql(String query, Long offset, Integer limit);

  /**
   * Get the type name to be used in the json string (should have the 's', such as 'workflows').
   *
   * @return The string used to represent the type when generating the JSON.
   */
  abstract protected String getTypeName();

  /**
   * Convert the list and the total into the JSON structured response.
   *
   * @param list The list of types, generally from a repo.findByCql() call.
   * @param total The total of types in the database, generally from a repo.count() call.
   *
   * @return The build JSON.
   */
  protected ObjectNode toJson(List<T> list, long total) {
    final ObjectNode root = mapper.createObjectNode();

    list.stream().collect(Collector.of(
      () -> root.putArray(getTypeName()),
      (l1, item) -> l1.addPOJO(item),
      (l1, l2) -> l1.addAll(l2),
      Collector.Characteristics.IDENTITY_FINISH
    ));

    root.put(TOTAL_RECORDS, total);

    return root;
  }
}
