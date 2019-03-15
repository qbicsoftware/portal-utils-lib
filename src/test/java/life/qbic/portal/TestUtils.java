package life.qbic.portal;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import life.qbic.portal.portlet.QBiCPortletUI;

/**
 * Utility methods for unit tests.
 */
public class TestUtils {
  /**
   * Copies the contents of the given resource (a file in the classpath) to {@link QBiCPortletUI#DEVELOPER_PROPERTIES_FILE_PATH}.
   * @param resourcePath the path (relative to classpath) where the source file is located.
   */
  public static void copyDeveloperPropertiesFrom(final String resourcePath) {
    copyResourceContents(resourcePath, QBiCPortletUI.DEVELOPER_PROPERTIES_FILE_PATH);
  }

  /**
   * Copies the cotnents of the given resource (a file in the classpath) to {@link QBiCPortletUI#PORTLET_PROPERTIES_FILE_PATH}.
   * @param resourcePath the path (relative to classpath) where the source file is located.
   */
  public static void copyPropertiesFrom(final String resourcePath) {
    copyResourceContents(resourcePath, QBiCPortletUI.PORTLET_PROPERTIES_FILE_PATH);
  }

  private static void copyResourceContents(final String origin, final String destination) {
    try {
      final ClassLoader classLoader = TestUtils.class.getClassLoader();
      final Path source = Paths.get(classLoader.getResource(origin).toURI());
      final Path target = Paths.get(source.getParent().toString(), File.separator, destination);
      Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    } catch (URISyntaxException | IOException e) {
      throw new RuntimeException("Could not copy properties", e);
    }
  }

  /**
   * Deletes the file {@link QBiCPortletUI#DEVELOPER_PROPERTIES_FILE_PATH} from the classpath, if it exists.
   */
  public static void deleteDeveloperPropertiesFile() {
    deleteResource(QBiCPortletUI.DEVELOPER_PROPERTIES_FILE_PATH);
  }

  /**
   * Deletes the file {@link QBiCPortletUI#DEVELOPER_PROPERTIES_FILE_PATH} from the classpath, if it exists.
   */
  public static void deletePropertiesFile() {
    deleteResource(QBiCPortletUI.PORTLET_PROPERTIES_FILE_PATH);
  }

  private static void deleteResource(final String resourcePath) {
    try {
      final URL configFileAsResource = TestUtils.class.getClassLoader().getResource(resourcePath);
      if (configFileAsResource != null) {
        Files.deleteIfExists(Paths.get(configFileAsResource.toURI()));
      }
    } catch (URISyntaxException | IOException e) {
      throw new RuntimeException("Could not delete resource " + resourcePath, e);
    }
  }

}
