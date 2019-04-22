package com.webservice;

import javax.xml.ws.Endpoint;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-03-13 10:45
 * @desc 发布方法
 **/
public class publishMethod {
	public static void main (String[] args) {
		String url = "http://localhost:8090/MyService";
		Endpoint.publish (url,new MyFirstWebService ());
		System.out.println ("方法发布了，老弟！");
	}
}
