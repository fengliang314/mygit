package com.ssm.system.util;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * 通用的返回的类
 * 
 * 
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Msg {
	
	//状态码   100-成功    200-失败
	private int code;
	//提示信息
	private String msg;
	
	private String enMsg;
	
	//用户要返回给浏览器的数据
	private Map<String, Object> extend = new HashMap<String, Object>();

	public static Msg success(){
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("处理成功！");
		result.setEnMsg("success");
		return result;
	}
	
	public static Msg fail(){
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("处理失败！");
		result.setEnMsg("fail");
		return result;
	}
	
	public Msg add(String key,Object value){
		this.getExtend().put(key, value);
		return this;
	}
	
	public String getEnMsg() {
		return enMsg;
	}

	public void setEnMsg(String enMsg) {
		this.enMsg = enMsg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	
}
