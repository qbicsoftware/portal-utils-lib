package life.qbic.portal.portlet;

import java.io.IOException;
import java.util.Properties;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is the class from which all other QBiC portlets derive.
 */
@Theme("mytheme")
@SuppressWarnings("serial")
@Widgetset("life.qbic.portlet.AppWidgetSet")
public abstract class QBiCPortletUI extends UI {

    private static final Logger LOG = LogManager.getLogger(QBiCPortletUI.class);

    private final static String PORTLET_REPOSITORY_URL;
    private final static String PORTLET_VERSION;

    // load values from portlet.properties in a static block so all instances will have properly initialized values
    static {
        boolean cleanInit = false;
        final Properties portletProperties = new Properties();
        try {
            portletProperties.load(QBiCPortletUI.class.getClassLoader().getResourceAsStream("portlet.properties"));
            cleanInit = false;
        } catch (IOException e) {
            LOG.error("Error loading portlet.properties. Portlet will display bogus version/url values. " + 
                      "Check that portlet.properties is found under the /WEB-INF/classes folder (this file " + 
                      "is copied from src/main/resources/portlet.properties during the build).", e);
        }
        PORTLET_VERSION = cleanInit ? StringUtils.trimToEmpty(portletProperties.getProperty("version")) : "0.0.1-alpha";
        PORTLET_REPOSITORY_URL = cleanInit ? StringUtils.trimToEmpty(portletProperties.getProperty("repository.url")) : "http://github.com/qbicsoftware";
    }
    
    @Override
    protected final void init(final VaadinRequest request) {
        if (StringUtils.isBlank(PORTLET_VERSION) || StringUtils.isBlank(PORTLET_REPOSITORY_URL)) {
            LOG.error("Missing version and/or repository url properties in portlet.properties file. " +
                      "The file should contain the 'version' and 'repository.url' properties.");
        }
        LOG.info("Version " + PORTLET_VERSION);
        final VerticalLayout layout = new VerticalLayout();        
        layout.setMargin(true);
        // add the portlet
        layout.addComponent(getPortletContent(request));

        addPortletInfo(layout);
        setContent(layout);
    }

    @Override
    public final void setContent(Component content) {
        super.setContent(content);
    }

    // adds version and repository information to the passed layout
    private void addPortletInfo(final Layout layout) {
        final Label portletInfoLabel = 
        new Label("version " + PORTLET_VERSION + " (<a href=\"" + PORTLET_REPOSITORY_URL + "\">" + PORTLET_REPOSITORY_URL + "</a>)", ContentMode.HTML);
        portletInfoLabel.addStyleName("portlet-footer");
        layout.addComponent(portletInfoLabel);
    }

    /**
     * Provide the content that will be displayed.
     */
    protected abstract Layout getPortletContent(final VaadinRequest request);
}