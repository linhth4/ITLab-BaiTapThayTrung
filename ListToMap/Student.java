package com.ListToMap;

public class Student {
	private int Id;
	private String name;
	private long total;

	public Student(int id, String name, long websites) {
		Id = id;
		this.name = name;
		this.total = total;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long gettotal() {
		return total;
	}

	public void settotal(long total) {
		this.total = total;
	}

}
