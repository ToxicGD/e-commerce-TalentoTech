import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData; // Necessary for the section of connectivity check
import java.util.Scanner;

public class App {

    private static void runQuery(Connection connection, String query) { // Main method for making SELECT queries
        try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        System.out.println("\n--- 📋 Query Result ---");

        // Print column headers
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(meta.getColumnName(i) + "\t");
        }
        System.out.println();

        // Print rows
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }

    } catch (SQLException e) {
        System.err.println("❌ Error executing query: " + e.getMessage());
    }
    }
    public static void main(String[] args) {
        
        // Environment variables and connection base
        if (System.getenv("DB_PASSWORD") == null) {
            System.err.println("Error: DB_PASSWORD environment variable is not set.");
            return;
        }
        if (System.getenv("DB_HOST") == null) {
            System.err.println("Error: DB_HOST environment variable is not set.");
            return;
        }
        final String DB_HOST = System.getenv("DB_HOST");
        final String DB_PORT = System.getenv("DB_PORT") != null ? System.getenv("DB_PORT") : "5432";
        final String DB_NAME = System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : "postgres";
        final String DB_USERNAME = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "postgres";
        final String DB_PASSWORD = System.getenv("DB_PASSWORD");
        final String DB_URL = String.format("jdbc:postgresql://%s:%s/%s", DB_HOST, DB_PORT, DB_NAME);
        System.out.println("Connecting to database at: " + DB_URL);
        System.out.println("Using username: " + DB_USERNAME);
        System.out.println("Using password: " + DB_PASSWORD.replaceAll(".", "*")); // Mask the password in logs
        
        Connection connection = null;

    try {

        // This is the connectivity check
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        System.out.println("---");
        System.out.println("🥳 Successfully connected to the database!");

        // Print metadata to prove success
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println("---");
        System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
        System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
        System.out.println("Driver Name: " + metaData.getDriverName());
        System.out.println("Driver Version: " + metaData.getDriverVersion());
        System.out.println("---");

        // Interactive menu
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== 📦 Database Query Menu ===");
            System.out.println("1. Show all users"); 
            System.out.println("2. Show all products");
            System.out.println("3. Custom SQL query"); // Right now only possible to SELECT
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim(); // Could also be done with int but String may be a better choice, will talk with guys

            switch (choice) {
                case "1":
                    runQuery(connection, "SELECT * FROM Users LIMIT 10");
                    break;
                case "2":
                    runQuery(connection, "SELECT * FROM Products LIMIT 10");
                    break;
                case "3":
                    System.out.print("Enter your SQL query (must be a SELECT): ");
                    String customQuery = scanner.nextLine();
                    if (customQuery.trim().toLowerCase().startsWith("select")) {
                        runQuery(connection, customQuery);
                    } else {
                        System.out.println("Only SELECT statements are allowed.");
                    }
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();

    } catch (SQLException e) {
        System.err.println("---");
        System.err.println("❌ Database connection failed or query error!");
        e.printStackTrace();
    } finally {
        try {
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            System.err.println("Error closing database resources: " + ex.getMessage());
        }
    }
}
}