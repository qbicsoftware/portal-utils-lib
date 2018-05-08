package life.qbic.portal.utils.user;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;
//import com.liferay.portal.service.UserServiceUtil;

/**
 * This class has liferay related user functionality. Use only in Portal environment
 * @author wojnar
 *
 */
public class UserRelated {
	
	
	/**
	 * returns the current user for the given liferay internal user id.
	 * @param liferay_user_id
	 * @return liferay user
	 */
	public static com.liferay.portal.model.User getLiferayUser(String liferay_user_id){
    	//explode city gots to figure out how to deal with it not being in the map
    	//String locationId = (String) userInfoMap.get("liferay.location.id");
    	//String companyId = (String) userInfoMap.get("liferay.company.id");
	    try {
	    	//String userName = (String) userInfoMap.get("liferay.user.id");
	    	return UserLocalServiceUtil.getUser(Long.parseLong(liferay_user_id)); 
			//com.liferay.portal.model.User currentLocalUser = UserServiceUtil.getUserById(Long.parseLong(userName));
			//currentLocalUser = UserLocalServiceUtil.getUser(Long.parseLong(userName));
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
}
