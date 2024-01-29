package org.folio.rest.workflow.enums;

/**
 * Provide HttpMethods in an enum for direct use in the database.
 *
 * <p>
 * More methods may exist than these but these are the ones supported in Spring 5 and earlier.
 * <a href="https://github.com/spring-projects/spring-framework/commit/6e335e3a9ff7727dc42e790904ae98a6d0edb7b5">Spring 6 replaced the HttpMethod enumeration with a class.</a>
 * </p>
 */
public enum HttpMethod {
  GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
}
