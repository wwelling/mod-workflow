package org.folio.rest.workflow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SftpOpTest {

  @ParameterizedTest
  @MethodSource("provideSftpOps")
  void enumerationIsValidTest(SftpOp enumeration, String expect) {
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
  private static Stream<Arguments> provideSftpOps() {
    return Stream.of(
      Arguments.of(SftpOp.GET, "GET"),
      Arguments.of(SftpOp.PUT, "PUT")
    );
  }

}
