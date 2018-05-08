package life.qbic.portal.utils;

/**
 * The ConfigurationManager Factory has only the getInstance mehtod. Dependigng on whether the
 * portlet runs on its own or in a Portal environemnt different implementation of the
 * ConfigurationManager are returned.
 * 
 * @author wojnar
 * 
 */
public class ConfigurationManagerFactory {
  /**
   * checks whether it runs in a Liferay Portal and returns either an independent or an dependent
   * Liferay implementation
   * 
   * @return Instance of ConfigurationManager
   */
  public static ConfigurationManager getInstance() {

    if (Utils.isLiferayPortlet()) {
      if (!LiferayConfigurationManager.Instance.isInitialized()) {
        LiferayConfigurationManager.Instance.init();
      }
      return LiferayConfigurationManager.Instance;
    }

    if (!LiferayIndependentConfigurationManager.Instance.isInitialized()) {
      LiferayIndependentConfigurationManager.Instance.init();
    }

    return LiferayIndependentConfigurationManager.Instance;
  }
}
