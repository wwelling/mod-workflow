package org.folio.rest.workflow.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;

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

  /**
   * The object mapper, which must be initialized in the extending class' constructor.
   */
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
  public abstract ObjectNode findByCql(String query, Long offset, Integer limit);

  /**
   * Get the type name to be used in the json string (should have the 's', such as 'workflows').
   *
   * @return The string used to represent the type when generating the JSON.
   */
  protected abstract String getTypeName();

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
    final ArrayNode array = root.putArray(getTypeName());

    list.forEach((T item) -> array.addPOJO(item));

    root.put(TOTAL_RECORDS, total);

    return root;
  }
}
