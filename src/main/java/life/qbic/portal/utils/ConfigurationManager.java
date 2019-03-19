package life.qbic.portal.utils;

import java.util.List;

/**
 * The ConfigurationManger interface represents the entire .properties file. One might think about
 * adding a getAttribute method in order to make it more generic.
 * 
 * @author wojnar
 * 
 */
public interface ConfigurationManager {

  @Deprecated
  /**
   * @deprecated This property was never loaded, neither by the already removed LiferayConfigurarionManager nor by LiferayIndependentConfigurationManager,
   * perhaps this method is not used at all.
   */
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

  String getGuseRemoteApiUrl();

  void setGuseRemoteApiUrl(String guseRemoteApiUrl);

  String getGuseRemoteApiPass();

  void setGuseRemoteApiPass(String guseRemoteApiPass);

  String getAttachmentURI();

  String getAttachmentUser();

  String getAttachmenPassword();

  String getAttachmentMaxSize();

  String getMysqlHost();

  String getMysqlPort();

  String getMysqlDB();
  
  String getNCCTMysqlDB();

  String getMysqlUser();

  String getMysqlPass();
  
  String getVocabularyMSLabeling();

  String getBarcodeResultsFolder();

  String getMetadataWriteGrp();

//  String getDeletionGrp();

  String getISAConfigPath();
  
  String getEpitopeAndSampleSizeVMHost();
  
  String getSampleSizeVMUser();
  
  String getRServePort();
  
  String getRNASeqSampleSizeContainerName();
  
  String getRServeUser();
  
  String getRServeHost();
  
  String getRServePassword();
  
  List<String> getUserDBInputUserGrps();

  List<String> getUserDBInputAdminGrps();

  /**
   * Determines if the given content, identified by a user-generated {@code contentId}, has been configured to display content for unauthenticated users.
   * For security and backwards compatibility purposes, implementations should use {@code false} as a default value in case a configuration is missing.
   *
   * @param contentId The id of the content in question. This id can be any non-blank string and gives developers the flexibility to define several content
   *                  identifiers for the same portlet.
   * @return {@code true} if the content identified by the given {@code contentId} can be displayed for unauthenticated users.
   */
  boolean isAllowUnauthenticatedAccess(final String contentId);

  /**
   * Path of the file containing statistics for {@link statistics-portlet}. This is is typically the output of the {@code statistics-cli} tool.
   * @return the path where the statistics file is located.
   */
  String getStatisticsFilePath();
}
