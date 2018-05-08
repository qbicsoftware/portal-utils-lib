package life.qbic.portal.utils.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.RemotePreference;
import com.liferay.portal.model.*;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.model.ExpandoBridge;


/**
 * This class can be used for testing purposes, if a portlet is run in serlvet mode and not in the portal environment
 * For method docuemntation see com.liferay.portal.model.User
 * MOST OF THE FUNCTIONS ARE NOT IMPLEMENTED YET. THEY RETURN NULL OR 0 OR FALSE.
 * @author wojnar
 *
 */
public class LiferayUserDummy implements User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String screenName;

	/**
	 * TODO
	 */
	@Override
	public int compareTo(User arg0) {
		return 0;
	}
	/**
	 * TODO
	 */
	public Object clone() { return null; }
	
	/**
	 * TODO
	 */
	@Override
	public boolean getAgreedToTermsOfUse() {
		return false;
	}

	/**
	 * TODO
	 */
	@Override
	public String getComments() {
		return null;
	}

	/**
	 * TODO
	 */
	@Override
	public long getCompanyId() {
		return 0;
	}

	/**
	 * TODO
	 */
	@Override
	public long getContactId() {
		return 0;
	}

	/**
	 * TODO
	 */
	@Override
	public Date getCreateDate() {
		return null;
	}

	/**
	 * TODO
	 */
	@Override
	public boolean getDefaultUser() {
		return false;
	}

	/**
	 * TODO
	 */
	@Override
	public String getEmailAddress() {
		return null;
		
	}
	/**
	 * TODO
	 */
	@Override
	public boolean getEmailAddressVerified() {
		return false;
	}
	
	/**
	 * TODO
	 */
	@Override
	public ExpandoBridge getExpandoBridge() {
		return null;
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		return null;
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {

	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {

	}

	/**
	 * TODO
	 */
	@Override
	public long getFacebookId() {
		return 0;
	}
	
	/**
	 * TODO
	 */
	@Override
	public int getFailedLoginAttempts() {
		return 0;
	}
	/**
	 * TODO
	 */
	@Override
	public String getFirstName() {
		return null;
	}
	/**
	 * TODO
	 */
	@Override
	public int getGraceLoginCount() {
		return 0;
	}
	/**
	 * TODO
	 */
	@Override
	public String getGreeting() {
		return null;
	}
	/**
	 * TODO
	 */
	@Override
	public String getJobTitle() {
		return null;
	}
	/**
	 * TODO
	 */
	@Override
	public String getLanguageId() {
		return null;
	}
	/**
	 * TODO
	 */
	@Override
	public Date getLastFailedLoginDate() {
		return null;
	}

	@Override
	public Date getLastLoginDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastLoginIP() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getLockout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date getLockoutDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getLoginDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLoginIP() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMiddleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return null;
	}

	@Override
	public String getOpenId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getPasswordEncrypted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date getPasswordModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getPasswordReset() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getPortraitId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getPrimaryKey() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReminderQueryAnswer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReminderQueryQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return this.screenName;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTimeZoneId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUserUuid() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAgreedToTermsOfUse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCachedModel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDefaultUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailAddressVerified() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEscapedModel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLockout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPasswordEncrypted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPasswordReset() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAgreedToTermsOfUse(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCachedModel(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setComments(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCompanyId(long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContactId(long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCreateDate(Date arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDefaultUser(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDigest(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEmailAddress(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEmailAddressVerified(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setModelAttributes(Map<String, Object> map) {

	}

	@Override
	public void setFacebookId(long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getLdapServerId() {
		return 0;
	}

	@Override
	public void setLdapServerId(long l) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setFailedLoginAttempts(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFirstName(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGraceLoginCount(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGreeting(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJobTitle(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLastFailedLoginDate(Date arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLastLoginDate(Date arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLastLoginIP(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLastName(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLockout(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLockoutDate(Date arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginDate(Date arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginIP(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMiddleName(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setModifiedDate(Date arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNew(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOpenId(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPassword(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPasswordEncrypted(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPasswordModifiedDate(Date arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPasswordReset(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPortraitId(long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPrimaryKey(long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPrimaryKeyObj(Serializable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setReminderQueryAnswer(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setReminderQueryQuestion(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setScreenName(String arg0) {
		// TODO Auto-generated method stub
		this.screenName = arg0;
	}

	@Override
	public void setStatus(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserId(long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserUuid(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUuid(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public CacheModel<User> toCacheModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User toEscapedModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User toUnescapedModel() {
		return null;
	}

	@Override
	public String toXmlString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetOriginalValues() {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getModelClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getModelClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist() throws SystemException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRemotePreference(RemotePreference remotePreference) {
	}

	@Override
	public List<Address> getAddresses() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getBirthday() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCompanyMx() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact getContact() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDigest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDigest(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayEmailAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayURL(ThemeDisplay arg0) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayURL(ThemeDisplay themeDisplay, boolean b) throws PortalException, SystemException {
		return null;
	}

	@Override
	public List<EmailAddress> getEmailAddresses() throws SystemException {
		return null;
	}

	@Override
	public String getDisplayURL(String arg0, String arg1)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayURL(String s, String s1, boolean b) throws PortalException, SystemException {
		return null;
	}

	@Override
	public boolean getFemale() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group getGroup() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getGroupId() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long[] getGroupIds() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getGroups() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogin() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getMale() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Group> getMySiteGroups() throws PortalException, SystemException {
		return null;
	}

	@Override
	public List<Group> getMySiteGroups(boolean b, int i) throws PortalException, SystemException {
		return null;
	}

	@Override
	public List<Group> getMySiteGroups(int i) throws PortalException, SystemException {
		return null;
	}

	@Override
	public List<Group> getMySiteGroups(String[] strings, boolean b, int i) throws PortalException, SystemException {
		return null;
	}

	@Override
	public List<Group> getMySiteGroups(String[] strings, int i) throws PortalException, SystemException {
		return null;
	}

	@Override
	public List<Group> getMySites() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getMySites(int arg0) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getMySites(boolean arg0, int arg1)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getMySites(String[] arg0, int arg1)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getMySites(String[] arg0, boolean arg1, int arg2)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long[] getOrganizationIds() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long[] getOrganizationIds(boolean b) throws PortalException, SystemException {
		return new long[0];
	}

	@Override
	public List<Organization> getOrganizations() throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Organization> getOrganizations(boolean b) throws PortalException, SystemException {
		return null;
	}

	@Override
	public boolean getPasswordModified() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PasswordPolicy getPasswordPolicy() throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPasswordUnencrypted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Phone> getPhones() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPortraitURL(ThemeDisplay arg0) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPrivateLayoutsPageCount() throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPublicLayoutsPageCount() throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<String> getReminderQueryQuestions() throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RemotePreference getRemotePreference(String s) {
		return null;
	}

	@Override
	public Iterable<RemotePreference> getRemotePreferences() {
		return null;
	}

	@Override
	public long[] getRoleIds() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getRoles() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getSiteGroups() throws PortalException, SystemException {
		return null;
	}

	@Override
	public List<Group> getSiteGroups(boolean b) throws PortalException, SystemException {
		return null;
	}

	@Override
	public long[] getTeamIds() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Team> getTeams() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimeZone getTimeZone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long[] getUserGroupIds() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserGroup> getUserGroups() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Website> getWebsites() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCompanyMx() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasCompanyMx(String arg0) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasMySites() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasOrganization() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPrivateLayouts() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPublicLayouts() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasReminderQuery() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailAddressComplete() {
		return false;
	}

	@Override
	public boolean isEmailAddressVerificationComplete() {
		return false;
	}

	@Override
	public boolean isFemale() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMale() throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPasswordModified() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReminderQueryComplete() {
		return false;
	}

	@Override
	public boolean isSetupComplete() {
		return false;
	}

	@Override
	public boolean isTermsOfUseComplete() {
		return false;
	}

	@Override
	public void setLanguageId(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPasswordModified(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPasswordUnencrypted(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTimeZoneId(String arg0) {
		// TODO Auto-generated method stub

	}

}
