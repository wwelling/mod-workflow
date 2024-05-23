package org.folio.rest.workflow.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.folio.rest.workflow.enums.CompressFileFormat;

/**
 * Provide functionality to detect file mime-types based on the magic headers.
 *
 * This approach is more reliable than detecting via file-extensions.
 *
 * @see <a href="https://en.wikipedia.org/wiki/List_of_file_signatures">(Wikipedia) List of Magic Headers</a>
 */
public class CompressFileMagic {

  private CompressFileMagic() {
    throw new IllegalStateException("Attempt to instatiate a utility class.");
  }

  /**
   * A minimum header size needed to perform the magic checks.
   */
  protected static final int HEADER_SIZE = 4;

  /**
   * Magic header for the BZIP2 format.
   */
  protected static final byte[] MAGIC_BZIP2 = { 0x42, 0x5A, 0x68 };

  /**
   * Magic header for the GZIP format.
   */
  protected static final byte[] MAGIC_GZIP = { 0x1F, (byte) 0x8B };

  /**
   * Magic header for the ZIP format.
   */
  protected static final byte[] MAGIC_ZIP = { 0x50, 0x4B, 0x03, 0x04 };

  /**
   * Magic header for the ZIP format that is empty.
   */
  protected static final byte[] MAGIC_ZIP_EMPTY = { 0x50, 0x4B, 0x05, 0x06 };

  /**
   * Magic header for the ZIP format that is spanned.
   */
  protected static final byte[] MAGIC_ZIP_SPAN = { 0x50, 0x4B, 0x07, 0x08 };

  /**
   * Detect if a string represents a BZIP2 format.
   *
   * @param input The string representing some of the initial bytes of a file.
   *
   * @return TRUE if the string represents the format and FALSE otherwise.
   */
  public static boolean isBzip2(byte[] input) {
    return matchBytes(input, MAGIC_BZIP2);
  }

  /**
   * Detect if a string represents a GZIP format.
   *
   * @param input The string representing some of the initial bytes of a file.
   *
   * @return TRUE if the string represents the format and FALSE otherwise.
   */
  public static boolean isGzip(byte[] input) {
    return matchBytes(input, MAGIC_GZIP);
  }

  /**
   * Detect if a string represents a ZIP format.
   *
   * @param input The string representing some of the initial bytes of a file.
   *
   * @return TRUE if the string represents the format and FALSE otherwise.
   */
  public static boolean isZip(byte[] input) {
    if (!matchBytes(input, MAGIC_ZIP) && !matchBytes(input, MAGIC_ZIP_EMPTY) && !matchBytes(input, MAGIC_ZIP_SPAN)) {
      return false;
    }

    return true;
  }

  /**
   * Detect a supported format from the file header.
   *
   * @param file The file being detected.
   *
   * @return A supported type on a successfull match and NULL otherwise.
   *
   * @throws IOException On error reading the file stream.
   */
  public static CompressFileFormat detectFormat(File file) throws IOException {
    try (FileInputStream fis = new FileInputStream(file)) {
      return detectFormat(fis);
    }
  }

  /**
   * Detect a supported format from the file header.
   *
   * @param inputStream A stream of the file being detected.
   *
   * @return A supported type on a successfull match and NULL otherwise.
   *
   * @throws IOException On error reading the file stream.
   */
  public static CompressFileFormat detectFormat(InputStream inputStream) throws IOException {
    byte[] header = inputStream.readNBytes(HEADER_SIZE);

    if (CompressFileMagic.isBzip2(header)) {
      return CompressFileFormat.BZIP2;
    }

    if (CompressFileMagic.isGzip(header)) {
      return CompressFileFormat.GZIP;
    }

    if (CompressFileMagic.isZip(header)) {
      return CompressFileFormat.ZIP;
    }

    return null;
  }

  /**
   * Determine if a byte sequence matches another.
   *
   * @param input The string representing some of the initial bytes of a file.
   *
   * @return TRUE if the string represents the format and FALSE otherwise.
   */
  private static boolean matchBytes(byte[] input, byte[] match) {
    if (input == null || input.length < match.length) return false;

    for (int i = 0; i < match.length; i++) {
      if (input[i] != match[i]) return false;
    }

    return true;
  }

}
