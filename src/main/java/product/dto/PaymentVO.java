package product.dto;

public class PaymentVO {

	private int delivery_num;
	private int code;

	private int amount;	

	private String order_name;
	private String order_email;
	private String order_phone;
	private String del_name;
	private String del_phone;
	private String del_address;
	private String del_message;
	private String del_date;
	private String paymethod;
	private String del_pass;
	private int post;
	private String userid;
	private Cus_ProductVO cus_productVO;
	
	public Cus_ProductVO getCus_productVO() {
		return cus_productVO;
	}
	public void setCus_productVO(Cus_ProductVO cus_productVO) {
		this.cus_productVO = cus_productVO;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getDelivery_num() {
		return delivery_num;
	}
	public void setDelivery_num(int delivery_num) {
		this.delivery_num = delivery_num;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getOrder_email() {
		return order_email;
	}
	public void setOrder_email(String order_email) {
		this.order_email = order_email;
	}
	public String getOrder_phone() {
		return order_phone;
	}
	public void setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
	}
	public String getDel_name() {
		return del_name;
	}
	public void setDel_name(String del_name) {
		this.del_name = del_name;
	}
	public String getDel_phone() {
		return del_phone;
	}
	public void setDel_phone(String del_phone) {
		this.del_phone = del_phone;
	}
	public String getDel_address() {
		return del_address;
	}
	public void setDel_address(String del_address) {
		this.del_address = del_address;
	}
	public String getDel_message() {
		return del_message;
	}
	public void setDel_message(String del_message) {
		this.del_message = del_message;
	}
	public String getDel_date() {
		return del_date;
	}
	public void setDel_date(String del_date) {
		this.del_date = del_date;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getDel_pass() {
		return del_pass;
	}
	public void setDel_pass(String del_pass) {
		this.del_pass = del_pass;
	}
	public int getPost() {
		return post;
	}
	public void setPost(int post) {
		this.post = post;
	}

	
}