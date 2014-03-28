package com.queue;

/**
 * 提交webservice数据bean
 * @author pangpeijie
 *
 */
public class DataBean {

	private String webServiceName;//webservice接口名
	
	private String jsonStr;//webservice接口数据

	public String getWebServiceName() {
		return webServiceName;
	}

	public void setWebServiceName(String webServiceName) {
		this.webServiceName = webServiceName;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
	
	
	
}
