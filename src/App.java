import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        String url = "jdbc:mysql://127.0.0.1:3306/lab7";
        String user = "root";
        String password = "root";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();

            while (true) {
                System.out.println("Select an option:");
                System.out.println("==============================");
                System.out.println(" 1) Print all employees");
                System.out.println(" 2) Add an employee");
                System.out.println("==============================");
                int input = scnr.nextInt();
                switch (input) {
                    case 1:
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
                        while (resultSet.next()) {
                            System.out.println(
                                    resultSet.getString("name") +
                                            " - " +
                                            resultSet.getString("department") +
                                            " ("
                                            + resultSet.getString("salary") + ")");
                        }
                        System.out.println("\n");
                        break;
                    case 2:
                        System.out.println("Please enter the employee name");
                        String nameInput = scnr.next();
                        System.out.println("Please enter the employee department");
                        String departmentInput = scnr.next();
                        System.out.println("Please enter the employee salary");
                        String salaryInput = scnr.next();
                        String command = "insert into employees (name, department, salary) values (?,?,?)";
                        PreparedStatement addNew = conn.prepareStatement(command);
                        addNew.setString(1, nameInput);
                        addNew.setString(2, departmentInput);
                        addNew.setString(3, salaryInput);
                        addNew.executeUpdate();
                        System.out.println("\n");
                        System.out.println("Added\n");
                        break;

                    default:
                        break;
                }

            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }
    }
}
