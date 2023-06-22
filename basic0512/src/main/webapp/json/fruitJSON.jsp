<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, java.util.List, java.util.ArrayList, java.util.HashMap" %>
<%@ page import="com.google.gson.Gson" %>
<%
	//스크립틀릿 - 메소드 정의 불가함, 호출만 가능함, 지역변수만 가능함, 인스턴스화 가능함, 제어문 가능함
	List<Map<String,Object>> fruitList = new ArrayList<>();//싱글스레드 안전
	Map<String,Object> rMap = new HashMap<>();
	rMap.put("first","토마토");
	rMap.put("second","키위");
	fruitList.add(rMap);//fruitList.get(0) = new HashMap<>();
	rMap = new HashMap<>();
	rMap.put("first","수박");
	rMap.put("second","딸기");
	fruitList.add(rMap);
	Gson g = new Gson();
	String temp = g.toJson(fruitList);
	out.print(temp);
%>