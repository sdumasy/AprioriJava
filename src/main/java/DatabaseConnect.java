
import com.google.gson.Gson;
import com.sun.tools.javac.jvm.ClassFile;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.sql.DriverManager.getConnection;


public class DatabaseConnect {

    private static Connection connection = null;

    private static String url = "jdbc:mysql://164.132.47.93";
    private static String user = "reader";
    private static String password = "reader";
    private static String databaseName = "Bakery";


    public static Connection openConnectionToDb() {
        try {
            connection = getConnection(url, user, password);
            connection.createStatement().executeQuery("USE " + databaseName);
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ClassFile.Version.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public static void closeConnectionToDb(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ClassFile.Version.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    public static List<Map<String, Object>>  excecuteSearchQuery(String query) {
        List<Map<String, Object>> listOfMaps = null;
        try {
            openConnectionToDb();
            QueryRunner queryRunner = new QueryRunner();
            listOfMaps = queryRunner.query(connection, query, new MapListHandler());
        } catch (SQLException se) {
            throw new RuntimeException("Couldn't query the database.", se);
        }
        finally {
            closeConnectionToDb(connection);
        }
        return listOfMaps;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDatabaseName() {
        return databaseName;
    }
}