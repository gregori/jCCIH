package br.eti.gregori.ccih.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyParser {
    private static final String CFG_FILE = "util.properties";

    private InputStream inputStream;
    private Properties properties;

    public PropertyParser() {
        try {
            initializeProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeProperties() throws IOException {
        try {
            properties = new Properties();
            String propFileName = CFG_FILE;

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + propFileName + "' not found.");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }

    public String getDatabase() {
        return properties.getProperty("database");
    }

    public String getDbUser() {
        return properties.getProperty("db_user");
    }

    public String getJdbcDriver() {
        return properties.getProperty("jdbc_driver");
    }

    public String getDbPassword() {
        return properties.getProperty("db_pass");
    }

    public String getDbUrl() {
        return properties.getProperty("db_url");
    }
}
