package com.zqf.team.domain;

public class Designer extends Programmer{
	private double bonus;
	

	/**
	 * @return the bonus
	 */
	public double getBonus() {
		return bonus;
	}

	/**
	 * @param bonus the bonus to set
	 */
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public Designer() {
		super();
	}

	public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
		super(id, name, age, salary, equipment);
		this.bonus = bonus;
	}
	@Override
	public String toString() {
		return getDetails()+"\t设计师\t"+getStatus().getNAME()+"\t"+bonus+"\t\t"+getEquipment().getDescription();
	}
	
	public String getDetailsForTeam() {
		return getMemberId()+"/"+getId()+"\t"+getName()+"\t"+getAge()
		+"\t"+getSalary()+"\t"+"设计师\t"+getBonus();
	}
	
}
