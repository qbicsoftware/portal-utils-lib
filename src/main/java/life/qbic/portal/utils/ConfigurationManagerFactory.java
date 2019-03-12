package life.qbic.portal.utils;

import com.liferay.util.portlet.PortletProps;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
        // this baby is running on a portal, how exciting!
        properties = PortletProps.getProperties();
      } else {
        // load from a properties file that is in the classpath
        try (final InputStream inputStream = ConfigurationManagerFactory.class.getClassLoader().getResourceAsStream("portlet.properties")) {
          properties = new Properties();
          properties.load(inputStream);
        } catch (IOException e) {
          throw new RuntimeException("Could not load properties from portlet.properties file. Make sure that this file is found on the classpath.", e);
        }
      }
      PROXY_INSTANCE = new PropertiesBasedConfigurationManager(properties);
    }
    return PROXY_INSTANCE;
  }
}
