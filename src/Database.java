import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Database {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=GPAtracking;user=sa;password=123456789;encrypt=true;trustServerCertificate=true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static List<String[]> fetchData() {
        List<String[]> data = new ArrayList<>();
        String query = "SELECT Subject, Credit, [In-class], Midterm, Final, Total FROM GPAscore";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String[] row = new String[6];
                row[0] = resultSet.getString("Subject");
                row[1] = String.valueOf(resultSet.getInt("Credit"));
                row[2] = String.valueOf(resultSet.getInt("In-class"));
                row[3] = String.valueOf(resultSet.getInt("Midterm"));
                row[4] = String.valueOf(resultSet.getInt("Final"));
                row[5] = String.valueOf(resultSet.getDouble("Total"));
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
