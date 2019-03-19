package life.qbic.portal.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.reflect.Whitebox;

/**
 * Tests for {@link PropertiesBasedConfigurationManager}.
 */
public class PropertiesBasedConfigurationManagerTest {

  @Mock
  private Logger mockLogger;

  // this rule is needed to use Whitebox to set the static "LOG" variable
  @Rule
  public MockitoRule rule = MockitoJUnit.rule();

  private Properties properties;

  @BeforeClass
  public static void loggerSetUp() {
    System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
    System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
  }

  @Before
  public void setUpTest() {
    // inject mock logger
    Whitebox.setInternalState(PropertiesBasedConfigurationManager.class, "LOG", mockLogger);
    properties = new Properties();
  }

  @Test
  public void testAllowUnauthenticatedAccessNormal() {
    properties.setProperty(PropertiesBasedConfigurationManager.ALLOW_UNAUTHENTICATED_ACCESS_PREFIX + "foo", "true");
    properties.setProperty(PropertiesBasedConfigurationManager.ALLOW_UNAUTHENTICATED_ACCESS_PREFIX + "bar", "false");
    properties.setProperty(PropertiesBasedConfigurationManager.ALLOW_UNAUTHENTICATED_ACCESS_PREFIX + "foobar", "not true");
    final ConfigurationManager configurationManager = new PropertiesBasedConfigurationManager(properties);

    assertTrue(configurationManager.isAllowUnauthenticatedAccess("foo"));
    assertFalse(configurationManager.isAllowUnauthenticatedAccess("bar"));
    assertFalse(configurationManager.isAllowUnauthenticatedAccess("foobar"));
  }

  @Test
  public void testAllowUnauthenticatedAccessWeirdTyping() {
    properties.setProperty(PropertiesBasedConfigurationManager.ALLOW_UNAUTHENTICATED_ACCESS_PREFIX + "foo", "True");
    properties.setProperty(PropertiesBasedConfigurationManager.ALLOW_UNAUTHENTICATED_ACCESS_PREFIX + "bar", "TRUE");
    properties.setProperty(PropertiesBasedConfigurationManager.ALLOW_UNAUTHENTICATED_ACCESS_PREFIX + "foobar", "tRuE");
    properties.setProperty(PropertiesBasedConfigurationManager.ALLOW_UNAUTHENTICATED_ACCESS_PREFIX + "barfoo", "  trUe   ");
    final ConfigurationManager configurationManager = new PropertiesBasedConfigurationManager(properties);

    assertTrue(configurationManager.isAllowUnauthenticatedAccess("foo"));
    assertTrue(configurationManager.isAllowUnauthenticatedAccess("bar"));
    assertTrue(configurationManager.isAllowUnauthenticatedAccess("foobar"));
    assertTrue(configurationManager.isAllowUnauthenticatedAccess("barfoo"));

  }

  @Test
  public void testAllowUnauthenticatedAccessMissingFromPropertiesFile() {
    properties.setProperty(PropertiesBasedConfigurationManager.ALLOW_UNAUTHENTICATED_ACCESS_PREFIX + "foo", "true");
    final ConfigurationManager configurationManager = new PropertiesBasedConfigurationManager(properties);

    assertFalse(configurationManager.isAllowUnauthenticatedAccess("bar"));
  }

  @Test
  public void testAllowUnauthenticatedAccessInvalidPropertyName() {
    properties.setProperty(PropertiesBasedConfigurationManager.ALLOW_UNAUTHENTICATED_ACCESS_PREFIX + "foo", "true");
    properties.setProperty(PropertiesBasedConfigurationManager.ALLOW_UNAUTHENTICATED_ACCESS_PREFIX, "true");
    final ConfigurationManager configurationManager = new PropertiesBasedConfigurationManager(properties);

    assertTrue(configurationManager.isAllowUnauthenticatedAccess("foo"));
    Mockito.verify(mockLogger).warn(ArgumentMatchers.contains("Invalid property id"));
  }

  @Test
  public void testStatisticsFilePathMissing() {
    final ConfigurationManager configurationManager = new PropertiesBasedConfigurationManager(properties);

    assertNull(configurationManager.getStatisticsFilePath());
  }

  @Test
  public void testStatisticsFilePath() {
    properties.setProperty(PropertiesBasedConfigurationManager.STATISTICS_FILE_PATH, "/bar/foo/test.yaml");
    final ConfigurationManager configurationManager = new PropertiesBasedConfigurationManager(properties);

    assertEquals("/bar/foo/test.yaml", configurationManager.getStatisticsFilePath());
  }
}
