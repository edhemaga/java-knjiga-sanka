import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		 // JDBC URL, username, and password
        String url = "jdbc:mysql://localhost:3306/knjiga_sanka";
        String username = "edhemaga";
        //String password = "EnisEnis1234?";
        String password = "123";

        // Establish a connection
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database.");
            
            Statement statement = connection.createStatement();
            
            var userTable = "CREATE TABLE IF NOT EXISTS users (\r\n"
            		+ "    id INT AUTO_INCREMENT PRIMARY KEY,\r\n"
            		+ "    name VARCHAR(255),\r\n"
            		+ "    lastname VARCHAR(255),\r\n"
            		+ "    username VARCHAR(255),\r\n"
            		+ "    password VARCHAR(255),\r\n"
            		+ "    type VARCHAR(50)\r\n"
            		+ ");\r\n";
            
            statement.execute(userTable);
            
            var productTable = "CREATE TABLE IF NOT EXISTS products (\r\n"
            		+ "    id INT AUTO_INCREMENT PRIMARY KEY,\r\n"
            		+ "    name VARCHAR(255) NOT NULL,\r\n"
            		+ "    quantity FLOAT NOT NULL,\r\n"
            		+ "    category VARCHAR(255) NOT NULL,\r\n"
            		+ "    type VARCHAR(50)\r\n"
            		+ ");\r\n";
            
            statement.execute(productTable);
            
           var coldDrinksTable = "CREATE TABLE IF NOT EXISTS coldDrinks (\r\n"
            		+ "    id INT PRIMARY KEY,\r\n"
            		+ "    manufacturer VARCHAR(255) NOT NULL,\r\n"
            		+ "    isAlcoholic BOOLEAN NOT NULL,\r\n"
            		+ "    isCarbonated BOOLEAN NOT NULL,\r\n"
            		+ "    isBottled BOOLEAN NOT NULL,\r\n"
            		+ "    FOREIGN KEY (id) REFERENCES products(id)"
            		+ ");\r\n";
            
            statement.execute(coldDrinksTable);
            
            var hotDrinksTable = "CREATE TABLE IF NOT EXISTS hotDrinks (\r\n"
            		+ "    id INT PRIMARY KEY,\r\n"
            		+ "    isTakeout BOOLEAN NOT NULL, \r\n"
            		+ "    FOREIGN KEY (id) REFERENCES products(id)"            	
            		+ ");\r\n";
            
            statement.execute(hotDrinksTable);

            
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter a command (type 'exit' to quit): ");
                String command = scanner.nextLine();
                
                if("ADD COLD DRINK".equalsIgnoreCase(command)) {
                	 Random random = new Random();

                     // Generate random values for the constructor parameters
                     String randomProductName = "RandomProduct" + random.nextInt(1000);
                     float randomProductQuantity = random.nextFloat() * 100;
                     String randomProductCategory = "RandomCategory" + random.nextInt(10);

                     // Create and return a Product object with random data
                     Product product = new Product(randomProductName, randomProductQuantity, randomProductCategory);
                     Product addedProduct = product.addProduct(connection);
                     
                     String randomName = "Drink" + random.nextInt(1000);
                     float randomQuantity = random.nextFloat() * 100;
                     String randomCategory = "Category" + random.nextInt(10);
                     String randomManufacturer = "Manufacturer" + random.nextInt(100);
                     boolean randomIsAlcoholic = random.nextBoolean();
                     boolean randomIsCarbonated = random.nextBoolean();
                     boolean randomIsBottled = random.nextBoolean();
                     ColdDrink newColdDrink = new ColdDrink(randomName, randomQuantity, randomCategory, randomManufacturer, randomIsAlcoholic, randomIsCarbonated, randomIsBottled);
                	
                     newColdDrink.addColdDrink(connection, addedProduct.getId());
                }
                
                if("COLD DRINKS BY CATEGORY".equalsIgnoreCase(command)) {
                	Product product = new Product();
                	Map<String, Integer> categoryCountMap = product.getProductsByCategory(connection);
                    for (Map.Entry<String, Integer> entry : categoryCountMap.entrySet()) {
                        System.out.println("Category: " + entry.getKey() + ", Product Count: " + entry.getValue());
                    }
                }

                if ("exit".equalsIgnoreCase(command)) {
                    System.out.println("Exiting the program...");
                    break;  // Exit the loop when the 'exit' command is entered
                }

                // Process the entered command or perform other actions
                System.out.println("You entered: " + command);
            }

            // Don't forget to close the scanner to release resources
            scanner.close();

        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        		
	
	}

}
