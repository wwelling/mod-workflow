package org.folio.rest.workflow.utility;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CompressFileMagicTest {

  private static final byte[] NULL_BYTES = null;
  private static final String TWO_CHARS = "12";

  @ParameterizedTest
  @MethodSource("provideBzip2For")
  public void isBzip2Works(byte[] input, boolean expect) {
    assertEquals(expect, CompressFileMagic.isBzip2(input));
  }

  @ParameterizedTest
  @MethodSource("provideGzipFor")
  public void isGzipWorks(byte[] input, boolean expect) {
    assertEquals(expect, CompressFileMagic.isGzip(input));
  }

  @ParameterizedTest
  @MethodSource("provideZipFor")
  public void isZipWorks(byte[] input, boolean expect) {
    assertEquals(expect, CompressFileMagic.isZip(input));
  }

  /**
   * Helper function for parameterized tests for BZIP2.
   *
   * @return
   *   The arguments array stream with the stream columns as:
   *     - byte[] input A squence of bytes to compare against.
   *     - boolean expect The expected result.
   */
  private static Stream<Arguments> provideBzip2For() {
    return Stream.of(
      Arguments.of(CompressFileMagicHelper.getMagicBzip2(), true),
      Arguments.of(CompressFileMagicHelper.getMagicGzip(), false),
      Arguments.of(CompressFileMagicHelper.getMagicZip(), false),
      Arguments.of(CompressFileMagicHelper.getMagicZipEmpty(), false),
      Arguments.of(CompressFileMagicHelper.getMagicZipSpan(), false),
      Arguments.of(NULL_BYTES, false),
      Arguments.of(VALUE.getBytes(), false),
      Arguments.of(TWO_CHARS.getBytes(), false)
    );
  }

  /**
   * Helper function for parameterized tests for GZIP.
   *
   * @return
   *   The arguments array stream with the stream columns as:
   *     - byte[] input A squence of bytes to compare against.
   *     - boolean expect The expected result.
   */
  private static Stream<Arguments> provideGzipFor() {
    return Stream.of(
      Arguments.of(CompressFileMagicHelper.getMagicBzip2(), false),
      Arguments.of(CompressFileMagicHelper.getMagicGzip(), true),
      Arguments.of(CompressFileMagicHelper.getMagicZip(), false),
      Arguments.of(CompressFileMagicHelper.getMagicZipEmpty(), false),
      Arguments.of(CompressFileMagicHelper.getMagicZipSpan(), false),
      Arguments.of(NULL_BYTES, false),
      Arguments.of(VALUE.getBytes(), false),
      Arguments.of(TWO_CHARS.getBytes(), false)
    );
  }

  /**
   * Helper function for parameterized tests for ZIP.
   *
   * @return
   *   The arguments array stream with the stream columns as:
   *     - byte[] input A squence of bytes to compare against.
   *     - boolean expect The expected result.
   */
  private static Stream<Arguments> provideZipFor() {
    return Stream.of(
      Arguments.of(CompressFileMagicHelper.getMagicBzip2(), false),
      Arguments.of(CompressFileMagicHelper.getMagicGzip(), false),
      Arguments.of(CompressFileMagicHelper.getMagicZip(), true),
      Arguments.of(CompressFileMagicHelper.getMagicZipEmpty(), true),
      Arguments.of(CompressFileMagicHelper.getMagicZipSpan(), true),
      Arguments.of(NULL_BYTES, false),
      Arguments.of(VALUE.getBytes(), false),
      Arguments.of(TWO_CHARS.getBytes(), false)
    );
  }

  /**
   * Expose the protected constants via static methods.
   */
  private static class CompressFileMagicHelper extends CompressFileMagic {

    public static byte[]  getMagicBzip2() {
      return CompressFileMagicHelper.MAGIC_BZIP2;
    }

    public static byte[]  getMagicGzip() {
      return CompressFileMagicHelper.MAGIC_GZIP;
    }

    public static byte[]  getMagicZip() {
      return CompressFileMagicHelper.MAGIC_ZIP;
    }

    public static byte[]  getMagicZipEmpty() {
      return CompressFileMagicHelper.MAGIC_ZIP_EMPTY;
    }

    public static byte[]  getMagicZipSpan() {
      return CompressFileMagicHelper.MAGIC_ZIP_SPAN;
    }
  };

}
