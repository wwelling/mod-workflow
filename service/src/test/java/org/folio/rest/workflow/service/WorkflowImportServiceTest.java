package org.folio.rest.workflow.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.github.jknack.handlebars.internal.Files;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;
import org.folio.rest.workflow.exception.WorkflowImportException;
import org.folio.rest.workflow.model.Workflow;
import org.folio.rest.workflow.model.repo.NodeRepo;
import org.folio.rest.workflow.model.repo.TriggerRepo;
import org.folio.rest.workflow.model.repo.WorkflowRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class WorkflowImportServiceTest {

  private static final String WORKFLOW_UUID = "7dcd302f-a438-4ca5-a7eb-21653610d46f";

  private static final String PATH_FWZ = "fwz/";

  private static final String PATH_FWZ_GZIP = PATH_FWZ + "unit_test_gzip.fwz";

  private static final String PATH_FWZ_GZIP_UN_VER = PATH_FWZ + "unit_test_gzip-unknown_version.fwz";

  @InjectMocks
  private WorkflowImportService workflowImportService;

  @MockBean
  private NodeRepo nodeRepo;

  @MockBean
  private TriggerRepo triggerRepo;

  @MockBean
  private WorkflowRepo workflowRepo;

  @Mock
  private Resource mockFwz;

  @Mock
  private Page<Workflow> page;

  private ClassLoader classLoader;

  @BeforeEach
  public void beforeEach() {
    classLoader = getClass().getClassLoader();
  }

  @Disabled
  @Test
  public void importFileWorksForGzipTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    InputStream fwzStream = loadFile(PATH_FWZ_GZIP);
    when(mockFwz.getInputStream()).thenReturn(fwzStream);

    Workflow workflow = workflowImportService.importFile(mockFwz);
    assertNotNull(workflow);
    assertEquals(WORKFLOW_UUID, workflow.getId());
  }

  @Disabled
  @Test
  public void importFileWorksForGzipWithUnknownVersionTest() throws IOException, CompressorException, ArchiveException, WorkflowImportException {
    InputStream fwzStream = loadFile(PATH_FWZ_GZIP_UN_VER);
    when(mockFwz.getInputStream()).thenReturn(fwzStream);

    Workflow workflow = workflowImportService.importFile(mockFwz);
    assertNotNull(workflow);
    assertEquals(WORKFLOW_UUID, workflow.getId());
  }

  /**
   * Helper function for loading a file contents with assertions.
   *
   * @param path The path to the file to load.
   *
   * @return The contents of the file.
   *
   * @throws IOException On I/O error.
   */
  private InputStream loadFile(String path) throws IOException {
    URL fileUrl = classLoader.getResource(path);
    assertNotNull(fileUrl);

    File file = new File(fileUrl.getFile());
    assertNotNull(file);

    return new FileInputStream(file);
  }

}
