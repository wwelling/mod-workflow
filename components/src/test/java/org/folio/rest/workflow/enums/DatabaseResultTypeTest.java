package org.folio.rest.workflow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DatabaseResultTypeTest {

  @ParameterizedTest
  @MethodSource("provideDatabaseResultTypes")
  void enumerationIsValidTest(DatabaseResultType enumeration, String expect) {
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
  private static Stream<Arguments> provideDatabaseResultTypes() {
    return Stream.of(
      Arguments.of(DatabaseResultType.CSV, "CSV"),
      Arguments.of(DatabaseResultType.TSV, "TSV"),
      Arguments.of(DatabaseResultType.JSON, "JSON")
    );
  }

}
