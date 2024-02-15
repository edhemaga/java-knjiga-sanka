import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Interfaces.IProduct;

public class Product implements IProduct {
	private int id;
	private String name;
	private float quantity;
	private String category;
	
	public Product() {}
	
	public Product(String name, float quantity, String category) {
		this.name = name;
		this.quantity = quantity;
		this.category = category;
	}
	
	public Product(int id, String name, float quantity, String category){
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.category = category;
	}
	
	
	public Product addProduct(Connection connection) {
            String sql = "INSERT INTO products (name, quantity, category) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, this.name);
                statement.setFloat(2, this.quantity);
                statement.setString(3, this.category);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                	String sqlSelect = "SELECT * FROM products ORDER BY id DESC LIMIT 1;";

                    try (PreparedStatement statementSelect = connection.prepareStatement(sqlSelect)) {
                        try (ResultSet resultSet = statementSelect.executeQuery()) {
                            if (resultSet.next()) {
                                // Retrieve product details from the result set
                                	int productId = resultSet.getInt("id");
                                	String productName = resultSet.getString("name");
                                	float productQuantity = resultSet.getFloat("quantity");
                                	String productCategory = resultSet.getString("category");

                                	return new Product(productId, productName, productQuantity, productCategory);
                                } else {
                                System.out.println("No products in the table.");
                                return null;
                            }
                        }
                    }
                    
                } else {
                    System.out.println("Insert failed.");
                    return null;
                }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        }
	}
	
    public Map<String, Integer> getProductsByCategory(Connection connection) {
        String sql = "SELECT category, COUNT(*) AS product_count FROM products GROUP BY category";

        Map<String, Integer> categoryCountMap = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String category = resultSet.getString("category");
                    int productCount = resultSet.getInt("product_count");

                    // Store category and product count in the map
                    categoryCountMap.put(category, productCount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query", e);
        }
        
        return categoryCountMap;
    }

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public float getQuantity() {
		return quantity;
	}
	
	public String getCategory() {
		return category;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public boolean isProductAvailable() {
		if(quantity > 0 ) {
			return true;
		} else {
			return false;
		}
	}
	

	
	//TODO implementirati isProductAvailable ali da vrati tekstualnu poruku npr. Product nije na stanju, produkt je na stanju
	
	/*public boolean isProductAvilable() {
		if(quantity > 0) {
			System.out.println("Product je na stanju");
		} else {
			System.out.println("Product nije na stanju");
		}
		return false;
	}*/
	
	
	//TODO procitati sta je interface, sta je nasljedivanje i kako radi
	
		
	
	//TODO probati napraviti klasu hladni napitci (ColdDrinks) koja nasljeduje Product
	
}
