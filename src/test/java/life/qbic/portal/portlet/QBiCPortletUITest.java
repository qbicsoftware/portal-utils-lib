package life.qbic.portal.portlet;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import life.qbic.portal.TestUtils;
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
 * Tests for QBiCPortletUI.
 */
public class QBiCPortletUITest {

  private final static Layout TEST_CONTENT = new VerticalLayout();

  @Mock
  private VaadinRequest mockRequest;
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
  public void setUpTest() throws Exception {
    // inject mock logger
    Whitebox.setInternalState(QBiCPortletUI.class, "LOG", mockLogger);
    // make sure that there's no portlet.properties in the classpath
    TestUtils.deletePropertiesFile();
  }

  @Test
  public void testMissingVersion() throws IOException, URISyntaxException {
    // we expect a clean init, but some warning/error should be logged
    TestUtils.copyPropertiesFrom("portlet.properties_no_version");

    final UI mockUI = new MockUI();
    mockUI.doInit(mockRequest, 1, "test");

    Mockito.verify(mockLogger)
        .error(ArgumentMatchers.contains("Missing version and/or repository url"));
    assertVersion(mockUI, QBiCPortletUI.DEFAULT_VERSION);
    assertRepoURL(mockUI, "TEST-VALUE-NO-VERSION");
  }

  @Test
  public void testMissingRepositoryUrl() throws IOException, URISyntaxException {
    // we expect a clean init, but some warning/error should be logged
    TestUtils.copyPropertiesFrom("portlet.properties_no_repo");

    final UI mockUI = new MockUI();
    mockUI.doInit(mockRequest, 1, "test");

    Mockito.verify(mockLogger)
        .error(ArgumentMatchers.contains("Missing version and/or repository url"));
    assertVersion(mockUI, "1.2.3-TEST-NOREPO");
    assertRepoURL(mockUI, QBiCPortletUI.DEFAULT_REPO);
  }

  @Test
  public void testEmptyPortletProperties() throws URISyntaxException, IOException {
    // we expect a clean init, but some warning/error should be logged
    TestUtils.copyPropertiesFrom("portlet.properties_nothing");

    final UI mockUI = new MockUI();
    mockUI.doInit(mockRequest, 1, "test");

    Mockito.verify(mockLogger)
        .error(ArgumentMatchers.contains("Missing version and/or repository url"));
    assertVersion(mockUI, QBiCPortletUI.DEFAULT_VERSION);
    assertRepoURL(mockUI, QBiCPortletUI.DEFAULT_REPO);
  }

  @Test
  public void testMissingPortletProperties() throws IOException, URISyntaxException {
    // we expect a clean init, but some warning/error should be logged with an exception (for the stacktrace)
    TestUtils.deletePropertiesFile();

    final UI mockUI = new MockUI();
    mockUI.doInit(mockRequest, 1, "test");

    Mockito.verify(mockLogger).error(ArgumentMatchers.contains("Error loading portlet.properties."),
        ArgumentMatchers.isA(Throwable.class));
    assertVersion(mockUI, QBiCPortletUI.DEFAULT_VERSION);
    assertRepoURL(mockUI, QBiCPortletUI.DEFAULT_REPO);
  }

  @Test
  public void testInfoLabelContainsProperValues() throws URISyntaxException, IOException {
    // happy path
    TestUtils.copyPropertiesFrom("portlet.properties_good");

    final UI mockUI = new MockUI();
    mockUI.doInit(mockRequest, 1, "test");

    Mockito.verify(mockLogger, Mockito.never()).error(Mockito.anyString());

    assertVersion(mockUI, "1.2.3-TEST");
    assertRepoURL(mockUI, "TEST-VALUE");
  }

  @Test
  public void testPortletInfoIsFooter() throws URISyntaxException, IOException {
    // we expect portlet information to be on a footer
    TestUtils.copyPropertiesFrom("portlet.properties_good");

    final UI mockUI = new MockUI();
    mockUI.doInit(mockRequest, 1, "test");

    final Label infoLabel = getInfoLabel(mockUI);

    assertThat(infoLabel.getStyleName(), equalTo(QBiCPortletUI.INFO_LABEL_CLASS));
  }

  @Test
  public void testContentIsAdded() throws URISyntaxException, IOException {
    // we expect any content the child class adds to be present
    TestUtils.copyPropertiesFrom("portlet.properties_good");

    final UI mockUI = new MockUI();
    mockUI.doInit(mockRequest, 1, "test");

    for (final Component component : (Layout) mockUI.getContent()) {
      if (component == TEST_CONTENT) {
        return;
      }
    }
    fail("Content was not added.");
  }

  @Test(expected = NullPointerException.class)
  public void testNullContent() throws URISyntaxException, IOException {
    // we expect this to fail with a loud exception
    TestUtils.copyPropertiesFrom("portlet.properties_good");

    final UI mockUI = new MockUI();
    ((MockUI) mockUI).useNullContent = true;
    mockUI.doInit(mockRequest, 1, "test");
  }

  // ========== support methods/classes ============
  private void assertVersion(final UI ui, final String version) {
    final Label infoLabel = getInfoLabel(ui);
    assertThat(infoLabel.getValue(), containsString("version " + version + " (<a"));
  }

  private void assertRepoURL(final UI ui, final String repoURL) {
    final Label infoLabel = getInfoLabel(ui);
    assertThat(infoLabel.getValue(), containsString(">" + repoURL + "</a>"));
  }

  private Label getInfoLabel(final UI ui) {
    return getLabel(ui, QBiCPortletUI.INFO_LABEL_ID);
  }

  private Label getLabel(final UI ui, final String id) {
    for (final Component component : (Layout) ui.getContent()) {
      if (id.equals(component.getId())) {
        return (Label) component;
      }
    }
    throw new RuntimeException("label with id " + id + " not found in QBiCPortletUI");
  }

  @SuppressWarnings("serial")
  private static class MockUI extends QBiCPortletUI {

    volatile boolean useNullContent = false;

    @Override
    protected Layout getPortletContent(final VaadinRequest request) {
      if (useNullContent) {
        return null;
      }
      return TEST_CONTENT;
    }
  }

}
