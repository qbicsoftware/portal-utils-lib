package life.qbic.portal.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Implements {@see ConfigurationManager}. Does not need Portal environment.
 * 
 * @author wojnar
 * 
 */

public enum LiferayIndependentConfigurationManager implements ConfigurationManager {
  Instance;
  public static final String CONFIGURATION_SUFFIX = ".configuration";
  public static final String DATASOURCE_KEY = "datasource";
  public static final String DATASOURCE_USER = "datasource.user";
  public static final String DATASOURCE_PASS = "datasource.password";
  public static final String DATASOURCE_URL = "datasource.url";
  public static final String DATASOURCE_API_URL = "datasource.api.url";

  public static final String GENOMEVIEWER_URL = "genomeviewer.url";
  public static final String GENOMEVIEWER_RESTAPI = "genomeviewer.restapi";

  public static final String TMP_FOLDER = "tmp.folder";
  public static final String SCRIPTS_FOLDER = "barcode.scripts";
  public static final String PATH_VARIABLE = "path.variable";

  public static final String PATH_TO_GUSE_WORKFLOWS = "path_to_guse_workflows";
  public static final String PATH_TO_GUSE_CERTIFICATE = "path_to_certificate";
  public static final String PATH_TO_WF_CONFIG = "path_to_wf_config";

  public static final String PATH_TO_REFERENCE_CONFIG = "path_to_reference_config";

  public static final String PATH_TO_DROPBOXES = "path_to_dropboxes";

  public static final String GUSE_REMOTE_API_URL = "guse_remoteapi_url";
  public static final String GUSE_REMOTE_API_PASS = "guse_remoteapi_password";

  public static final String ATTACHMENT_URI = "attachment.uri";
  public static final String ATTACHMENT_USER = "attachment.user";
  public static final String ATTACHMENT_PASS = "attachment.password";
  public static final String ATTACHMENT_MAX_SIZE = "max.attachment.size";

  public static final String MSQL_HOST = "mysql.host";
  public static final String MSQL_DB = "mysql.db";
  public static final String MSQL_USER = "mysql.user";
  public static final String MSQL_PORT = "mysql.port";
  public static final String MSQL_PASS = "mysql.pass";

  private String configurationFileName;
  private String dataSource;
  private String dataSourceUser;
  private String dataSourcePass;
  private String dataSourceUrl;
  private String dataSourceApiUrl;

  private String genomeViewerUrl;
  private String genomeViewerRestApi;

  private String tmpFolder;
  private String scriptsFolder;
  private String pathVariable;

  private String pathToGuseWorkflows;
  private String pathToGuseCertificate;
  private String pathToWFConfig;

  private String pathToReferenceConfig;

  private String pathToDropboxes;

  private String guseRemoteApiUrl;
  private String guseRemoteApiPass;

  private String attachmentURI;
  private String attachmentUser;
  private String attachmentPass;
  private String attachmentMaxSize;

  private String msqlHost;
  private String msqlDB;
  private String msqlUser;
  private String msqlPort;
  private String msqlPass;

  private String portletPropertiesFileName = "portlet.properties";

  private boolean initialized = false;


  public boolean isInitialized() {
    return initialized;
  }

  /**
   * init the portlet with the following properties file.
   * 
   * @param portletPropertiesFileName
   */
  public void init(String portletPropertiesFileName) {
    this.portletPropertiesFileName = portletPropertiesFileName;
    init();
  }

  public void init() {
    Properties portletConfig = new Properties();
    InputStream input = null;

    try {
      input = LiferayIndependentConfigurationManager.class.getClassLoader()
          .getResourceAsStream(portletPropertiesFileName);
      if (input == null) {
        System.out.println("Sorry, unable to find " + portletPropertiesFileName);
        return;
      }

      // load a properties file from class path, inside static method
      portletConfig.load(input);
      dataSource = portletConfig.getProperty(DATASOURCE_KEY, "openBIS");
      dataSourceUser = portletConfig.getProperty(DATASOURCE_USER);
      dataSourcePass = portletConfig.getProperty(DATASOURCE_PASS);
      dataSourceUrl = portletConfig.getProperty(DATASOURCE_URL);

      genomeViewerUrl = portletConfig.getProperty(GENOMEVIEWER_URL);
      genomeViewerRestApi = portletConfig.getProperty(GENOMEVIEWER_RESTAPI);

      tmpFolder = portletConfig.getProperty(TMP_FOLDER);
      scriptsFolder = portletConfig.getProperty(SCRIPTS_FOLDER);
      pathVariable = portletConfig.getProperty(PATH_VARIABLE);
      pathToGuseWorkflows = portletConfig.getProperty(PATH_TO_GUSE_WORKFLOWS);
      pathToGuseCertificate = portletConfig.getProperty(PATH_TO_GUSE_CERTIFICATE);
      pathToWFConfig = portletConfig.getProperty(PATH_TO_WF_CONFIG);

      pathToReferenceConfig = portletConfig.getProperty(PATH_TO_REFERENCE_CONFIG);

      pathToDropboxes = portletConfig.getProperty(PATH_TO_DROPBOXES);

      guseRemoteApiUrl = portletConfig.getProperty(GUSE_REMOTE_API_URL);
      guseRemoteApiPass = portletConfig.getProperty(GUSE_REMOTE_API_PASS);

      attachmentURI = portletConfig.getProperty(ATTACHMENT_URI);
      attachmentUser = portletConfig.getProperty(ATTACHMENT_USER);
      attachmentPass = portletConfig.getProperty(ATTACHMENT_PASS);
      attachmentMaxSize = portletConfig.getProperty(ATTACHMENT_MAX_SIZE);


    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    initialized = true;
  }

  @Override
  public String getConfigurationFileName() {
    return configurationFileName;
  }

  @Override
  public String getDataSource() {
    return dataSource;
  }

  @Override
  public String getDataSourceUser() {
    return dataSourceUser;
  }

  @Override
  public String getDataSourcePassword() {
    return dataSourcePass;
  }

  @Override
  public String getDataSourceUrl() {
    return dataSourceUrl;
  }

  @Override
  public String getDataSourceApiUrl() {
    return dataSourceApiUrl;
  }

  @Override
  public String getGenomeViewerUrl() {
    return genomeViewerUrl;
  }

  @Override
  public String getGenomeViewerRestApiUrl() {
    return genomeViewerRestApi;
  }

  @Override
  public String getBarcodeScriptsFolder() {
    return scriptsFolder;
  }

  @Override
  public String getTmpFolder() {
    return tmpFolder;
  }

  @Override
  public String getBarcodePathVariable() {
    return pathVariable;
  }

  @Override
  public String getPathToGuseWorkflows() {
    return pathToGuseWorkflows;
  }

  @Override
  public void setPathToGuseWorkflows(String pathToGuseWorkflows) {
    this.pathToGuseWorkflows = pathToGuseWorkflows;
  }

  @Override
  public String getPathToGuseCertificate() {
    return pathToGuseCertificate;
  }

  @Override
  public void setPathToGuseCertificate(String pathToGuseCertificate) {
    this.pathToGuseCertificate = pathToGuseCertificate;
  }

  @Override
  public String getPathToWFConfig() {
    return pathToWFConfig;
  }

  @Override
  public void setPathToWFConfig(String pathToWFConfig) {
    this.pathToWFConfig = pathToWFConfig;
  }

  @Override
  public String getPathToDropboxes() {
    return pathToDropboxes;
  }

  @Override
  public void setPathToDropboxes(String pathToDropboxes) {
    this.pathToDropboxes = pathToDropboxes;
  }

  @Override
  public String getGuseRemoteApiUrl() {
    return guseRemoteApiUrl;
  }

  @Override
  public void setGuseRemoteApiUrl(String guseRemoteApiUrl) {
    this.guseRemoteApiUrl = guseRemoteApiUrl;
  }

  @Override
  public String getGuseRemoteApiPass() {
    return guseRemoteApiPass;
  }

  @Override
  public void setGuseRemoteApiPass(String guseRemoteApiPass) {
    this.guseRemoteApiPass = guseRemoteApiPass;
  }

  @Override
  public String getAttachmentURI() {
    return attachmentURI;
  }

  @Override
  public String getAttachmentUser() {
    return attachmentUser;
  }

  @Override
  public String getAttachmenPassword() {
    return attachmentPass;
  }

  @Override
  public String getAttachmentMaxSize() {
    return attachmentMaxSize;
  }

  @Override
  public String getMsqlHost() {
    return msqlHost;
  }

  @Override
  public String getMysqlPort() {
    return msqlPort;
  }

  @Override
  public String getMysqlDB() {
    return msqlDB;
  }

  @Override
  public String getMysqlUser() {
    return msqlUser;
  }

  @Override
  public String getMysqlPass() {
    return msqlPass;
  }

  public String getPathToReferenceConfig() {
    return pathToReferenceConfig;
  }

  public void setPathToReferenceConfig(String pathToReferenceConfig) {
    this.pathToReferenceConfig = pathToReferenceConfig;
  }
}
