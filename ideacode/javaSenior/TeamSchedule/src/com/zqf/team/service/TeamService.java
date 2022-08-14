package com.zqf.team.service;

import com.zqf.team.domain.Architect;
import com.zqf.team.domain.Designer;
import com.zqf.team.domain.Employee;
import com.zqf.team.domain.Programmer;

/**
 * 
 * @Description  关于团队开发人员的管理:添加、删除
 * @author oscarzqf Email:228371071@qq.com
 * @version
 * @date Aug 9, 20215:27:31 PM
 *
 */
public class TeamService {
	private static int counter=1;//memberid赋值用
	private final int MAX_MEMBER=5;//限制开发团队人数
	Programmer[] team=new Programmer[MAX_MEMBER];//存开发人员
	private int total=0;//当前开发团队实际人数
	public TeamService() {
		super();
	}
	/**
	 * 
	 * @Description  获取所有团队成员
	 * @author oscarzqf
	 * @date Aug 9, 20215:42:35 PM
	 * @return
	 */
	public Programmer[] getTeam() {
		Programmer[] team=new Programmer[total];
		for(int i=0;i<team.length;++i) {
			team[i]=this.team[i];
		}
		return team;
	}
	/**
	 * 
	 * @Description 添加员工
	 * @author oscarzqf
	 * @date Aug 9, 20215:42:57 PM
	 * @param e
	 */
	public void addMember (Employee e) throws TeamException{
		//成员已满
		if(total>=MAX_MEMBER) {
			throw new TeamException("成员已满，无法添加");
		}
		//不是开发人员
		if(!(e instanceof Programmer)) {
			throw new TeamException("该成员不是开发人员，无法添加！");
		}
		//该员工已是某团队成员或者休假,能走到这说明是开发人员，所有可以强制转换，保证后面
				//可以调用子类私有属性和方法
				Programmer p=(Programmer)e;
		//该员工已经在开发团队中
		if(isExist(p)) {
			throw new TeamException("该员工已经在团队");
		}
		
		if("BUSY".equalsIgnoreCase(p.getStatus().getNAME())) {
			throw new TeamException("该员工已经是其他团队人员");
		}else if("VOCATION".equalsIgnoreCase(p.getStatus().getNAME())) {
			throw new TeamException("该员工正在休假，无法添加");
		}
		//团队中最多一个架构师
		//最多两个设计师
		//最多三个程序员
		//获得已经有成员中人数
		int numOfArch=0,numOfDes=0,numOfPro=0;
		for(int i=0;i<total;i++) {
			//注意小到大
		if(team[i] instanceof Architect) {
			numOfArch++;
		}else if(team[i] instanceof Designer) {
			numOfDes++;
		}else if(team[i] instanceof Programmer) {
			numOfPro++;
		}
		}
		if(p instanceof Architect) {
			if(numOfArch>=1) {
				//注意不要合成一个if(&&),
				//否则如果第一个的第一个条件为true后面的
				//第一个条件会都为true（instanceOf特性）
				throw new TeamException("团队中最多一名架构师");
			}
		}else if(p instanceof Designer) {
			if(numOfDes>=2) {
				throw new TeamException("团队中最多有两名设计师");
			}
		}else if(p instanceof Programmer) {
			if(numOfPro>=3) {
				throw new TeamException("团队中最多三名程序员");
			}
		}
		
		//将p添加到现有的team中
		team[total++]=p;
		//改变p的属性值
		p.setStatus(Status.BUSY);
		p.setMemberId(counter++);
	}
	/**
	 * 
	 * @Description  判断员工是否已经存在
	 * @author oscarzqf
	 * @date Aug 9, 20215:57:58 PM
	 * @param e
	 * @return
	 */
	private boolean isExist(Programmer e) {
		for(int i=0;i<total;++i) {           ////这里千万不能用team.length,会空指针异常
			if(team[i].getId()==e.getId()) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @Description  从团队中根据memberID删除成员
	 * @author oscarzqf
	 * @date Aug 9, 20217:00:51 PM
	 * @param memberId
	 */
	public void removeMember(int memberId) throws TeamException{
		int i;
		for(i=0;i<total;++i) {
			if(team[i].getMemberId()==memberId) {
				//改状态
				team[i].setStatus(Status.FREE);
				team[i].setMemberId(0);
				break;
			}
		}
		//如果i等于total则没找到，抛异常
				if(i==total) {
					throw new TeamException("找不到指定memberId的员工，删除失败");
				}
		//前移覆盖
		for(int j=i+1;j<total;++j) {
			team[j-1]=team[j];	
		}
		//最后置空
		team[--total]=null;
	}
}
