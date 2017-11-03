/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DuongPTH
 */
public class DatabseConnector implements Serializable {

    public DatabseConnector() {
    }

    public static Connection getConnection(String databaseName, String adminName, String adminPass) {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=" + databaseName, adminName, adminPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
