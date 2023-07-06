package com.example.user_management.util;

import com.example.user_management.infrastructure.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

@Slf4j
public class PropertiesReader {

    private PropertiesReader() {
    }

    private static Properties applicationProperties = new Properties();
    private static Properties validationProperties = new Properties();
    private static Logger logger = Logger.getLogger(PropertiesReader.class);


    static {
        // Load application properties file
        try (
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.FileProperties.PROPERTIES_APPLICATION);
                InputStreamReader reader = new InputStreamReader(is, Constants.ENCODING_UTF8);) {
            applicationProperties.load(reader);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        // Load validation properties file
        try (
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.FileProperties.PROPERTIES_VALIDATION);
                InputStreamReader reader = new InputStreamReader(is, Constants.ENCODING_UTF8);
        ) {
            validationProperties.load(reader);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static String getProperty(String propertyName) {
        return getProperty(propertyName, Constants.FileProperties.PROPERTIES_VALIDATION);
    }

    public static String getProperty(String propertyName, String propertiesType) {
        switch (propertiesType) {
            case Constants.FileProperties.PROPERTIES_APPLICATION:
                return applicationProperties.getProperty(propertyName);
            case Constants.FileProperties.PROPERTIES_VALIDATION:
                return validationProperties.getProperty(propertyName);
            default:
                return null;
        }
    }

}
