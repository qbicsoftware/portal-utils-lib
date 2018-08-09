package life.qbic.portal.utils;


/**
 * The ConfigurationManger interface represents the entire .properties file. One might think about
 * adding a getAttribute method in order to make it more generic.
 * 
 * @author wojnar
 * 
 */
public interface ConfigurationManager {

  String getConfigurationFileName();

  String getDataSource();

  String getDataSourceUser();

  String getDataSourcePassword();

  /**
   * @return the openBIS URL to be used by a client (e.g., openBIS client).
   */
  String getDataSourceUrl();

  @Deprecated
  /**
   * @return the openBIS URL for direct API access.
   */
  String getDataSourceApiUrl();

  String getGenomeViewerUrl();

  String getGenomeViewerRestApiUrl();

  String getBarcodeScriptsFolder();

  String getTmpFolder();

  String getBarcodePathVariable();

  String getPathToGuseWorkflows();

  void setPathToGuseWorkflows(String pathToGuseWorkflows);

  String getPathToGuseCertificate();

  void setPathToGuseCertificate(String pathToGuseCertificate);

  String getPathToWFConfig();

  void setPathToWFConfig(String pathToWFConfig);

  String getPathToReferenceConfig();

  void setPathToReferenceConfig(String pathToReferenceConfig);

  String getPathToDropboxes();

  void setPathToDropboxes(String pathToDropboxes);

  boolean isInitialized();

  String getGuseRemoteApiUrl();

  void setGuseRemoteApiUrl(String guseRemoteApiUrl);

  String getGuseRemoteApiPass();

  void setGuseRemoteApiPass(String guseRemoteApiPass);

  String getAttachmentURI();

  String getAttachmentUser();

  String getAttachmenPassword();

  String getAttachmentMaxSize();

  String getMsqlHost();

  String getMysqlPort();

  String getMysqlDB();

  String getMysqlUser();

  String getMysqlPass();
}
