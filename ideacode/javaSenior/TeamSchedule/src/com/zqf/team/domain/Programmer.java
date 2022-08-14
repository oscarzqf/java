package com.zqf.team.domain;

import com.zqf.team.service.Status;

public class Programmer extends Employee{
	private int memberId;//开发团队中的ID
	private Status status=Status.FREE;
	private Equipment equipment;
	
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * @return the memberId
	 */
	public int getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return the equipment
	 */
	public Equipment getEquipment() {
		return equipment;
	}
	/**
	 * @param equipment the equipment to set
	 */
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	/**
	 * 构造器
	 */
	public Programmer() {
		super();
	}
	public Programmer(int id,String name,int age,double salary, Equipment equipment) {
		super( id, name, age,salary);
		
		this.equipment = equipment;
	}
	@Override
	public String toString() {
		return getDetails()+"\t程序员\t"+status.getNAME()+"\t\t\t"+equipment.getDescription();
	}
	
	public String getDetailsForTeam() {
		return memberId+"/"+getId()+"\t"+getName()+"\t"+getAge()
		+"\t"+getSalary()+"\t"+"程序员";
	}
	
}
