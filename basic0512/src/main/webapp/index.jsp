<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//스크립틀릿 - 자바땅 - 자바코드를 사용할 수 있다. - 변수와 메소드 (인스턴스화)
	//스크립틀릿 안에 선언된 변수는 모두 지변(지역변수)이다.
	//변수의 초기화(위치 선택할 수 있니?)를 할 수 있다. - 기초가 아니다 - 해본자
	//변수 선언 - 타입 변수명 = 값;
	//여기에 쓰면 톰캣 서버가 실행한다
	//실행한 결과를 html문서로 내려 보낸다.(다운로드)
	//이미 결정 되었다. - 정적페이지가 다운로드 되었다. 된것이다.
	//이미 결정되었다는 무슨 말일까?
	//다운로드가 되었다 - 브라우저측에 - 클라이언트 
	//JSP(Java Server Page - 주최는 Server) => java와 html 섞어쓰기
	//JSP라고 쓰고 html이라고 읽는다 -> jsp의 mime type이 text/html이다
	//파일을 저장한 확장자는 의미없다 
	//reactjs-html과 javascript섞어쓰기
	//응답페이지 처리하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
html땅 
문자열은 인라인요소(자체크기를 안 갖고있음) <-> 블록 요소(자체크기를 갖고있음. 자동 줄바꿈)
-->
Tomcat Server Start!!!
<br>
<% String name="이순신"; %><!-- 오른쪽에 있는 값을 왼쪽에 대입. 주의 : 같은지를 묻는게 아니라.... if(1==1) if(1===1타입까지도 같니 -->
<!-- 변수 out은 내장객체라서 인스턴스화 없이도 즉시 사용가능함 -->
<% out.print(name); %> <!-- F12 -개발자센터 - 이순신만 보인다 브라우저에 출력함-->
<!-- 익스프레션 : 괄호안에 있는 값 -->
<%="강감찬" %>
<%=10 %>
<%
	//ln이 있으면 개행처리, 줄바꿈
	System.out.println("강감찬"); //콘솔에 출력할 때 사용함 - 컨벤션
%>
</body>
</html>