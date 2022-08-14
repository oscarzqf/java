package com.zqf.team.service;
/**
 * 
 * @Description  员工的状态,使用枚举
 * @author oscarzqf Email:228371071@qq.com
 * @version
 * @date Aug 9, 202112:23:06 PM
 *
 */
public class Status {
	private final String NAME;
	private Status(String name) {
		this.NAME=name;
	}
	public static final Status FREE=new Status("FREE");
	public static final Status BUSY=new Status("BUSY");
	public static final Status VOCATION=new Status("VOCATION");
	/**
	 * @return the nAME
	 */
	public String getNAME() {
		return NAME;
	}
	
}
