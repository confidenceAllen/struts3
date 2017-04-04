package com.struts2;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

public class UserAction implements SessionAware,ApplicationAware{
	
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String logout(){
		//1.在线人数-1
		Integer count = (Integer) application.get("count");
		
		if(count != null && count > 0){
			count--;
			application.put("count", count);
		}
		
		return "logout";
	}

	public String execute(){
		
		//把用户信息存入Session域中
		
		session.put("username", username);
		
		//在线人数+1
		//1.获取当前的在线人数
		Integer count = (Integer) application.get("count");
		if(count==null){
			count=0;
		}
		//2.使当前的人数+1
		count++;
		
		application.put("count", count);
		
		return "login-success";
	}
	
	private Map<String,Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	
	private Map<String,Object> application;

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application=application;
		
	}

}
