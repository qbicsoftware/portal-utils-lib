package life.qbic.portal.utils;

import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;

import life.qbic.portal.utils.sessions.VaadinSessions;
import life.qbic.portal.utils.user.UserRelated;


/**
 * LiferayAndVaadinUtils is the main class of this library. It implements most of the needed
 * functionality.
 * 
 * @author wojnar
 * 
 */
public class Utils {
  private static String LiferaySpecificAttribute = "PORTLET_ID";
  
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
            Utils.LiferaySpecificAttribute);
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
