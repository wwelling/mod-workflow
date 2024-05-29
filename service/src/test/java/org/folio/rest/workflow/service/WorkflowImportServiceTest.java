package org.folio.rest.workflow.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.activemq.broker.BrokerService;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;
import org.folio.rest.workflow.exception.WorkflowImportException;
import org.folio.rest.workflow.model.Workflow;
import org.folio.rest.workflow.model.repo.NodeRepo;
import org.folio.rest.workflow.model.repo.TriggerRepo;
import org.folio.rest.workflow.model.repo.WorkflowRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ExtendWith(MockitoExtension.class)
class WorkflowImportServiceTest {

  private static final String WORKFLOW_UUID = "7dcd302f-a438-4ca5-a7eb-21653610d46f";

  @InjectMocks
  private WorkflowImportService workflowImportService;

  @MockBean
  private NodeRepo nodeRepo;

  @MockBean
  private TriggerRepo triggerRepo;

  @MockBean
  private WorkflowRepo workflowRepo;

  @SpyBean
  private ObjectMapper objectMapper;

  @Mock
  private Page<Workflow> page;

  @Value("classpath:fwz/unit_test_gzip.fwz")
  private Resource fwzGzipResource;

  @Value("classpath:fwz/unit_test_gzip-unknown_version.fwz")
  private Resource fwzGzipUnVerResource;

  private Workflow workflow;

  @BeforeEach
  void beforeEach() {
    workflow = new Workflow();
    workflow.setId(WORKFLOW_UUID);

    when(workflowRepo.save(any())).thenReturn(workflow);
  }

  @Test
  void importFileWorksForGzipTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    Workflow imported = workflowImportService.importFile(fwzGzipResource);
    assertNotNull(imported);
    assertEquals(workflow.getId(), imported.getId());
  }

  @Test
  void importFileWorksForGzipWithUnknownVersionTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    Workflow imported = workflowImportService.importFile(fwzGzipUnVerResource);
    assertNotNull(imported);
    assertEquals(workflow.getId(), imported.getId());
  }

  /**
   * Forcibly disable JmsBroker due to port conflict problems.
   *
   * The SpringBootTest annotation is resulting in the JmsBroker being loaded.
   * Other tests classes may be doing this as well.
   * Spring fails to close the broker connection between tests.
   *
   * The Broker is not needed, so mock it into nothingness.
   * The `allow-bean-definition-overriding` must be set to true.
   */
  @TestConfiguration
  public static class JmsBrokerConfig {

    @Bean
    public BrokerService broker() throws Exception {
      return Mockito.mock(BrokerService.class);
    }
  }

}
