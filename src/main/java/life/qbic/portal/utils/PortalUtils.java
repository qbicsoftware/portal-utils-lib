package life.qbic.portal.utils;

import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;

import java.util.Enumeration;
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
   * if run in an Liferay environment the current user is returned. If run independendly e.g. as a
   * servlet null is returned
   * 
   * @return current Liferay user or null if no user exists.
   */
  public static com.liferay.portal.model.User getUser() {
	VaadinSession.getCurrent().getService();
    String remoteuser = VaadinService.getCurrentRequest().getRemoteUser();

    if (remoteuser == null) {
      return null;
    }
    return UserRelated.getLiferayUser(remoteuser);
  }

  /**
   * Checks whether it is run in a Liferay Portal environment.
   * 
   * @return true, if run in a Liferay Portal environement.
   */
  public static boolean isLiferayPortlet() {
    VaadinSession.getCurrent().getService();
    String remoteuser = VaadinService.getCurrentRequest().getRemoteUser();
    Object PORTLET_ID =
        VaadinService.getCurrentRequest().getAttribute(
            PortalUtils.LiferaySpecificAttribute);
    if (LOG.isInfoEnabled()) {
      LOG.info("remoteUser = {}, PORTLET_ID = {}", remoteuser, PORTLET_ID);
      final Enumeration<String> attributeNames = VaadinService.getCurrentRequest().getAttributeNames();
      while (attributeNames.hasMoreElements()) {
        final String attributeName = attributeNames.nextElement();
        LOG.info("  {} = {}", attributeName, VaadinService.getCurrentRequest().getAttribute(attributeName));
      }
    }
    return remoteuser != null && PORTLET_ID != null;
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

}
