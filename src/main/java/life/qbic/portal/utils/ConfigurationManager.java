package life.qbic.portal.utils;


/**
 * The ConfigurationManger interface represents the entire .properties file. One might think about
 * adding a getAttribute method in order to make it more generic.
 * 
 * @author wojnar
 * 
 */
public interface ConfigurationManager {

  public String getConfigurationFileName();

  public String getDataSource();

  public String getDataSourceUser();

  public String getDataSourcePassword();

  public String getDataSourceUrl();

  public String getGenomeViewerUrl();

  public String getGenomeViewerRestApiUrl();

  public String getBarcodeScriptsFolder();

  public String getTmpFolder();

  public String getBarcodePathVariable();

  public String getPathToGuseWorkflows();

  public void setPathToGuseWorkflows(String pathToGuseWorkflows);

  public String getPathToGuseCertificate();

  public void setPathToGuseCertificate(String pathToGuseCertificate);

  public String getPathToWFConfig();

  public void setPathToWFConfig(String pathToWFConfig);

  public String getPathToReferenceConfig();

  public void setPathToReferenceConfig(String pathToReferenceConfig);

  public String getPathToDropboxes();

  void setPathToDropboxes(String pathToDropboxes);

  public boolean isInitialized();

  public String getGuseRemoteApiUrl();

  public void setGuseRemoteApiUrl(String guseRemoteApiUrl);

  public String getGuseRemoteApiPass();

  public void setGuseRemoteApiPass(String guseRemoteApiPass);

  public String getAttachmentURI();

  public String getAttachmentUser();

  public String getAttachmenPassword();

  public String getAttachmentMaxSize();

  public String getMsqlHost();

  public String getMysqlPort();

  public String getMysqlDB();

  public String getMysqlUser();

  public String getMysqlPass();
}
