package com.zqf.team.domain;

public class Architect extends Designer{
	private int stock;

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	public Architect() {
		super();
	}

	public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
		super(id, name, age, salary, equipment, bonus);
		this.stock = stock;
	}
	@Override
	public String toString() {
		return getDetails()+"\t架构师\t"+getStatus().getNAME()+"\t"+getBonus()+"\t"+stock+"\t"+getEquipment().getDescription();
	}
	public String getDetailsForTeam() {
		return getMemberId()+"/"+getId()+"\t"+getName()+"\t"+getAge()
		+"\t"+getSalary()+"\t"+"架构师\t"+getBonus()+"\t"+stock;
	}
	
}
