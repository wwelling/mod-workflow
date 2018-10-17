package org.folio.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.folio.rest.model.Action;
import org.folio.rest.model.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OkapiDiscoveryService {

  private final static Logger log = LoggerFactory.getLogger(OkapiDiscoveryService.class);

  private final static String PROVIDES = "provides";
  private final static String ID = "id";
  private final static String HANDLERS = "handlers";

  private final static String PROXY_TENANT_BASE_PATH = "/_/proxy/tenants/";
  private final static String PROXY_MODULE_BASE_PATH = "/_/proxy/modules/";
  private final static String MODULES_SUB_PATH = "/modules";

  @Value("${tenant.headerName:X-Okapi-Tenant}")
  private String tenantHeaderName;

  @Value("${okapi.location}")
  private String okapiLocation;

  @Autowired
  private HttpService httpService;

  @Autowired
  private ObjectMapper objectMapper;

  public List<Action> getActionsByTenant(String tenant) throws Exception {
    List<Action> actions = new ArrayList<>();
    JsonNode modulesNode = getModules(tenant);
    for (JsonNode moduleNode : modulesNode) {
      String id = moduleNode.get("id").asText();
      actions.addAll(getActionsByTenantAndModuleId(tenant, id));
    }
    return actions;
  }

  public List<Action> getActionsByTenantAndModuleId(String tenant, String id) throws Exception {
    Map<String, List<Handler>> handlerMap = getHandlers(tenant, id);
    List<Action> actions = new ArrayList<>();
    for (Map.Entry<String, List<Handler>> entry : handlerMap.entrySet()) {
      String interfaceName = entry.getKey();
      List<Handler> handlers = entry.getValue();
      handlers.forEach(handler -> {
        actions.addAll(handler.getActionByInterface(interfaceName));
      });
    }
    return actions;
  }

  public Map<String, List<Handler>> getHandlers(String tenant, String id) throws Exception {
    JsonNode moduleDescriptorNode = getModuleDescriptor(tenant, id);
    Map<String, List<Handler>> handlerMap = new HashMap<String, List<Handler>>();
    for (JsonNode interfaceNode : moduleDescriptorNode.get(PROVIDES)) {
      List<Handler> handlers = new ArrayList<>();
      String interfaceName = interfaceNode.get(ID).asText();
      for (JsonNode handlersNode : interfaceNode.get(HANDLERS)) {
        handlers.add(objectMapper.readValue(handlersNode.toString(), Handler.class));
      }
      handlerMap.put(interfaceName, handlers);
    }
    return handlerMap;
  }

  public JsonNode getModules(String tenant) {
    String url = okapiLocation + PROXY_TENANT_BASE_PATH + tenant + MODULES_SUB_PATH;
    log.debug("Proxy request to {}", url);
    ResponseEntity<JsonNode> response = request(url, tenant);
    log.debug("Response status code " + response.getStatusCode().toString());
    log.debug("Response body " + response.getBody());
    return response.getBody();
  }

  public JsonNode getModuleDescriptor(String tenant, String id) {
    String url = okapiLocation + PROXY_MODULE_BASE_PATH + id;
    log.debug("Proxy request to {}", url);
    ResponseEntity<JsonNode> response = request(url, tenant);
    log.debug("Response status code {}", response.getStatusCode().toString());
    log.debug("Response body {}", response.getBody());
    return response.getBody();
  }

  private ResponseEntity<JsonNode> request(String url, String tenant) {
    HttpMethod method = HttpMethod.GET;
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<?> request = new HttpEntity<>(headers);
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.add(tenantHeaderName, tenant);
    return this.httpService.exchange(url, method, request, JsonNode.class, new Object[0]);
  }

}
