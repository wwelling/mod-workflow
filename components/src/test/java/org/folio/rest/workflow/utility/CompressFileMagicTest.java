package org.folio.rest.workflow.utility;

import static org.folio.spring.test.mock.MockMvcConstant.VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;
import org.folio.rest.workflow.enums.CompressFileFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CompressFileMagicTest {

  private static final String PATH_MAGIC = "magic/";

  private static final String PATH_MAGIC_BZIP2 = PATH_MAGIC + "test.txt.tar.bz2";

  private static final String PATH_MAGIC_GZIP = PATH_MAGIC + "test.txt.tar.gz";

  private static final String PATH_MAGIC_NONE = PATH_MAGIC + "test.txt.none";

  private static final String PATH_MAGIC_ZIP = PATH_MAGIC + "test.txt.zip";

  private static final String PATH_MAGIC_ZIP_EMPTY = PATH_MAGIC + "test.empty.zip";

  private static final String PATH_MAGIC_ZIP_SPAN = PATH_MAGIC + "test.spanned.zip";

  private static final byte[] NULL_BYTES = null;

  private static final String TWO_CHARS = "12";

  private ClassLoader classLoader;

  @BeforeEach
  void beforeEach() {
    classLoader = getClass().getClassLoader();
  }

  @Test
  void attemptToInstantiateThrowsException() {
    assertThrows(IllegalStateException.class, () -> {
      new CompressFileMagicHelper();
    });
  }

  @ParameterizedTest
  @MethodSource("provideBzip2For")
  void isBzip2Works(byte[] input, boolean expect) {
    assertEquals(expect, CompressFileMagic.isBzip2(input));
  }

  @ParameterizedTest
  @MethodSource("provideGzipFor")
  void isGzipWorks(byte[] input, boolean expect) {
    assertEquals(expect, CompressFileMagic.isGzip(input));
  }

  @ParameterizedTest
  @MethodSource("provideZipFor")
  void isZipWorks(byte[] input, boolean expect) {
    assertEquals(expect, CompressFileMagic.isZip(input));
  }

  @ParameterizedTest
  @MethodSource("provideNullFor")
  void isNullWorks(byte[] input, boolean expect) {
    assertEquals(expect, CompressFileMagicHelper.isNull(input));
  }

  @ParameterizedTest
  @MethodSource("detectFormatFor")
  void detectFormatWorks(String path, CompressFileFormat expect) throws IOException {
    File file = loadFile(path);
    assertEquals(expect, CompressFileMagic.detectFormat(file));
  }

  /**
   * Helper function for parameterized tests for detectFormat().
   *
   * @return
   *   The arguments array stream with the stream columns as:
   *     - String path The path to the file, relative to the test resources.
   *     - CompressFileFormat expect The format to expect, which can be NULL.
   */
  private static Stream<Arguments> detectFormatFor() {
    return Stream.of(
      Arguments.of(PATH_MAGIC_BZIP2, CompressFileFormat.BZIP2),
      Arguments.of(PATH_MAGIC_GZIP, CompressFileFormat.GZIP),
      Arguments.of(PATH_MAGIC_NONE, (CompressFileFormat) null),
      Arguments.of(PATH_MAGIC_ZIP, CompressFileFormat.ZIP),
      Arguments.of(PATH_MAGIC_ZIP_EMPTY, CompressFileFormat.ZIP),
      Arguments.of(PATH_MAGIC_ZIP_SPAN, CompressFileFormat.ZIP)
    );
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
      Arguments.of(TWO_CHARS.getBytes(), false),
      Arguments.of(null, false)
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
      Arguments.of(TWO_CHARS.getBytes(), false),
      Arguments.of(null, false)
    );
  }

  /**
   * Helper function for parameterized tests for NULL case.
   *
   * @return
   *   The arguments array stream with the stream columns as:
   *     - byte[] input A squence of bytes to compare against.
   *     - boolean expect The expected result.
   */
  private static Stream<Arguments> provideNullFor() {
    return Stream.of(
      Arguments.of(CompressFileMagicHelper.getMagicBzip2(), false),
      Arguments.of(CompressFileMagicHelper.getMagicGzip(), false),
      Arguments.of(CompressFileMagicHelper.getMagicZip(), false),
      Arguments.of(CompressFileMagicHelper.getMagicZipEmpty(), false),
      Arguments.of(CompressFileMagicHelper.getMagicZipSpan(), false),
      Arguments.of(NULL_BYTES, false),
      Arguments.of(VALUE.getBytes(), false),
      Arguments.of(TWO_CHARS.getBytes(), false),
      Arguments.of(null, false)
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
      Arguments.of(TWO_CHARS.getBytes(), false),
      Arguments.of(null, false)
    );
  }

  /**
   * Helper function for loading a file contents with assertions.
   *
   * @param path The path to the file to load.
   *
   * @return The contents of the file.
   */
  private File loadFile(String path) {
    URL fileUrl = classLoader.getResource(path);
    assertNotNull(fileUrl, "For path: " + path);

    return new File(fileUrl.getFile());
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

    /**
     * Fake method for unit testing NULL case on right value.
     */
    public static boolean isNull(byte[] input) {
      return matchBytes(input, null);
    }

  };

}
