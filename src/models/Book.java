package models;

public class Book {
	private String category;
	private String title;
	private float price;
	
	public Book(String category, String title, float price) {
		super();
		this.category = category;
		this.title = title;
		this.price = price;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	
	
}
