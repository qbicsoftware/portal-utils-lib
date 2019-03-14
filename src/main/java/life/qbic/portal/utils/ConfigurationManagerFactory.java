package life.qbic.portal.utils;

import com.liferay.util.portlet.PortletProps;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import life.qbic.portal.portlet.QBiCPortletUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The ConfigurationManager Factory has only the {@link #getInstance()} method. This class maintains a static reference to an implementation of the
 * {@link ConfigurationManager}, as a sort of "proxy singleton" (developer's quotes). Please note that the term "proxy singleton" does not probably exist, but
 * it is not easy to describe this pattern.
 *
 * This odd design choice (having a factory that acts as a singleton) was chosen due to the fact that there are hard references to this class all over our
 * portlets, so changing the signature of the methods required to get an instance of a {@link ConfigurationManager}, would mean that no portlet would compile
 * if pure singletons (without a factory) are used. Such is life, I guess.
 *
 * Depending on whether the portlet runs on its own or in a Portal environment a different configuration file will be used to initialize the instance of
 * {@llink ConfigurationManager}.
 * 
 * @author wojnar, chahuistle
 * 
 */
public class ConfigurationManagerFactory {

  private static final Logger LOG = LogManager.getLogger(ConfigurationManagerFactory.class);

  // this is a "proxy singleton" (developer's quotes, I actually don't know how to name this "pattern", again, my quotes)
  private static ConfigurationManager PROXY_INSTANCE;
  /**
   * Returns the (singleton) instance of a {@link ConfigurationManager} that has been configured with the right configuration file.
   * 
   * @return Instance of {@link ConfigurationManager}.
   */
  public static synchronized ConfigurationManager getInstance() {
    if (PROXY_INSTANCE == null) {
      final Properties properties;
      // find out how we will populate the Properties object, that is, whether from a Liferay config file or from a file in the classpath
      if (PortalUtils.isLiferayPortlet()) {
        LOG.info("Running on a Liferay portal");
        // this baby is running on a portal, how exciting!
        properties = PortletProps.getProperties();
      } else {
        // load from a properties file that is in the classpath
        LOG.info("It seems that this is not a Liferay instance, reading local configuration file");
        try (final InputStream inputStream = ConfigurationManagerFactory.class.getClassLoader().getResourceAsStream(QBiCPortletUI.PORTLET_PROPERTIES_FILE_PATH)) {
          if (inputStream == null) {
            throw new FileNotFoundException("Resource " + QBiCPortletUI.PORTLET_PROPERTIES_FILE_PATH + " was not found in the classpath.");
          }
          properties = new Properties();
          properties.load(inputStream);
        } catch (IOException e) {
          throw new RuntimeException("Could not load properties from a local configuration file. Make sure that this file is found on the classpath.", e);
        }
      }
      PROXY_INSTANCE = new PropertiesBasedConfigurationManager(properties);
    }
    return PROXY_INSTANCE;
  }
}
