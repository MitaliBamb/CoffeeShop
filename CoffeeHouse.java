import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CoffeeHouse {

    // Database credentials and URL - update USER and PASS as needed
    private static final String DB_URL = "jdbc:mysql://localhost:3306/coffee_house";
    private static final String USER = "root";           // your MySQL username
    private static final String PASS = "";  // your MySQL password (put your real password)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] menu = {"Espresso", "Cappuccino", "Latte", "Americano"};
        double[] prices = {2.5, 3.0, 3.5, 2.0};

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();

        double total = 0.0;
        boolean ordering = true;

        System.out.println("Welcome to the Coffee House, " + name + "!");

        while (ordering) {
            System.out.println("\nMenu:");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + ". " + menu[i] + " - $" + prices[i]);
            }
            System.out.print("Select your coffee (1-" + menu.length + "): ");
            int choice = scanner.nextInt();

            if (choice < 1 || choice > menu.length) {
                System.out.println("Invalid choice, try again.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            double cost = prices[choice - 1] * quantity;
            total += cost;

            System.out.println("Added " + quantity + " x " + menu[choice - 1] + " to your order. Subtotal: $" + cost);

            System.out.print("Would you like to order anything else? (y/n): ");
            char more = scanner.next().charAt(0);

            if (more == 'n' || more == 'N') {
                ordering = false;
            }
        }

        System.out.println("\nYour total bill is: $" + total);
        System.out.println("Thank you for visiting the Coffee House!");

        // Save order details to DB
        saveOrderToDatabase(name, phone, total);

        scanner.close();
    }

    private static void saveOrderToDatabase(String name, String phone, double total) {
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Prepare SQL insert statement
            String sql = "INSERT INTO orders (name, phone, total_bill) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setDouble(3, total);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Your order has been saved successfully.");
            }

            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error saving order to database.");
            e.printStackTrace();
        }
    }
}
