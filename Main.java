import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Runnable class to handle database operations in a separate thread
class DatabaseTask implements Runnable {
    private String url;
    private String sql;

    public DatabaseTask(String url, String sql) {
        this.url = url;
        this.sql = sql;
    }

    @Override
    public void run() {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created or already exists by " + Thread.currentThread().getName());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

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

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Create and start multiple threads
            Thread user1 = new Thread(new DatabaseTask(url, sql), "User 1");
            Thread user2 = new Thread(new DatabaseTask(url, sql), "User 2");
            Thread user3 = new Thread(new DatabaseTask(url, sql), "User 3");
            Thread user4 = new Thread(new DatabaseTask(url, sql), "User 4");

            user1.start();
            user2.start();
            user3.start();
            user4.start();

            user1.join();
            user2.join();
            user3.join();
            user4.join();
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
