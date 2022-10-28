package com.example.demo.entity;
public class Subject {
	private  int subId;
	private String name;
	private String credit;
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public Subject(int subId, String name, String credit) {
		super();
		this.subId = subId;
		this.name = name;
		this.credit = credit;
	}
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Subject [subId=" + subId + ", name=" + name + ", credit=" + credit + "]";
	}

	
}
