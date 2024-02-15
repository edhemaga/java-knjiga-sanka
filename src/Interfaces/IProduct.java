package Interfaces;

public interface IProduct {
    String getName();
    float getQuantity();
    String getCategory();
    void setName(String name);
    void setQuantity(float quantity);
    void setCategory(String category);
    boolean isProductAvailable();
}
