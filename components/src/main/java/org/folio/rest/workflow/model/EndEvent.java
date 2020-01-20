package org.folio.rest.workflow.model;

import javax.persistence.Entity;

import org.folio.rest.workflow.components.Event;

@Entity
public class EndEvent extends Node implements Event {

}
