package com.springlab.member;

import java.sql.Date;

public class MemberDTO {

	private int idx;
	private String id;
	private String pass;
	private String name;
	private String email;
	private int age;
	private double weight;
	private Date regDate;
	private int cnt;
	
	//기본 생성자 추가
	public MemberDTO() {}
	
	//getter/setter 
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	//toString()메소드 재정의 : 객체 자체를 출력시 필드의 값을 출력
	@Override
	public String toString() {
		return "MemberDTO [idx=" + idx + ", id=" + id + ", pass=" + pass + ", name=" + name + ", email=" + email
				+ ", age=" + age + ", weight=" + weight + ", regDate=" + regDate + ", cnt=" + cnt + "]";
	}
	
	
	
	
	
	
}
