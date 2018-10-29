package org.folio.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.rest.domain.model.AbstractBaseEntity;
import org.springframework.http.HttpMethod;

@Entity
public class Trigger extends AbstractBaseEntity {

	@NotNull
	@Size(min = 4, max = 64)
	@Column(unique = true)
	private String name;

	@NotNull
	@Column
	private String tenant;

	@NotNull
	@Enumerated(EnumType.STRING)
	private HttpMethod method;

	@Column
	@NotNull
	private String pathPattern;

	public Trigger() {
		super();
	}

	public Trigger(String name, String Tenant, HttpMethod method, String pathPattern) {
		this();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public String getPathPattern() {
		return pathPattern;
	}

	public void setPathPattern(String pathPattern) {
		this.pathPattern = pathPattern;
	}

}
