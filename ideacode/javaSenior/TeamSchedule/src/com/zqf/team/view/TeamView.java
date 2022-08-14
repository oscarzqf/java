package com.zqf.team.view;

import com.zqf.team.domain.Employee;
import com.zqf.team.domain.Programmer;
import com.zqf.team.service.NameListService;
import com.zqf.team.service.TeamException;
import com.zqf.team.service.TeamService;

/**
 * 
 * @Description  显示
 * @author oscarzqf Email:228371071@qq.com
 * @version
 * @date Aug 9, 20217:19:52 PM
 *
 */
public class TeamView {
	private NameListService listSvc=new NameListService();
	private TeamService teamSvc=new TeamService();
	public void enterMainMenu() {
		boolean loopFlag=true;
		char menu=0;
		while(loopFlag) {
		if(menu!='1') {//选‘1’的时候不会执行显示菜单
		listAllEmployees();
		}
		System.out.print("1-团队列表  2-添加团队成员 3-删除团队成员 4-退出 请选择(1-4):");
		menu=TSUtility.readMenuSelection();
		switch(menu) {
		case '1':
			getTeam();
			break;
		case '2':
			addMember();
			break;
		case '3':
			deleteMember();
			break;
		case '4':
			System.out.println("确认要退出 Y/N：");
			char isExit=TSUtility.readConfirmSelection();
			if(isExit=='Y') {
				loopFlag=false;
				System.out.println("退出成功");
			}
			break;
		}
		}
	}
	/**
	 * 
	 * @Description 显示所有员工信息
	 * @author oscarzqf
	 * @date Aug 9, 20217:28:49 PM
	 */
	private void listAllEmployees() {
		System.out.println("-------------------------------开发团队调度软件--------------------------------\n");
		Employee [] employees=listSvc.getAllEmployees();//获取所有员工对象
		if(employees==null||employees.length==0) {
			System.out.println("公司没有员工信息");
		}else {
			System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备\n");
			for(int i=0;i<employees.length;++i) {
				System.out.println(employees[i]);
			}
		}
		System.out.println("------------------------------------------------------------------------------");
	}
	private void getTeam() {
		System.out.println("-----------------团队成员列表-------------------");
		Programmer[] team=teamSvc.getTeam();
		if(team==null||team.length==0) {
			System.out.println("开发团队目前没有成员！");
		}else {
			System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票\n");
			for(int i=0;i<team.length;++i) {
				System.out.println(team[i].getDetailsForTeam());
			}
		}
		System.out.println("-----------------------------------------------");
	}
	private void addMember() {
		System.out.println("-----------------添加团队成员---------------");
		System.out.println("请输入要添加员工的ID：");
		int id=TSUtility.readInt();
		try {
			Employee emp=listSvc.getEmployee(id);
			teamSvc.addMember(emp);
			System.out.println("添加成功");
			
		} catch (TeamException e) {
			// TODO Auto-generated catch block
			System.out.println("添加失败，原因："+e.getMessage());
		}
		//按回车键继续
		TSUtility.readReturn();
	}
	
	private void deleteMember() {
		System.out.println("------------------------删除成员-----------------");
		System.out.print("请输入要删除员工的TID：");
		int memberId=TSUtility.readInt();
		System.out.println("确认删除"+memberId+" Y/N ：");
		char isDelete=TSUtility.readConfirmSelection();
		if(isDelete=='N') {
			return;
		}
		try {
			teamSvc.removeMember(memberId);
			System.out.println("删除成功");
		} catch (TeamException e) {
			// TODO Auto-generated catch block
			System.out.println("删除失败，原因："+e.getMessage());
		}
		//按回车键继续
				TSUtility.readReturn();
	}
	public static void main(String[] args) {
		TeamView view=new TeamView();
		view.enterMainMenu();
	}

}
