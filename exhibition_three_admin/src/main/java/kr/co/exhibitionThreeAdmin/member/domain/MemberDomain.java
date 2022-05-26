package kr.co.exhibitionThreeAdmin.member.domain;

import java.sql.Date;

public class MemberDomain {
	public String userid, password, name, tel, zipcode, address1, address2, isdeleted;
	public Date subscribe_date;
	
	
	public MemberDomain() {
	}
	
	public MemberDomain(String userid, String password, String name, String tel, String zipcode, String address1,
			String address2, String isdeleted, Date subscribe_date) {
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.tel = tel;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
		this.isdeleted = isdeleted;
		this.subscribe_date = subscribe_date;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	public Date getSubscribe_date() {
		return subscribe_date;
	}
	public void setSubscribe_date(Date subscribe_date) {
		this.subscribe_date = subscribe_date;
	}

	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", password=" + password + ", name=" + name + ", tel=" + tel
				+ ", zipcode=" + zipcode + ", address1=" + address1 + ", address2=" + address2 + ", isdeleted="
				+ isdeleted + ", subscribe_date=" + subscribe_date + "]";
	}
	
}
