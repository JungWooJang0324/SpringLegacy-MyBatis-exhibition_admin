package kr.co.exhibitionThreeAdmin.additional.vo;

public class LoginVO {
	public String admin_id;
	public String password;
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginVO [admin_id=" + admin_id + ", password=" + password + "]";
	}
	
	
}
