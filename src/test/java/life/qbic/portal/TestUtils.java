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
   * Finds a file in the classpath using the given {@code propertiesFilePath} and copies its contents a file named {@link QBiCPortletUI#PORTLET_PROPERTIES_FILE_PATH},
   * also located in the classpath.
   * @param propertiesFilePath the destination.
   * @throws URISyntaxException
   * @throws IOException
   */
  public static void copyPropertiesFrom(final String propertiesFilePath)
      throws URISyntaxException, IOException {
    final ClassLoader classLoader = TestUtils.class.getClassLoader();
    final Path source = Paths.get(classLoader.getResource(propertiesFilePath).toURI());
    final Path target = Paths.get(source.getParent().toString(), File.separator, QBiCPortletUI.PORTLET_PROPERTIES_FILE_PATH);
    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
  }

  /**
   * Deletes the file {@link QBiCPortletUI#PORTLET_PROPERTIES_FILE_PATH} from the classpath, if it exists.
   */
  public static void deleteConfigFile() throws URISyntaxException, IOException {
    final URL configFileAsResource = TestUtils.class.getClassLoader().getResource(QBiCPortletUI.PORTLET_PROPERTIES_FILE_PATH);
    if (configFileAsResource != null) {
      Files.deleteIfExists(Paths.get(configFileAsResource.toURI()));
    }
  }

}
