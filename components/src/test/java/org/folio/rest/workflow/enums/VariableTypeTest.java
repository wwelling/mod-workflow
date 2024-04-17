package org.folio.rest.workflow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class VariableTypeTest {

  @ParameterizedTest
  @MethodSource("provideVariableTypes")
  void enumerationIsValidTest(VariableType enumeration, String expect) {
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
  private static Stream<Arguments> provideVariableTypes() {
    return Stream.of(
      Arguments.of(VariableType.PROCESS, "PROCESS"),
      Arguments.of(VariableType.LOCAL, "LOCAL")
    );
  }

}
