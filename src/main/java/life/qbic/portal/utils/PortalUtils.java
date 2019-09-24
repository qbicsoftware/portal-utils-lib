package life.qbic.portal.utils;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Enumeration;
import java.util.Properties;

import com.vaadin.ui.UI;
import life.qbic.portal.utils.sessions.VaadinSessions;
import life.qbic.portal.utils.user.UserRelated;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * This is the main class of this library. It implements most of the needed
 * functionality.
 * 
 * @author wojnar
 * 
 */
public class PortalUtils {
  private static final String LiferaySpecificAttribute = "PORTLET_ID";
  private static final Logger LOG = LogManager.getLogger(PortalUtils.class);
  
  /**
   * returns the current Base path
   * 
   * @return base path of the runnnig portlet as string
   */
  public static String getCurrentBasePath() {
    return VaadinSessions.getCurrentBasePath();
  }
  
  /**
   * Returns a file stream given a content String and information about file name and extension
   * @param content file content
   * @param name file name without extension
   * @param extension file extension
   * @return
   */
  public static StreamResource getFileStream(final String content, String name, String extension) {
    StreamResource resource = new StreamResource(new StreamResource.StreamSource() {
      @Override
      public InputStream getStream() {
        try {
          InputStream is = new ByteArrayInputStream(content.getBytes());
          return is;
        } catch (Exception e) {
          e.printStackTrace();
          return null;
        }
      }
    }, name + "." + extension);
    return resource;
  }

  /**
   * if run in an Liferay environment the current user is returned. If run independendly e.g. as a
   * servlet null is returned
   * 
   * @return current Liferay user or null if no user exists.
   */
  public static com.liferay.portal.model.User getUser() {
    final String remoteUser = VaadinService.getCurrentRequest().getRemoteUser();
    return remoteUser == null ? null : UserRelated.getLiferayUser(remoteUser);
  }

  /**
   * Obtains the screen name of the currently logged user. If no user is logged-in, then {@code null} is returned.
   * @return the screen name of the currently logged user or {@code null} if there's no authenticated user.
   */
  public static String getScreenName() {
    final com.liferay.portal.model.User user = getUser();
    return user == null ? null : user.getScreenName();
  }

  /**
   * Obtains the screen name of the currently logged user. If no user is logged-in, then the string {@code Anonymous} is returned.
   * @return the screen name of the currently logged user or {@code Anonymous} if there's no authenticated user.
   */
  public static String getNonNullScreenName() {
    final String screenName = getScreenName();
    return screenName == null ? "Anonymous" : screenName;
  }

  /**
   * Checks whether it is run in a Liferay Portal environment, regardless of user authentication status.
   * 
   * @return true, if run in a Liferay Portal environement, regardless of user authentication status.
   */
  public static boolean isLiferayPortlet() {
    VaadinSession.getCurrent().getService();
    Object PORTLET_ID =
        VaadinService.getCurrentRequest().getAttribute(
            PortalUtils.LiferaySpecificAttribute);
    if (LOG.isInfoEnabled()) {
      LOG.info("PORTLET_ID = {}", PORTLET_ID);
      final Enumeration<String> attributeNames = VaadinService.getCurrentRequest().getAttributeNames();
      while (attributeNames.hasMoreElements()) {
        final String attributeName = attributeNames.nextElement();
        LOG.info("  {} = {}", attributeName, VaadinService.getCurrentRequest().getAttribute(attributeName));
      }
    }
    return PORTLET_ID != null;
  }

  /**
	 * Transforms bytes into human readable byte count eg.  110592:   110.6 kB (si = true)  108.0 KiB (si =false)
	 * @param bytes
	 * @param si
	 * @return bytes in readable count and unit
	 */
	public static String humanReadableByteCount(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}


    /**
     * Generates path to image path e.g. https://portal-testing.qbic.uni-tuebingen.de/slideshow-portlet/VAADIN/
     * @param subfolder needs to be a folder placed in VAADIN folder
     * @return path to the pictures folder of a portlet
     */
    public static String buildImagePath(String subfolder) {
        StringBuilder pathBuilder = new StringBuilder();

        if (isLiferayPortlet()) {
            Properties prop = new Properties();
            InputStream in = PortalUtils.class.getClassLoader()
                    .getResourceAsStream("WEB-INF/liferay-plugin-package.properties");
            try {
                prop.load(in);
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            String portletName = prop.getProperty("name");


            //pathBuilder.append("https://");
            URI location = UI.getCurrent().getPage().getLocation();
            // http
            pathBuilder.append(location.getScheme());
            pathBuilder.append("://");
            // host+port
            pathBuilder.append(location.getAuthority());

            String port = (Integer.toString(location.getPort()));
            if (location.toString().contains(port)) {
                pathBuilder.append(":");
                pathBuilder.append(port);
            }
            // pathBuilder.append("portal-testing.qbic.uni-tuebingen.de");
            pathBuilder.append("/");
            pathBuilder.append(portletName);
        }

        //path to images folder
        pathBuilder.append("/VAADIN/"+subfolder);
        LOG.info(pathBuilder.toString());
        return pathBuilder.toString();
    }

    /**
     *
     * @return Path to image folder with default image path {@code images}
     */
    public static String buildImagePath() {
        return buildImagePath("images");
    }

  /**
   * Builds and returns a {@link com.vaadin.ui.Layout} displaying a "please log in" message. Portlets using this method must include the mail9.png and
   * lock12.png image resources (both sized 64x64 pixels) from the {@code src/main/resources/img} folder in this project to the corresponding folder in the
   * portlet project (usually {@code src/main/webapp/VAADIN/themes/mytheme}).
   *
   * @return a {@link com.vaadin.ui.Layout} informing users to log in.
   */
  public static Layout buildNotLoggedInLayout() {
    // Mail to qbic
    ExternalResource resource = new ExternalResource("mailto:support@qbic.zendesk.com");
    Link mailToQbicLink = new Link("", resource);
    mailToQbicLink.setIcon(new ThemeResource("mail9.png"));


    ThemeDisplay themedisplay =
        (ThemeDisplay) VaadinService.getCurrentRequest().getAttribute(WebKeys.THEME_DISPLAY);

    // redirect to liferay login page
    Link loginPortalLink = new Link("", new ExternalResource(themedisplay.getURLSignIn()));
    loginPortalLink.setIcon(new ThemeResource("lock12.png"));

    // left part of the page
    VerticalLayout signIn = new VerticalLayout();
    signIn.addComponent(new Label("<h3>Sign in to manage your projects and access your data:</h3>",
        ContentMode.HTML));
    signIn.addComponent(loginPortalLink);
    signIn.setStyleName("no-user-login");
    // right part of the page
    VerticalLayout contact = new VerticalLayout();
    contact.addComponent(new Label(
        "<h3>If you are interested in doing projects get in contact:</h3>", ContentMode.HTML));
    contact.addComponent(mailToQbicLink);
    contact.setStyleName("no-user-login");

    // build final layout, with some gaps between
    HorizontalLayout notSignedInLayout = new HorizontalLayout();
    Label expandingGap1 = new Label();
    expandingGap1.setWidth("100%");
    notSignedInLayout.addComponent(expandingGap1);
    notSignedInLayout.addComponent(signIn);

    notSignedInLayout.addComponent(contact);
    notSignedInLayout.setExpandRatio(expandingGap1, 0.16f);
    notSignedInLayout.setExpandRatio(signIn, 0.36f);

    notSignedInLayout.setExpandRatio(contact, 0.36f);

    notSignedInLayout.setWidth("100%");
    notSignedInLayout.setSpacing(true);

    return notSignedInLayout;
  }
}
