<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, java.util.ArrayList, java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>a1.jsp</title>
</head>
<body>
	<h2>sendRedirect메소드를 이용한 페이지 이동- response객체제공함</h2>
    <ul>
        <li>내용1</li>
        <li>내용2</li>
        <li>내용3</li>
    </ul>
    <%
        //4번
       //DML중에서 SELECT처리할 떄 사용함 - forward로 페이지 이동처리를 해야 유지할 수 있다.
        RequestDispatcher view = request.getRequestDispatcher("b1.jsp");//메소드 호출이 객체를 리턴함
        //List는 인터페이스이다
        //인터페이스는 단독으로 인스턴스화를 할 수 없다.
        //인터페이스는 반드시 구현체 클래스를 가져야한다
        //인터페이스는 추상메소드만 가진다 - 그래서 추상클래스보다 더 추상적이다. ????
        List<Map<String, Object>> memList = new ArrayList<>();//다형성-폴리모피즘
        Map<String, Object> rmap = new HashMap<>();
        rmap.put("mem_id", "tomato");
        rmap.put("mem_pw", 123);
        rmap.put("mem_name", "토마토");
        memList.add(rmap);//주소번지 저장됨 - 제네릭타입
        out.print(memList.size());//0출력 -> 1 출력
        request.setAttribute("size",memList.size());//size=0 유지할 수 있다
        request.setAttribute("memList",memList);//size=0 유지할 수 있다
    //내장객체란 인스턴스화 없이도 바로 사용 가능한 객체이다
    //파라미터로 넘기는 변수 이름은 줄여 쓸 수 없다. - 왜냐면 내장객체 이름이니까
    	view.forward(request,response);//요청객체와 응답객체의 주소번지를 넘긴다????
    %>
    <p>내용4</p>
</body>
</html>
<!-- 
	이것을 회원목록 조회할 때, 조건검색할 때, 부서목록, 주문목록 조회 - (CRUD - select(가장중요-실력차-야근-조인), insert, update, delete : DML데이타 조작 언어)
	select한 결과를 유지해야 한다(서블릿과 JSP사이에 값(주소번지)을  )
 -->