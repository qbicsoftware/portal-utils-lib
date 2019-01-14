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
  public static final String ISA_CONFIG = "isa.config.folder";
  public static final String BARCODE_SCRIPTS_FOLDER = "barcode.scripts";
  public static final String BARCODE_PATH_VARIABLE = "path.variable";
  public static final String BARCODE_RESULTS = "barcode.results";

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
  
  public static final String MSQL_NCCT_DB = "mysql.ncct.db";

  public static final String METADATA_OVERWRITE_GROUP = "metadata.write.group";
  public static final String DELETION_GROUP = "UNUSEDDELETION";
  
  public static final String LABELING_METHODS = "vocabulary.ms.labeling";
  
  public static final String EPITOPE_PREDICTION_VM_HOST = "vm.epitope.host";
  public static final String EPITOPE_PREDICTION_VM_USER = "vm.samplesize.user";
  
  public static final String RNASEQ_SAMPLESIZE_CONTAINER = "vm.container.samplesize.name";
  
  public static final String RSERVE_HOST = "rserve.host";
  public static final String RSERVE_USER = "rserve.user";
  public static final String RSERVE_PORT = "rserve.port";
  public static final String RSERVE_PASS = "rserve.password";

  private String configurationFileName;
  private String dataSource;
  private String dataSourceUser;
  private String dataSourcePass;
  private String dataSourceUrl;
  private String dataSourceApiUrl;

  private String genomeViewerUrl;
  private String genomeViewerRestApi;

  private String tmpFolder;
  private String isaConfigFolder;
  private String barcodeScriptsFolder;
  private String barcodePathVariable;
  private String barcodeResultsFolder;

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
  
  private String ncctMsqlDB;
  
  private String metadataOverwriteGroup;
  private String metadataDeletionGroup;
  private String labelingMethods;
  
  private String epitopePredictionVMHost;
  private String epitopePredictionVMUser;
  private String rnaseqSampleSizeContainerName;
  private String rserveHost;
  private String rserveUser;
  private String rservePort;
  private String rservePass;

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
      isaConfigFolder = portletConfig.getProperty(ISA_CONFIG);
      barcodeScriptsFolder = portletConfig.getProperty(BARCODE_SCRIPTS_FOLDER);
      barcodePathVariable = portletConfig.getProperty(BARCODE_PATH_VARIABLE);
      barcodeResultsFolder = portletConfig.getProperty(BARCODE_RESULTS);
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

      msqlHost = portletConfig.getProperty(MSQL_HOST);
      msqlDB = portletConfig.getProperty(MSQL_DB);
      msqlUser = portletConfig.getProperty(MSQL_USER);
      msqlPort = portletConfig.getProperty(MSQL_PORT);
      msqlPass = portletConfig.getProperty(MSQL_PASS);
      
      ncctMsqlDB = portletConfig.getProperty(MSQL_NCCT_DB);

      metadataOverwriteGroup = portletConfig.getProperty(METADATA_OVERWRITE_GROUP);
//      metadataDeletionGroup = portletConfig.getProperty(metadataDeletionGroup);
      labelingMethods = portletConfig.getProperty(LABELING_METHODS);
      
      epitopePredictionVMHost = portletConfig.getProperty(EPITOPE_PREDICTION_VM_HOST);
      epitopePredictionVMUser = portletConfig.getProperty(EPITOPE_PREDICTION_VM_USER);
      rnaseqSampleSizeContainerName = portletConfig.getProperty(RNASEQ_SAMPLESIZE_CONTAINER);
      rserveHost = portletConfig.getProperty(RSERVE_HOST);
      rservePort = portletConfig.getProperty(RSERVE_PORT);
      rserveUser = portletConfig.getProperty(RSERVE_USER);
      rservePass = portletConfig.getProperty(RSERVE_PASS);

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
    return barcodeScriptsFolder;
  }

  @Override
  public String getTmpFolder() {
    return tmpFolder;
  }

  @Override
  public String getBarcodePathVariable() {
    return barcodePathVariable;
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
  public String getMysqlHost() {
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
  
  @Override
  public String getVocabularyMSLabeling() {
    return labelingMethods;
  }

  @Override
  public String getBarcodeResultsFolder() {
    return barcodeResultsFolder;
  }

  @Override
  public String getMetadataWriteGrp() {
    return metadataOverwriteGroup;
  }

//  @Override
//  public String getDeletionGrp() {
//    // TODO Auto-generated method stub
//    return null;
//  }

  @Override
  public String getISAConfigPath() {
    return isaConfigFolder;
  }

  @Override
  public String getEpitopeAndSampleSizeVMHost() {
    return epitopePredictionVMHost;
  }

  @Override
  public String getSampleSizeVMUser() {
    return epitopePredictionVMUser;
  }

  @Override
  public String getRServePort() {
    return rservePort;
  }

  @Override
  public String getRNASeqSampleSizeContainerName() {
    return rnaseqSampleSizeContainerName;
  }
  
  @Override
  public String getRServeHost() {
    return rserveHost;
  }
  
  @Override
  public String getRServeUser() {
    return rserveUser;
  }

  @Override
  public String getRServePassword() {
    return rservePass;
  }

  @Override
  public String getNCCTMysqlDB() {
    return ncctMsqlDB;
  }

}
