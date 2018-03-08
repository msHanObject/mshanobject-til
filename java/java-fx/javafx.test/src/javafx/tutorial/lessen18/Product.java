package javafx.tutorial.lessen18;

public class Product {
	//
	private String name;
	private double price;
	private int quantity;
	
	public Product() {
		//
		this.name = "";
		this.price = 0;
		this.quantity = 0;
	}
	
	public Product(String name, double price, int guantity) {
		//
		this.name = name;
		this.price = price;
		this.quantity = guantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int guantity) {
		this.quantity = guantity;
	}
}
