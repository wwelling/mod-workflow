package org.folio.rest.workflow.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.activemq.broker.BrokerService;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;
import org.folio.rest.workflow.exception.WorkflowImportException;
import org.folio.rest.workflow.exception.WorkflowImportInvalidOrMissingProperty;
import org.folio.rest.workflow.exception.WorkflowImportRequiredFileMissing;
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

  @Value("classpath:fwz/unit_test_fake.fwz")
  private Resource fwzFakeResource;

  @Value("classpath:fwz/unit_test_bzip2.tar.bz2")
  private Resource fwzBzip2AsBz2Resource;

  @Value("classpath:fwz/unit_test_bzip2.fwz")
  private Resource fwzBzip2Resource;

  @Value("classpath:fwz/unit_test_gzip.tar.gz")
  private Resource fwzGzipAsGzResource;

  @Value("classpath:fwz/unit_test_gzip-bad_code.fwz")
  private Resource fwzGzipBadCodeResource;

  @Value("classpath:fwz/unit_test_gzip-bad_deserializeas.fwz")
  private Resource fwzGzipBadDeserializeasResource;

  @Value("classpath:fwz/unit_test_gzip-bad_scriptformat.fwz")
  private Resource fwzGzipBadScriptformatResource;

  @Value("classpath:fwz/unit_test_gzip-missing_code.fwz")
  private Resource fwzGzipMisCodeResource;

  @Value("classpath:fwz/unit_test_gzip-missing_deserializeas.fwz")
  private Resource fwzGzipMisDeserializeasResource;

  @Value("classpath:fwz/unit_test_gzip-missing_script.fwz")
  private Resource fwzGzipMisScriptResource;

  @Value("classpath:fwz/unit_test_gzip-missing_scriptformat.fwz")
  private Resource fwzGzipMisScriptformatResource;

  @Value("classpath:fwz/unit_test_gzip-missing_setup.fwz")
  private Resource fwzGzipMisSetupResource;

  @Value("classpath:fwz/unit_test_gzip-missing_workflow.fwz")
  private Resource fwzGzipMisWorkflowResource;

  @Value("classpath:fwz/unit_test_gzip-python.fwz")
  private Resource fwzGzipPythonResource;

  @Value("classpath:fwz/unit_test_gzip.fwz")
  private Resource fwzGzipResource;

  @Value("classpath:fwz/unit_test_gzip-ruby.fwz")
  private Resource fwzGzipRubyResource;

  @Value("classpath:fwz/unit_test_gzip-unknown_version.fwz")
  private Resource fwzGzipUnVerResource;

  @Value("classpath:fwz/unit_test_zip.zip")
  private Resource fwzZipAsZipResource;

  @Value("classpath:fwz/unit_test_zip.fwz")
  private Resource fwzZipResource;

  private Workflow workflow;

  @BeforeEach
  void beforeEach() {
    workflow = new Workflow();
    workflow.setId(WORKFLOW_UUID);

    when(workflowRepo.save(any())).thenReturn(workflow);
  }

  @Test
  void importFileThrowsExceptionForFakeTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    assertThrows(WorkflowImportException.class, () ->
      workflowImportService.importFile(fwzFakeResource)
    );
  }

  @Test
  void importFileThrowsExceptionWithBadCodeTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    assertThrows(WorkflowImportInvalidOrMissingProperty.class, () ->
      workflowImportService.importFile(fwzGzipBadCodeResource)
    );
  }

  @Test
  void importFileThrowsExceptionWithBadDeserializeasTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    assertThrows(WorkflowImportInvalidOrMissingProperty.class, () ->
      workflowImportService.importFile(fwzGzipBadDeserializeasResource)
    );
  }

  @Test
  void importFileThrowsExceptionWithBadScriptformatTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    assertThrows(WorkflowImportInvalidOrMissingProperty.class, () ->
      workflowImportService.importFile(fwzGzipBadScriptformatResource)
    );
  }

  @Test
  void importFileThrowsExceptionWithMisDeserializeasTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    assertThrows(WorkflowImportInvalidOrMissingProperty.class, () ->
      workflowImportService.importFile(fwzGzipMisDeserializeasResource)
    );
  }

  @Test
  void importFileThrowsExceptionWithMisScriptTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    assertThrows(WorkflowImportRequiredFileMissing.class, () ->
      workflowImportService.importFile(fwzGzipMisScriptResource)
    );
  }

  @Test
  void importFileThrowsExceptionWithMisSetupTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    assertThrows(WorkflowImportRequiredFileMissing.class, () ->
      workflowImportService.importFile(fwzGzipMisSetupResource)
    );
  }

  @Test
  void importFileThrowsExceptionWithMisWorkflowTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    assertThrows(WorkflowImportRequiredFileMissing.class, () ->
      workflowImportService.importFile(fwzGzipMisWorkflowResource)
    );
  }

  @Test
  void importFileWorksForBzip2Test() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    Workflow imported = workflowImportService.importFile(fwzBzip2Resource);
    assertNotNull(imported);
    assertEquals(workflow.getId(), imported.getId());
  }

  @Test
  void importFileWorksForBzip2AsBz2Test() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    Workflow imported = workflowImportService.importFile(fwzBzip2AsBz2Resource);
    assertNotNull(imported);
    assertEquals(workflow.getId(), imported.getId());
  }

  @Test
  void importFileWorksForGzipTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    Workflow imported = workflowImportService.importFile(fwzGzipResource);
    assertNotNull(imported);
    assertEquals(workflow.getId(), imported.getId());
  }

  @Test
  void importFileWorksForGzipAsGzTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    Workflow imported = workflowImportService.importFile(fwzGzipAsGzResource);
    assertNotNull(imported);
    assertEquals(workflow.getId(), imported.getId());
  }

  @Test
  void importFileWorksForGzipWithPythonTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    Workflow imported = workflowImportService.importFile(fwzGzipPythonResource);
    assertNotNull(imported);
    assertEquals(workflow.getId(), imported.getId());
  }

  @Test
  void importFileWorksForGzipWithRubyTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    Workflow imported = workflowImportService.importFile(fwzGzipRubyResource);
    assertNotNull(imported);
    assertEquals(workflow.getId(), imported.getId());
  }

  @Test
  void importFileWorksForGzipWithUnknownVersionTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    Workflow imported = workflowImportService.importFile(fwzGzipUnVerResource);
    assertNotNull(imported);
    assertEquals(workflow.getId(), imported.getId());
  }

  @Test
  void importFileWorksForZipTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    Workflow imported = workflowImportService.importFile(fwzZipResource);
    assertNotNull(imported);
    assertEquals(workflow.getId(), imported.getId());
  }

  @Test
  void importFileWorksForZipAsZipTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    Workflow imported = workflowImportService.importFile(fwzZipAsZipResource);
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
