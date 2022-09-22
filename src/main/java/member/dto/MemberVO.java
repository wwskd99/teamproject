package member.dto;

// 회원정보를 저장하는 빈
public class MemberVO {
	private String name;	// varchar(10)
	private String userid;
	private String pwd;
	private String email;
	private String address;
	private String phone;
	private int grade;
	private int gender;// integer(1)
	private int mile;

	public MemberVO() { }
	
	public MemberVO(String name, String userid, String pwd, String email, String address, String phone, int grade,
			int gender, int mile) {
		super();
		this.name = name;
		this.userid = userid;
		this.pwd = pwd;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.grade = grade;
		this.gender = gender;
		this.mile = mile;
	}
	// Getter/Setter
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMile() {
		return mile;
	}
	public void setMile(int mile) {
		this.mile = mile;
	}
	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", userid=" + userid + ", pwd=" + pwd + ", email=" + email + ", address="
				+ address + ", phone=" + phone + ", grade=" + grade + ", gender=" + gender + ", mile=" + mile + "]";
	}

	
}
