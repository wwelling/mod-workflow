package org.folio.rest.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class EmailTask extends Task {

  @NotNull
  @Column(nullable = false)
  private String bodyContextKey;

  @NotNull
  @Column(nullable = false)
  private String subject;

  @NotNull
  @Column(nullable = false)
  private String to;

  @Column(nullable = true)
  private String cc;

  @Column(nullable = true)
  private String bcc;

  public EmailTask() {
    super();
  }

  public String getBodyContextKey() {
    return bodyContextKey;
  }

  public void setBodyContextKey(String bodyContextKey) {
    this.bodyContextKey = bodyContextKey;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getCc() {
    return cc;
  }

  public void setCc(String cc) {
    this.cc = cc;
  }

  public String getBcc() {
    return bcc;
  }

  public void setBcc(String bcc) {
    this.bcc = bcc;
  }

  @Override
  public String id(int index) {
    return String.format("email_task_%s", index);
  }

}
