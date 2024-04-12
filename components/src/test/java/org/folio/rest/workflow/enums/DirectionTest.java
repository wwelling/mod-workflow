package org.folio.rest.workflow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {

  @ParameterizedTest
  @MethodSource("provideDirections")
  void enumerationIsValidTest(Direction enumeration, String expect) {
    assertEquals(expect, enumeration.getValue());
  }

  /**
   * Provide variables for the given enumeration parameterized test.
   *
   * @return
   *   The arguments array stream with the stream columns as:
   *     - enumeration: The enumeration value.
   *     - expect: The string that the enumeration is expected to match.
   */
  private static Stream<Arguments> provideDirections() {
    return Stream.of(
      Arguments.of(Direction.UNSPECIFIED, "Unspecified"),
      Arguments.of(Direction.CONVERGING, "Converging"),
      Arguments.of(Direction.DIVERGING, "Diverging"),
      Arguments.of(Direction.MIXED, "Mixed")
    );
  }

}
