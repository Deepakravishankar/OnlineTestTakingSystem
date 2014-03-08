package com.ilp.otts.bean;

public class SchoolBean {
	private String schoolid,name,location,phone,board,email;

	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}

	public String getSchoolid() {
		return schoolid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getBoard() {
		return board;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	public void display(){
		System.out.printf("%s,%s,%s,%s,%s,%s",schoolid,name,location,phone,email,board);
	}
}
