package org.folio.rest.workflow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DirectoryActionTest {

  @ParameterizedTest
  @MethodSource("provideDirectoryActions")
  void enumerationIsValidTest(DirectoryAction enumeration, String expect) {
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
  private static Stream<Arguments> provideDirectoryActions() {
    return Stream.of(
      Arguments.of(DirectoryAction.READ_NEXT, "READ_NEXT"),
      Arguments.of(DirectoryAction.DELETE_NEXT, "DELETE_NEXT"),
      Arguments.of(DirectoryAction.LIST, "LIST"),
      Arguments.of(DirectoryAction.WRITE, "WRITE")
    );
  }

}
