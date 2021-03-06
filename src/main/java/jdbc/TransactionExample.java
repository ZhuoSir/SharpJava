package jdbc;

import java.sql.SQLException;

/**
 * Created by sunny-chen on 16/11/22.
 */
public class TransactionExample {

    public TransactionExample() {
    }

    public void insert() throws SQLException {
        try {
            DBConnect.connect();
            DBConnect.AutoCommit(false);

            String sql = "INSERT INTO Person (IDCard, Name, Sex, Email, Phone, AddressID) " +
                    "VALUES (22222, 'wang', 2, '61131215@qq.com', 6538, 1)";
            DBConnect.statement.executeUpdate(sql);

            sql = "INSERT INTO test.Person (IDCard, Name, Sex, Email, Phone, AddressID) " +
                    "VALUES (33333, 'zhang', 1, '213121312@qq.com', 6538, 1)";
            DBConnect.statement.executeUpdate(sql);

            DBConnect.connection.commit();
        } catch (SQLException e) {
            DBConnect.connection.rollback();
            e.printStackTrace();
        } finally {
            DBConnect.close();
        }
    }

    public static void main(String args[]) {
        TransactionExample example = new TransactionExample();
        try {
            example.insert();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
