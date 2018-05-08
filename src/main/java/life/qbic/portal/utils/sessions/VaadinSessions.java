package life.qbic.portal.utils.sessions;

import java.util.Map;

import com.vaadin.server.VaadinPortletSession;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;


/**
 * This class deals specifically with vaadin sessions
 * and returns some useful information about the session.
 * @author wojnar
 *
 */
public class VaadinSessions {

	private static VaadinRequest request;
	public static void init(){
		VaadinSession.getCurrent().getService();
		VaadinSessions.request = VaadinService.getCurrentRequest();
	}
	
	/**
	 * Checks whether the current session is a Portal session or Portal independent
	 * @return true if run in Portal environment
	 */
	public static boolean isPortalSession(){
		return (VaadinSession.getCurrent() instanceof VaadinPortletSession);
	}
	
	/**
	 * returns an attribute, if it exists
	 * @param attribute
	 * @return VaadinRequest attribute
	 */
	public static Map getAttribute(String attribute) {
		return (Map) VaadinSessions.request.getAttribute(attribute);
	}
	
	/**
	 * The absolute Path of the current base Path is returned.
	 * @return the absolute path of the location of the portlet
	 */
	public static String getCurrentBasePath(){
		return VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	}
}
