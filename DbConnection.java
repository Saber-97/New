package GUI;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class DbConnection {
    
    private static String servername = "localhost" ;
    private static String username = "root" ;
    private static String dbname = "database" ;
    private static int portnumber = 3306 ;
    private static String password = "" ;
    
    public static Connection getConnection(){
        Connection cnx = null;
        MysqlDataSource datasource = new MysqlDataSource();
        
        datasource.setServerName(servername);
        datasource.setDatabaseName(dbname);
        datasource.setUser(username);
        datasource.setPassword(password);
        datasource.setPortNumber(portnumber);
        
        try {
            cnx = datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger("Get Connection: "+DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnx;
    }
    
}
