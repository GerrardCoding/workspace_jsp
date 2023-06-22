<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	//스크립트 코드를 body 태그에 작성하면 호출없이 바로 실행됨
	//원래 http://localhost:9000/notice/test.jsp로 연결 되면 안됨
	location.href="http://172.16.2.15:3000"; //원래 막혀있어야 한다(보안상)
</script>
</body>
</html>