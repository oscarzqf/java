package com.zqf.team.service;
/**
 * 
 * @Description  自定义一个异常类  找不到指定的员工
 * @author oscarzqf Email:228371071@qq.com
 * @version
 * @date Aug 9, 20214:02:26 PM
 *
 */
public class TeamException extends Exception{
	static final long serialVersionUID=-3387514229948L;
	public TeamException() {};
	public TeamException(String msg) {
		super(msg);
	};
}
