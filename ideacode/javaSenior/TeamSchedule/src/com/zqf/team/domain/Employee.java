package com.zqf.team.domain;

public class Employee {
	private int id;
	private String name;
	private int age;
	private double salary;
	/**
	 * 获得方法
	 */
	
	/**
	 * 构造器
	 */
	public Employee() {
		super();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Employee(int id, String name, int age, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	/**
	 * 
	 * @Description  定义返回前四个项目的方法，方便后面操作
	 * @author oscarzqf
	 * @date Aug 9, 20214:47:58 PM
	 * @return
	 */
	public String getDetails() {
		return id+"\t"+name+"\t"+age+"\t"+salary;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getDetails();
	}
	
	
	
}
