package Dominio;

import java.util.LinkedList;

public class Product {

	private String nameProduct;
	private String category;
	private String description;
	private int price;
	private int stock;
	private LinkedList<String> reviews;
	
	public Product(String nameProduct, String category, String description, int price, int stock) {
		this.nameProduct = nameProduct;
		this.category = category;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.reviews = new LinkedList<>();
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public LinkedList<String> getReviews() {
		return reviews;
	}

	public void setReviews(LinkedList<String> reviews) {
		this.reviews = reviews;
	}
	
	
	
}
