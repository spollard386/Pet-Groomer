import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:test.db";

        String sql = "CREATE TABLE IF NOT EXISTS pets (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " type TEXT NOT NULL,\n"
                + " name TEXT NOT NULL,\n"
                + " age INTEGER,\n"
                + " needsDogSpace BOOLEAN,\n"
                + " needsCatSpace BOOLEAN,\n"
                + " stayDuration INTEGER,\n"
                + " totalAmountDue REAL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created or already exists.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
