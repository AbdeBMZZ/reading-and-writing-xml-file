package models;

public class Book {
	private String title;
	private String price;
	
	public Book() {
		super();

	}
	

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + "]";
	}


	public void setPrice(String price) {
		this.price = price;
	}

	
	
}
