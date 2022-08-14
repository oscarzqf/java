package com.zqf.team.service;

import com.zqf.team.domain.*;


/**
 * 
 * @Description  负责将Date中的数据封装到Employee[]数组中
 * 同时提供相关操作
 * @author oscarzqf Email:228371071@qq.com
 * @version 1.0
 * @date Aug 9, 202112:43:08 PM
 *
 */
public class NameListService {
	private Employee[] employees;
/**
 * 在无参构造器中给数组及数组元素初始化
 */
	public NameListService() {
		//根据数据类中数组设置大小
		employees=new Employee[Data.EMPLOYEES.length];
		//循环类数组,new体现多态性
		for(int i=0;i<Data.EMPLOYEES.length;++i) {
			//获取员工类型
			int type=Integer.parseInt(Data.EMPLOYEES[i][0]);
			//先获取Employee的四个基本信息
			int id=Integer.parseInt(Data.EMPLOYEES[i][1]);
			String name=Data.EMPLOYEES[i][2];
			int age=Integer.parseInt(Data.EMPLOYEES[i][3]);
			double salary=Double.parseDouble(Data.EMPLOYEES[i][4]);
			//将变量声明在外面，提高通用性，减少变量声明
			Equipment equipment;
			double bonus;
			int stock;
			
			switch(type) {
			case Data.EMPLOYEE: 
				employees[i]=new Employee(id,name,age,salary);
				break;
			case Data.PROGRAMMER:
				equipment=createEquipment(i);
				employees[i]=new Programmer(id,name,age,salary,equipment);
				break;
			case Data.DESIGNER:
				equipment=createEquipment(i);
				bonus=Double.parseDouble(Data.EMPLOYEES[i][5]);
				employees[i]=new Designer(id,name,age,salary,equipment,bonus);
				break;
			case Data.ARCHITECT:
				equipment=createEquipment(i);
				bonus=Double.parseDouble(Data.EMPLOYEES[i][5]);
				stock=Integer.parseInt(Data.EMPLOYEES[i][6]);
				employees[i]=new Architect(id,name,age,salary,equipment,bonus,stock);
				break;
			}
		}
	}
	/**
	 * 
	 * @Description  根据编号获取相应类型设备
	 * @author oscarzqf
	 * @date Aug 9, 20213:29:33 PM
	 * @param index
	 * @return
	 */
	private Equipment createEquipment(int index) {
		int type=Integer.parseInt(Data.EQIPMENTS[index][0]);
		switch(type) {
		case Data.PC:
			return new PC(Data.EQIPMENTS[index][1],Data.EQIPMENTS[index][2]);
		case Data.NOTEBOOK:
			double price=Double.parseDouble(Data.EQIPMENTS[index][2]);
			return new NoteBook(Data.EQIPMENTS[index][1],price);
		case Data.PRINTER:
			return new Printer(Data.EQIPMENTS[index][1],Data.EQIPMENTS[index][2]);
		}
		return null;
	}
	/**
	 * 
	 * @Description   获取所有员工返回数组
	 * @author oscarzqf
	 * @date Aug 9, 20213:49:41 PM
	 * @return
	 */
	public Employee[] getAllEmployees() {
		return employees;
	}
	
	public Employee getEmployee(int id)  throws TeamException{
		for(int i=0;i<employees.length;i++) {
			if(employees[i].getId()==id) {
				return employees[i];
			}
		}
		//编译时异常一定要处理，这里方法异常抛出
		throw new TeamException("找不到指定员工！");
	}
}
