package br.eti.gregori.ccih.util;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

public class PropertyParser {
    private static final String CFG_FILE = "config.properties";

    private InputStream inputStream;
    private OutputStream output;
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

    public boolean getDbSetup() {
        return properties.getProperty("db_setup").equals("1");
    }

    public void disableDbSetup() {
        try {
            output =  new FileOutputStream("config.properties");
            properties.setProperty("db_setup", "0");
            properties.store(output, null);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo de propriedades não encontrado!", "Erro ao atualizar configurações", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar propriedades!", "Erro ao atualizar configurações", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
