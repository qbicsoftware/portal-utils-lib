package life.qbic.portal.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import life.qbic.datamodel.services.ServiceUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Implementation of the {@link ConfigurationManager} based on {@link java.util.Properties}. See
 * {@link ConfigurationManagerFactory} for an explanation on why this is not a singleton.
 */
public class PropertiesBasedConfigurationManager implements ConfigurationManager {

  static final Logger LOG = LogManager.getLogger(PropertiesBasedConfigurationManager.class);

  // names of the properties (set with package access so the tests can see these constants
  static final String DATASOURCE_KEY = "datasource";
  static final String DATASOURCE_USER = "datasource.user";
  static final String DATASOURCE_PASS = "datasource.password";
  static final String DATASOURCE_URL = "datasource.url";
  static final String DATASOURCE_API_URL = "datasource.api.url";

  static final String OMERO_HOST = "omero.host";
  static final String OMERO_PORT = "omero.port";
  static final String OMERO_USER = "omero.user";
  static final String OMERO_PASS = "omero.password";

  static final String GENOMEVIEWER_URL = "genomeviewer.url";
  static final String GENOMEVIEWER_RESTAPI = "genomeviewer.restapi";

  static final String TMP_FOLDER = "tmp.folder";
  static final String ISA_CONFIG = "isa.config.folder";
  static final String BARCODE_SCRIPTS_FOLDER = "barcode.scripts";
  static final String BARCODE_PATH_VARIABLE = "path.variable";
  static final String BARCODE_RESULTS = "barcode.results";

  static final String PATH_TO_GUSE_WORKFLOWS = "path_to_guse_workflows";
  static final String PATH_TO_GUSE_CERTIFICATE = "path_to_certificate";
  static final String PATH_TO_WF_CONFIG = "path_to_wf_config";

  static final String PATH_TO_REFERENCE_CONFIG = "path_to_reference_config";

  static final String PATH_TO_DROPBOXES = "path_to_dropboxes";

  static final String GUSE_REMOTE_API_URL = "guse_remoteapi_url";
  static final String GUSE_REMOTE_API_PASS = "guse_remoteapi_password";

  static final String ATTACHMENT_URI = "attachment.uri";
  static final String ATTACHMENT_USER = "attachment.user";
  static final String ATTACHMENT_PASS = "attachment.password";
  static final String ATTACHMENT_MAX_SIZE = "max.attachment.size";

  static final String MSQL_HOST = "mysql.host";
  static final String MSQL_DB = "mysql.db";
  static final String MSQL_USER = "mysql.user";
  static final String MSQL_PORT = "mysql.port";
  static final String MSQL_PASS = "mysql.pass";

  static final String DB_INPUT_USER_GROUPS = "mysql.input.usergrp";
  static final String DB_INPUT_ADMIN_GROUPS = "mysql.input.admingrp";

  static final String MSQL_NCCT_DB = "mysql.ncct.db";

  static final String METADATA_OVERWRITE_GROUP = "metadata.write.group";
  static final String DELETION_GROUP = "UNUSEDDELETION";

  static final String LABELING_METHODS = "vocabulary.ms.labeling";

  static final String EPITOPE_PREDICTION_VM_HOST = "vm.epitope.host";
  static final String EPITOPE_PREDICTION_VM_USER = "vm.samplesize.user";

  static final String RNASEQ_SAMPLESIZE_CONTAINER = "vm.container.samplesize.name";

  static final String RSERVE_HOST = "rserve.host";
  static final String RSERVE_USER = "rserve.user";
  static final String RSERVE_PORT = "rserve.port";
  static final String RSERVE_PASS = "rserve.password";

  static final String STATISTICS_FILE_PATH = "statistics.file.path";

  static final String SERVICES_REGISTRY_URL = "services.registry.url";

  static final String SAMPLE_TRACKING_SERVICE_URL = "services.sampletracking.url";

  static final String SERVICE_USER_NAME = "services.user.name";
  static final String SERVICE_USER_PASSWORD = "services.user.password";

  static final String SERVICES_SUBSCRIPTION_URL = "services.subscription.url";
  // in the properties file we expect properties defined as follows:
  // access.unauthenticated.foo = false
  // access.unauthenticated.bar = true
  // where foo/bar are content identifiers
  static final String ALLOW_UNAUTHENTICATED_ACCESS_PREFIX = "access.unauthenticated.";

  // member variables
  private String dataSource;
  private String dataSourceUser;
  private String dataSourcePass;
  private String dataSourceUrl;
  private String dataSourceApiUrl;

  private String omeroHost;
  private String omeroPort;
  private String omeroUser;
  private String omeroPass;

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

  private List<String> dbInputUserGrps;
  private List<String> dbInputAdminGrps;

  private String ncctMsqlDB;

  private String metadataOverwriteGroup;
  private String labelingMethods;

  private String epitopePredictionVMHost;
  private String epitopePredictionVMUser;
  private String rnaseqSampleSizeContainerName;
  private String rserveHost;
  private String rserveUser;
  private String rservePort;
  private String rservePass;

  private String statisticsFilePath;
  private String servicesRegistryUrl;

  private String servicesSubscriptionUrl;

  private String sampleTrackingServiceUrl;

  private ServiceUser serviceUser;

  // stores all properties that start with <ALLOW_UNAUTHENTICATED_ACCESS_PREFIX> whose value has
  // been set to true
  private Set<String> authorizedUnauthenticatedContentIds;

  /**
   * @param properties A {@link Properties} object that contains the properties for this instance.
   */
  PropertiesBasedConfigurationManager(final Properties properties) {
    Validate.notNull(properties, "properties is required and cannot be null");
    dataSource = properties.getProperty(DATASOURCE_KEY, "openBIS");
    dataSourceUser = properties.getProperty(DATASOURCE_USER);
    dataSourcePass = properties.getProperty(DATASOURCE_PASS);
    dataSourceUrl = properties.getProperty(DATASOURCE_URL);
    dataSourceApiUrl = properties.getProperty(DATASOURCE_API_URL);

    omeroHost = properties.getProperty(OMERO_HOST);
    omeroPort = properties.getProperty(OMERO_PORT);
    omeroUser = properties.getProperty(OMERO_USER);
    omeroPass = properties.getProperty(OMERO_PASS);

    genomeViewerUrl = properties.getProperty(GENOMEVIEWER_URL);
    genomeViewerRestApi = properties.getProperty(GENOMEVIEWER_RESTAPI);

    tmpFolder = properties.getProperty(TMP_FOLDER);
    isaConfigFolder = properties.getProperty(ISA_CONFIG);
    barcodeScriptsFolder = properties.getProperty(BARCODE_SCRIPTS_FOLDER);
    barcodePathVariable = properties.getProperty(BARCODE_PATH_VARIABLE);
    barcodeResultsFolder = properties.getProperty(BARCODE_RESULTS);

    pathToGuseWorkflows = properties.getProperty(PATH_TO_GUSE_WORKFLOWS);
    pathToGuseCertificate = properties.getProperty(PATH_TO_GUSE_CERTIFICATE);
    pathToWFConfig = properties.getProperty(PATH_TO_WF_CONFIG);

    pathToReferenceConfig = properties.getProperty(PATH_TO_REFERENCE_CONFIG);

    pathToDropboxes = properties.getProperty(PATH_TO_DROPBOXES);

    guseRemoteApiUrl = properties.getProperty(GUSE_REMOTE_API_URL);
    guseRemoteApiPass = properties.getProperty(GUSE_REMOTE_API_PASS);

    attachmentURI = properties.getProperty(ATTACHMENT_URI);
    attachmentUser = properties.getProperty(ATTACHMENT_USER);
    attachmentPass = properties.getProperty(ATTACHMENT_PASS);
    attachmentMaxSize = properties.getProperty(ATTACHMENT_MAX_SIZE);

    msqlHost = properties.getProperty(MSQL_HOST);
    msqlDB = properties.getProperty(MSQL_DB);
    msqlUser = properties.getProperty(MSQL_USER);
    msqlPort = properties.getProperty(MSQL_PORT);
    msqlPass = properties.getProperty(MSQL_PASS);

    dbInputUserGrps =
        new ArrayList<>(Arrays.asList(properties.getProperty(DB_INPUT_USER_GROUPS, "").split(",")));
    for (int i = 0; i < dbInputUserGrps.size(); i++)
      dbInputUserGrps.set(i, dbInputUserGrps.get(i).trim());
    dbInputAdminGrps = new ArrayList<>(
        Arrays.asList(properties.getProperty(DB_INPUT_ADMIN_GROUPS, "").split(",")));
    for (int i = 0; i < dbInputAdminGrps.size(); i++)
      dbInputAdminGrps.set(i, dbInputAdminGrps.get(i).trim());

    ncctMsqlDB = properties.getProperty(MSQL_NCCT_DB);

    metadataOverwriteGroup = properties.getProperty(METADATA_OVERWRITE_GROUP);
    labelingMethods = properties.getProperty(LABELING_METHODS);

    epitopePredictionVMHost = properties.getProperty(EPITOPE_PREDICTION_VM_HOST);
    epitopePredictionVMUser = properties.getProperty(EPITOPE_PREDICTION_VM_USER);
    rnaseqSampleSizeContainerName = properties.getProperty(RNASEQ_SAMPLESIZE_CONTAINER);
    rserveHost = properties.getProperty(RSERVE_HOST);
    rservePort = properties.getProperty(RSERVE_PORT);
    rserveUser = properties.getProperty(RSERVE_USER);
    rservePass = properties.getProperty(RSERVE_PASS);

    statisticsFilePath = properties.getProperty(STATISTICS_FILE_PATH);

    sampleTrackingServiceUrl = properties.getProperty(SAMPLE_TRACKING_SERVICE_URL);
    servicesRegistryUrl = properties.getProperty(SERVICES_REGISTRY_URL);

    servicesSubscriptionUrl = properties.getProperty(SERVICES_SUBSCRIPTION_URL);

    createServiceUser(properties);

    // go through all properties that start with <ALLOW_UNAUTHENTICATED_ACCESS_PREFIX> and process
    // them
    // in the end, this map will contain the ids of the content that can be displayed for
    // unauthenticated users
    authorizedUnauthenticatedContentIds = new TreeSet<>();
    for (final String propertyName : properties.stringPropertyNames()) {
      // store only properties that:
      // 1. start with our prefix AND
      // 2. are set to true
      if (propertyName.startsWith(ALLOW_UNAUTHENTICATED_ACCESS_PREFIX)
          && Boolean.parseBoolean(properties.getProperty(propertyName, "").trim())) {
        // we don't need to store the prefix
        final String contentId =
            propertyName.substring(ALLOW_UNAUTHENTICATED_ACCESS_PREFIX.length());
        if (StringUtils.isNotBlank(contentId)) {
          authorizedUnauthenticatedContentIds.add(contentId);
        } else {
          // property is empty
          LOG.warn(
              "Invalid property id found in configuration. The format of this property name is: access.unauthenticated.<contentId>=[true|false], "
                  + "where <contentId> identifies some content");
        }
      }
    }
  }

  private void createServiceUser(Properties properties) {
    this.serviceUser = new ServiceUser();

    String name = properties.getProperty(SERVICE_USER_NAME);
    String password = properties.getProperty(SERVICE_USER_PASSWORD);

    this.serviceUser.name = name;
    this.serviceUser.password = password;

  }

  @Override
  public String getConfigurationFileName() {
    // TODO: in the previous implementations (LiferayConfigurationManager,
    // LiferayIndependentConfigurationManager) this method always returned null because
    // the member variable holding the configuration value was never set
    return null;
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

  @Override
  public List<String> getUserDBInputUserGrps() {
    return dbInputUserGrps;
  }

  @Override
  public List<String> getUserDBInputAdminGrps() {
    return dbInputAdminGrps;
  }

  @Override
  public boolean isAllowUnauthenticatedAccess(final String contentId) {
    return authorizedUnauthenticatedContentIds.contains(contentId);
  }

  @Override
  public String getStatisticsFilePath() {
    return statisticsFilePath;
  }

  @Override
  public String getOmeroHostname() {
    return omeroHost;
  }

  @Override
  public String getOmeroPort() {
    return omeroPort;
  }

  @Override
  public String getOmeroUser() {
    return omeroUser;
  }

  @Override
  public String getOmeroPassword() {
    return omeroPass;
  }

  @Override
  public String getServicesRegistryUrl() {
    return servicesRegistryUrl;
  }

  @Override
  public String getServicesSubscriptionUrl() { return servicesSubscriptionUrl; }

  @Override
  public ServiceUser getServiceUser() {
    return serviceUser;
  }

  @Override
  public String getSampleTrackingServiceUrl() {
    return sampleTrackingServiceUrl;
  }

}
