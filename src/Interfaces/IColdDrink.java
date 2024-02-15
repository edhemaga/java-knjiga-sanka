package Interfaces;

public interface IColdDrink {
	String getManufacturer();
	void setManufacturer(String manufacturer);
	boolean isAlcoholic();
	void setAlcoholic(boolean alcoholic);
	boolean isCarbonated();
	void setCarbonated(boolean carbonated);
	boolean isBottled();
	void setBottled(boolean bottled);
}

