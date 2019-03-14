# Portal Utilities Library
[![Build Status](https://travis-ci.org/qbicsoftware/portal-utils-lib.svg?branch=master)](https://travis-ci.org/qbicsoftware/portal-utils-lib)[![Code Coverage]( https://codecov.io/gh/qbicsoftware/portal-utils-lib/branch/master/graph/badge.svg)](https://codecov.io/gh/qbicsoftware/portal-utils-lib)

## Author
Created by Luis de la Garza (luis.delagarza@qbic.uni-tuebingen.de).

## Description
Includes old `liferayandvaadinutils` library. New portal-specific code should be placed here.

## How portlets are configured
So you are minding your own business when you decide that it would be better to make your cool new feature configurable. *Where is the configuration file?*, you ask yourself. And you may ask yourself, "Well... How did I get here?".

Configuration in a running Liferay portal happens via properties files. When a portlet is initialized, Liferay loads all available properties files. These files are found at the *root* folder where the portal has been installed. All files with a `.properties` file extension will be loaded as so-called *portal properties* (which you can access by invoking the `com.liferay.util.portlet.PortletProps.getProperties()` method).

This is all fine and dandy, but what happens when you do `mvn jetty:run`? In this case, the settings are read from a file named `portlet.properties`. This file is located in the classpath and, if you generated your portlet using [our cookiecutter command-line tool]((the https://github.com/qbicsoftware/cookiecutter-templates-cli)), that file already exists under `src/main/resources/portlet.properties` and you can modify it for local testing (be careful to not add sensitive information to this file and then push it to GitHub!).

### How to add a new configuration setting
1. Add a *getter* method to the `life.qbic.portal.utils.ConfigurationManager` interface. This method will return the value of the new configuration setting you are adding, so make sure to name it accordingly.
1. Modify the `life.qbic.portal.utils.PropertiesBasedConfigurationManager` class by adding your new configuration setting(s). Take a look at how other settings are loaded and try to be consistent with the code (i.e., add a constant with a property name, add a member variable to hold the property value and add a *getter* method).
1. If you want to do it by the book, add a test. Yes, it's just a simple *getter* method, but we have seen configuration settings that were never read from a properties file. There's no limit to how bad a project can turn into if no one writes tests.

#### Example
Your portlet needs to display some numeric values and you want to be able to configure the formatting. First, you modify the `life.qbic.portal.utils.ConfigurationManager` interface and add the following code:

```
/**
  * @return the pattern to format numeric values.
  */
  String getNumberFormatPattern();
```

You then open `life.qbic.portal.utils.PropertiesBasedConfigurationManager` and add code similar to the following one:

 ```
 /**
   * @return the path of the file that goes into our secret feature.
   */
   static final String NUMBER_FORMAT_PATTERN = "number.format.pattern";
   
   ...
   
   private final String numberFormatPattern;
   
   ...
   PropertiesBasedConfigurationManager(final Properties properties) {
     // other code
     numberFormatPattern = properties.getProperty(NUMBER_FORMAT_PATTERN);
   }
 ```
 
 Your test would look like:
 
```
 @Test
 public void testNumberFormatPattern() {
   properties.setProperty(PropertiesBasedConfigurationManager.NUMBER_FORMAT_PATTERN, "#,###,###,##0.00");
   
   final ConfigurationManager configurationManager = new PropertiesBasedConfigurationManager(properties);
   
   assertEquals("#,###,###,##0.00", configurationManager.getNumberFormatPattern());
 }
```
 





