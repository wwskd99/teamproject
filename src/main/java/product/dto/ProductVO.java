package product.dto;

import java.sql.Timestamp;

public class ProductVO {
	// 필드
	private int code;
	private String name;
	private int price;
	private String pictureUrl;
	private String description;
	private int category_id;
	private String category_name;
	private int stock;
	private Timestamp writedate;
	// 생성자
	public ProductVO() {
	}

	public ProductVO(int code, String name, int price, String pictureUrl, String description, int category_id, int stock,
			Timestamp writedate, String category_name) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.pictureUrl = pictureUrl;
		this.description = description;
		this.category_id = category_id;
		this.category_name = category_name;
		this.stock = stock;
		this.writedate = writedate;
	}

	// Getter/Setter
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public Timestamp getWritedate() {
		return writedate;
	}


	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
}