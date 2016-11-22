package jdbc;

import java.sql.*;

/**
 * Created by sunny-chen on 16/11/22.
 */
public class DBConnect {

    static Connection connection;
    static Statement statement;

    public static Connection connect() {
        try {
            Class.forName(JDBCConstants.DRIVER_MYSQL);
            connection = DriverManager.getConnection(JDBCConstants.MYSQLURL, JDBCConstants.USER, JDBCConstants.PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close() throws SQLException {
        statement.close();
        connection.close();
    }

    public static void AutoCommit(boolean b) throws SQLException {
        connection.setAutoCommit(b);
    }
}
