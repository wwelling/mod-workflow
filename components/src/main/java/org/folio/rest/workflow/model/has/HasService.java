package org.folio.rest.workflow.model.has;

/**
 * This interface provides the Service connection related methods.
 */
public interface HasService extends HasUsername {

  public String getBasePath();
  public String getHost();
  public int getPort();
  public String getScheme();

  public void setBasePath(String basePath);
  public void setHost(String host);
  public void setPort(int port);
  public void setScheme(String scheme);
}
