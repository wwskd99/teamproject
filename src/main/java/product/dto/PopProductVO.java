package product.dto;

public class PopProductVO {
	
	private String popword;
	private int count;
	
	public PopProductVO() {
	}

	public PopProductVO(String popword, int count) {
		super();
		this.popword = popword;
		this.count = count;
	}
	
	public String getPopword() {
		return popword;
	}

	public void setPopword(String popword) {
		this.popword = popword;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
