package Dominio;


public class Product {

	private String nameProduct;
	private String category;
	private String description;
	private int price;
	private int stock;
	private String image;
	
	public Product(String nameProduct, String category, String description, int price, int stock, String image) {
		this.nameProduct = nameProduct;
		this.category = category;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	
	
	
	
	
}
