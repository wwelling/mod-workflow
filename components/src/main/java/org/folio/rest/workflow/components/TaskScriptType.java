package org.folio.rest.workflow.components;

public enum TaskScriptType {
  GROOVY("groovy"), JAVA("java"), JS("js"), PYTHON("py"), RUBY("rb");

  public final String engineName;

  private TaskScriptType(String engineName) {
    this.engineName = engineName;
  }

}