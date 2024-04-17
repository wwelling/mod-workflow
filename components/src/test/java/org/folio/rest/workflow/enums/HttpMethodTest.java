package org.folio.rest.workflow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HttpMethodTest {

  @ParameterizedTest
  @MethodSource("provideHttpMethods")
  void enumerationIsValidTest(HttpMethod enumeration, String expect) {
    assertEquals(expect, enumeration.name());
  }

  /**
   * Provide variables for the given enumeration parameterized test.
   *
   * @return
   *   The arguments array stream with the stream columns as:
   *     - enumeration: The enumeration value.
   *     - expect: The string that the enumeration is expected to match.
   */
  private static Stream<Arguments> provideHttpMethods() {
    return Stream.of(
      Arguments.of(HttpMethod.GET, "GET"),
      Arguments.of(HttpMethod.HEAD, "HEAD"),
      Arguments.of(HttpMethod.POST, "POST"),
      Arguments.of(HttpMethod.PUT, "PUT"),
      Arguments.of(HttpMethod.PATCH, "PATCH"),
      Arguments.of(HttpMethod.DELETE, "DELETE"),
      Arguments.of(HttpMethod.OPTIONS, "OPTIONS"),
      Arguments.of(HttpMethod.TRACE, "TRACE")
    );
  }

}
