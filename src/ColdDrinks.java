import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Interfaces.IColdDrink;

class ColdDrink extends Product implements IColdDrink  {
	private int id;
    private String manufacturer;
    private boolean isAlcoholic;
    private boolean isCarbonated;
    private boolean isBottled;
    
    public ColdDrink() {        
    	super();
}

    public ColdDrink(String name, float quantity, String category, String manufacturer, boolean isAlcoholic, boolean isCarbonated, boolean isBottled) {
        super(name, quantity, category);
        this.manufacturer = manufacturer;
        this.isAlcoholic = isAlcoholic;
        this.isCarbonated = isCarbonated;
        this.isBottled = isBottled;
    }
    
    public ColdDrink getDrink(Connection connection, int id) {
    	String sql = "SELECT products.id AS product_id, products.name, products.quantity, products.category, " +
                "coldDrinks.id AS coldDrink_id, coldDrinks.manufacturer, coldDrinks.isAlcoholic, " +
                "coldDrinks.isCarbonated, coldDrinks.isBottled " +
                "FROM products " +
                "INNER JOIN coldDrinks ON products.id = coldDrinks.id" +
                "WHERE id = " + id + ";";
    	
    		try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                        	String productName = resultSet.getString("name");
                            float productQuantity = resultSet.getFloat("quantity");
                            String productCategory = resultSet.getString("category");

                            String coldDrinkManufacturer = resultSet.getString("manufacturer");
                            boolean isAlcoholic = resultSet.getBoolean("isAlcoholic");
                            boolean isCarbonated = resultSet.getBoolean("isCarbonated");
                            boolean isBottled = resultSet.getBoolean("isBottled");

                            // Create a new ColdDrink object and set its values
                            ColdDrink coldDrink = new ColdDrink(productName, productQuantity, productCategory,
                                    coldDrinkManufacturer, isAlcoholic, isCarbonated, isBottled);

                            // Now, the coldDrink object is ready for use in your application
                            System.out.println("Cold Drink Object: " + coldDrink);
                			return coldDrink;
                        }
                    }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new Error(e);
            }
			return null;
    }

    public void addColdDrink(Connection connection, int id) {
            String sqlColdDrink = "INSERT INTO coldDrinks (id, manufacturer, isAlcoholic, isCarbonated, isBottled) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sqlColdDrink)) {
                // Set values for the placeholders
            	statement.setInt(1, id);
                statement.setString(2, this.manufacturer);
                statement.setBoolean(3, this.isAlcoholic);
                statement.setBoolean(4, this.isCarbonated);
                statement.setBoolean(5, this.isBottled);
                System.out.print(statement);
                // Execute the insert statement
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Insert successful. Rows affected: " + rowsAffected);
                } else {
                    System.out.println("Insert failed.");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Getter and setter for manufacturer
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    // Getter and setter for isAlcoholic
    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        isAlcoholic = alcoholic;
    }

    // Getter and setter for isCarbonated
    public boolean isCarbonated() {
        return isCarbonated;
    }

    public void setCarbonated(boolean carbonated) {
        isCarbonated = carbonated;
    }

    // Getter and setter for isBottled
    public boolean isBottled() {
        return isBottled;
    }

    public void setBottled(boolean bottled) {
        isBottled = bottled;
    }
}
