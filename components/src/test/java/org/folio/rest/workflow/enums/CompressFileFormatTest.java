package org.folio.rest.workflow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CompressFileFormatTest {

  @ParameterizedTest
  @MethodSource("provideCompressFileFormats")
  void enumerationIsValidTest(CompressFileFormat enumeration, String expect) {
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
  private static Stream<Arguments> provideCompressFileFormats() {
    return Stream.of(
      Arguments.of(CompressFileFormat.BZIP2, "BZIP2"),
      Arguments.of(CompressFileFormat.GZIP, "GZIP"),
      Arguments.of(CompressFileFormat.ZIP, "ZIP")
    );
  }

}
