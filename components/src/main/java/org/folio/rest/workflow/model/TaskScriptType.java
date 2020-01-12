package org.folio.rest.workflow.model;

public enum TaskScriptType {
  GROOVY("groovy"),
  JAVA("java"),
  JS("js"),
  PYTHON("py"),
  RUBY("rb");

  private final String extension;

  private TaskScriptType(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }
  
  

}