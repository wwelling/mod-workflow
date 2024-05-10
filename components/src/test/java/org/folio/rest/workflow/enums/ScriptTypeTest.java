package org.folio.rest.workflow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ScriptTypeTest {

  @ParameterizedTest
  @MethodSource("provideScriptTypes")
  void enumerationIsValidTest(ScriptType enumeration, String expect) {
    assertEquals(expect, enumeration.getExtension());
  }

  /**
   * Provide variables for the given enumeration parameterized test.
   *
   * @return
   *   The arguments array stream with the stream columns as:
   *     - enumeration: The enumeration value.
   *     - expect: The string that the enumeration is expected to match.
   */
  private static Stream<Arguments> provideScriptTypes() {
    return Stream.of(
      Arguments.of(ScriptType.GROOVY, "groovy"),
      Arguments.of(ScriptType.JAVA, "java"),
      Arguments.of(ScriptType.JS, "js"),
      Arguments.of(ScriptType.PYTHON, "py"),
      Arguments.of(ScriptType.RUBY, "rb")
    );
  }

}
