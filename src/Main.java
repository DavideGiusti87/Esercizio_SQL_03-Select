/*
SQL 03 - Select
Exercise - SQL-select

before doing this exercise, you have to complete 0-SQL-installation-and-user-permissions and SQL-table exercises
use JDBC for executing the following query on the jdbc:mysql://localhost:3306/newdb database:
take the names and surnames of all the students (using ResultSet and its .next() method) and:
print the names on screen while executing the query
assign the surnames to an ArrayList called surnames
once the query is completed, print all the surnames
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        Connection connection = null;
        ArrayList<String> surnames = new ArrayList<>();

        try (FileInputStream file = new FileInputStream("C:/Users/Davide/IdeaProjects/Develhope/Esercizio_SQL_03-Select/src/db.properties")) {

            Properties properties = new Properties();
            properties.load(file);

            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("* CONNECTION TO DB SUCCESFUL *");

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT last_name, first_name " +
                            "FROM `newdb`.`students`;"
            );

            while (rs.next()) {
                System.out.printf("%d) %s\n", rs.getRow(), rs.getString("first_name"));
                surnames.add(rs.getString("last_name"));
            }

            System.out.println(Arrays.toString(surnames.toArray()));

        } catch (IOException | SQLException e) {

            System.out.println("* UNABLE TO CONNECT TO THE DB, MODIFY IT OR RUN A QUERY *\n" + e.getMessage());

        } finally {
            try {

                if (connection != null) connection.close();
                System.out.println("* CLOSED CONNECTION TO DB *");

            } catch (SQLException ex) {
                System.out.println("* IMPOSSIBLE DISCONNECTION FROM DB *\n" + ex.getMessage());
            }
        }


    }
}
