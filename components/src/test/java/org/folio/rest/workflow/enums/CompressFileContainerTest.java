package org.folio.rest.workflow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CompressFileContainerTest {

  @ParameterizedTest
  @MethodSource("provideCompressFileContainers")
  void enumerationIsValidTest(CompressFileContainer enumeration, String expect) {
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
  private static Stream<Arguments> provideCompressFileContainers() {
    return Stream.of(
      Arguments.of(CompressFileContainer.NONE, "NONE"),
      Arguments.of(CompressFileContainer.TAR, "TAR")
    );
  }

}
