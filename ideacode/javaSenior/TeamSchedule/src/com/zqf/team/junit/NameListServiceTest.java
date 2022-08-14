package com.zqf.team.junit;

import org.junit.Test;

import com.zqf.team.domain.Employee;
import com.zqf.team.service.NameListService;
import com.zqf.team.service.TeamException;

/**
 * 
 * @Description  测试NameListService
 * @author oscarzqf Email:228371071@qq.com
 * @version
 * @date Aug 9, 20214:18:56 PM
 *
 */
public class NameListServiceTest {
	@Test
	public void textGetAllEmployees() {
		NameListService service=new NameListService();
		Employee[] employees=service.getAllEmployees();
		for(int i=0;i<employees.length;++i) {
			System.out.println(employees[i]);
		}
	}
	
	@Test
	public void testGetEmployee() {
		NameListService service=new NameListService();
		
		try {
			Employee employee=service.getEmployee(1);
			System.out.println(employee);//已经重写了toString方法
		}catch(TeamException e) {
			System.out.println(e.getMessage());
		}
	}

}
