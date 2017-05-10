package datasource.simple;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * Created by sunny on 2017/5/10.
 */
public class SimpleDateSource implements DataSource {

    private static Logger log = Logger.getLogger(SimpleDateSource.class);

    private static final String driverClassName = "com.mysql.jdbc.Driver";

    private static final String url = "jdbc:mysql://127.0.0.1:3306/test";

    private static final String user = "root";

    private static final String password = "root";

    private static LinkedList<Connection> pool = (LinkedList<Connection>) Collections.synchronizedList(new LinkedList<Connection>());

    private static SimpleDateSource instance = null;

    static {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public SimpleDateSource instance() {
        if (null == instance)
            instance = new SimpleDateSource();
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        synchronized (pool) {
            if (pool.size() > 0)
                return pool.removeFirst();
            else
                return createConnection();
        }
    }

    private Connection createConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        pool.add(conn);
        return conn;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
