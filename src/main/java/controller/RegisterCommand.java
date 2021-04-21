package controller;

import java.time.LocalDateTime;

public class RegisterCommand {

	private String id;
	private String pw;
	private String confirmPw;
	private String name;
	private String year;
	private String month;
	private String day;
	private String birth;
	private String addr_road;
	private String addr_detail;
	private String addr;
	private String tel;
	private String email;
	private LocalDateTime regdate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getConfirmPw() {
		return confirmPw;
	}

	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		if(Integer.parseInt(month) >= 10) {
			this.month = month;
		}else {
			this.month = "0"+month;
		}
		if(Integer.parseInt(day) >= 10) {
			this.day = month;
		}else {
			this.day = "0"+day;
		}
		return year + "년 " + month + "월 " + day + "일";
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddr() {
		return addr_road + " " + addr_detail;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getAddr_road() {
		return addr_road;
	}

	public void setAddr_road(String addr_road) {
		this.addr_road = addr_road;
	}

	public String getAddr_detail() {
		return addr_detail;
	}

	public void setAddr_detail(String addr_detail) {
		this.addr_detail = addr_detail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getRegdate() {
		return regdate;
	}

	public void setRegdate(LocalDateTime regdate) {
		this.regdate = regdate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}






	public boolean isPasswordEqualToConfirmPassword() {
		return pw.equals(confirmPw);
	}
}
