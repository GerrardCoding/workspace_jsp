<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.vo.DeptVO" %>    
<%
	List<DeptVO> deptList = (List<DeptVO>)request.getAttribute("deptList");
	out.print(deptList);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 목록[WEB-INF]</title>
</head>
<body>
부서 목록[WEB-INF]
</body>
</html>