
public class HotDrinks extends Product {
	private int id;
	private boolean isTakeout;

	public HotDrinks(String name, float quantity, String category, boolean isTakeout) {
		super(name, quantity, category);
		this.isTakeout = isTakeout;
	}

    public boolean isTakeout() {
        return isTakeout;
    }

    public void setTakeout(boolean takeout) {
        isTakeout = takeout;
    }
}
