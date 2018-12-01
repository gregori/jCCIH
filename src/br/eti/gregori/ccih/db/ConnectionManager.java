package br.eti.gregori.ccih.db;

import br.eti.gregori.ccih.util.PropertyParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static String database;
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    private static Connection conn = null;
    private static ConnectionManager manager;

    static {
        manager = new ConnectionManager();
    }

    public static ConnectionManager getInstance() {
        return manager;
    }

    private ConnectionManager() {
        PropertyParser pp = new PropertyParser();
        database = pp.getDatabase();
        dbUrl = pp.getDbUrl() + database;
        dbUser = pp.getDbUser();
        dbPassword = pp.getDbPassword();
    }

    public Connection getConnection() throws SQLException {

        if (conn == null || conn.isClosed()) {
            try {
                conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return conn;
    }
}
