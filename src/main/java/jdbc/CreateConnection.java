package jdbc;

import hibernate.domain.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by sunny-chen on 16/11/19.
 */
public class CreateConnection {

    private static Statement statement;
    private static Connection connection;

    public static void main(String[] args) {
        try {
            Class.forName(JDBCConstants.DRIVER_MYSQL);
            System.out.println("Driver load success");
            connection = DriverManager.getConnection(JDBCConstants.MYSQLURL, JDBCConstants.USER, JDBCConstants.PASSWORD);
            System.out.println("Connect success");
            statement = connection.createStatement();
            testQuery();
            connection.close();
            System.out.println("Connect close");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet query(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    public static void testQuery() throws SQLException {
        List<Person> personList = new ArrayList<>();
        String sql = "select * from Person";
        ResultSet resultSet = query(sql);
        Person person;
        while (resultSet.next()) {
            person = new Person();
            person.setIdCard(resultSet.getInt("IDCard"));
            person.setName(resultSet.getString("Name"));
            person.setSex(resultSet.getInt("Sex"));
            person.setEmail(resultSet.getString("Email"));
            person.setPhone(resultSet.getInt("Phone"));
            person.setAddressId(resultSet.getInt("AddressID"));
            personList.add(person);
        }
        personList.forEach(System.out::println);
    }
}
