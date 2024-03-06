/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author franc
 */
public class DatabaseConnection {
    // MÉTETE BIEN ESTO EN LA CABEZA !!!
    // Evita nombrar esta clase como Connection, 
    // ya que genera conflicto con la importación de java.sql.Connection
    public static Connection getConnection() {
        Connection connection = null;
        Properties prop = new Properties();
        
        try {
            InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("config.properties");
    
            if (inputStream == null) {
                System.out.println("Unable to load config.properties file.");
                return null;
            }
            
            prop.load(inputStream);

            // Información DB
            String db_name = prop.getProperty("db_name");
            String db_url = prop.getProperty("db_url") + db_name;
            String db_user = prop.getProperty("db_user");
            String db_password = prop.getProperty("db_password");
        
            // Cargar el driver
            Class.forName(prop.getProperty("db_driver"));
            connection = DriverManager.getConnection(
                    db_url, 
                    db_user, 
                    db_password
            );
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("An error has been found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error has been found: " + e.getMessage());
        }
        
        return connection;
    }
    
    // dbg
    //public static void main(String[] args) {
    //    Connection connection = DatabaseConnection.getConnection();
    //    if (connection != null) {
    //        System.out.println("Connection successful: " + connection);
    //    } else {
    //        System.out.println("Connection failed.");
    //    }
    //}
}
