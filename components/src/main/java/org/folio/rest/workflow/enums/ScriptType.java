package org.folio.rest.workflow.enums;

public enum ScriptType {
  GROOVY("groovy"),
  JAVA("java"),
  JS("js"),
  PYTHON("py"),
  RUBY("rb");

  private final String extension;

  private ScriptType(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }

}
