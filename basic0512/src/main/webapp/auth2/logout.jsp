<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//세션에 저장된 모든 정보를 삭제해줌
//저장: setAttribute("키",값); 키값만 다르면 여러가지 정보를 담을 수 도 있다(멀티로 관리가능함)
//읽기: getAttribute("키");
//삭제: removeAttribute("키");
	session.invalidate();//session.removeAttribute("nickname");
//여기는 jsp페이지이니까 res나 resp처럼 줄여쓰기는 안됨
//XXX.java(서블릿) -> HttpServlet상속받으면 서블릿이라고 함 -> 오버라이드 관계(선언)에 있는 메소드가 있다
//doGet(req,res), doPost(req,res) 선언하는 메소드에서는 파라미터자리에 변수 선언이 됨
//이 때는 줄여쓰기가 가능함
//XXX.jsp -> XXX_jsp.java -> XXX_jsp.class 내장객체를 사용하니까 줄여쓰면 안됨
	response.sendRedirect("/gym.jsp");
%>
