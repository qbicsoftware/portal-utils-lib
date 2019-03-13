package life.qbic.portal.utils;

import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.mockito.Mock;
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

  @BeforeClass
  public static void loggerSetUp() {
    System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
    System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
  }

  @Before
  public void setUpTest() {
    // inject mock logger
    Whitebox.setInternalState(PropertiesBasedConfigurationManager.class, "LOG", mockLogger);
  }
}
