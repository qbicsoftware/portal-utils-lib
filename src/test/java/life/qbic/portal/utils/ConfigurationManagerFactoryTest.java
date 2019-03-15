package life.qbic.portal.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import com.liferay.util.portlet.PortletProps;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Properties;

import life.qbic.portal.TestUtils;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;


/**
 * Tests for {@link ConfigurationManagerFactory}
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PortalUtils.class, PortletProps.class})
@PowerMockIgnore("javax.management.*")
@SuppressStaticInitializationFor("com.liferay.util.portlet.PortletProps")
public class ConfigurationManagerFactoryTest {

  private Properties mockPortalProperties;

  @Mock
  private Logger mockLogger;

  @Before
  public void setUp() throws IOException, URISyntaxException {
    mockPortalProperties = new Properties();
    // force ConfigurationManagerFactory to be uninitialized before every test is executed
    Whitebox.setInternalState(ConfigurationManagerFactory.class, "PROXY_INSTANCE", (ConfigurationManager)null);
    Whitebox.setInternalState(ConfigurationManagerFactory.class, "LOG", mockLogger);
    // make sure there's no dveloper.properties in the path
    TestUtils.deleteDeveloperPropertiesFile();
  }

  @After
  public void cleanUp()  {
    TestUtils.deleteDeveloperPropertiesFile();
  }

  @Test
  public void testPortalEnvironment() {
    // set one property to test if the portal properties are used
    mockPortalProperties.setProperty("mysql.user", "Mr. Squirrel");
    PowerMockito.mockStatic(PortalUtils.class);
    PowerMockito.mockStatic(PortletProps.class);

    when(PortalUtils.isLiferayPortlet()).thenReturn(true);
    when(PortletProps.getProperties()).thenReturn(mockPortalProperties);

    assertEquals("Mr. Squirrel", ConfigurationManagerFactory.getInstance().getMysqlUser());
  }

  @Test
  public void testLocalEnvironment() throws Exception {
    TestUtils.copyDeveloperPropertiesFrom("developer.properties_good");
    PowerMockito.mockStatic(PortalUtils.class);
    when(PortalUtils.isLiferayPortlet()).thenReturn(false);

    assertEquals("Mr. Mister", ConfigurationManagerFactory.getInstance().getMysqlUser());
  }

  @Test
  public void testLocalConfigFileNotFound() throws Exception {
    PowerMockito.mockStatic(PortalUtils.class);
    when(PortalUtils.isLiferayPortlet()).thenReturn(false);

    ConfigurationManagerFactory.getInstance();

    Mockito.verify(mockLogger).warn(ArgumentMatchers.contains("local configuration file was not found in the classpath"), ArgumentMatchers.anyString());
  }
}
