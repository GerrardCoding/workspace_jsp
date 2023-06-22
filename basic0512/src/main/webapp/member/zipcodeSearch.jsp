<%@page import="java.util.Map" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	//디클러레이션-
	//메소드를 호출하려면 가장 먼저 뭘했지? - 인스턴스화를 하려면 클래스명이 필요해
	//이 파일은 .java가 아니더라....... 나는 이아이는 .jsp이라서.....
	//a..jsp.java, 이름...jsp.java - Tomcat서버가 그렇다
	//만일 톰캣 서버가 아닌 다른 서버를 사용하게 되면 클래스 이름이 달라진다. 왜냐면 명명규칙은 서버제품마다
	//다른 명명규칙을 정했을 테니깐......
	//그러니까 메소드 호출은 불가하지 않을까????
	//재사용성을 높이는 코딩을 전개하는 첫번째 방법이 메소드 중심의 코딩을 전개하는 것이다
	//나는 메소드의 파라미터 갯수를 정할 수 있나?
	//나는 메소드의 리턴타입을 정할 수 있는 기준이 있는가?
	//기초의 문제가 아니더라..
	void go(){}
%>
<%
	//스크립틀릿 - 내안에는 모두 지변이다. 서블릿으로 전환시 service메소드 내부에 들어가는 코드이니까 지역변수이다
	//그러니까 메소드 선언은 곤란하죠
	//여기는 Tomcat서버 내부에서 실행이 된 후 html문서를 만들어서 클라이언트측에 응답으로 다운로드 내보내는 방식으로 처리됨
	int size = 0;
	List<Map<String,Object>> zList = (List)request.getAttribute("zList");//강제 형전환
	if(zList !=null) {
		size = zList.size();
	}
	//확장자 jsp를 갖는 파일에 적힌 자바 코드는 브라우저에서 소스보기로 절대 볼 수 없다 - 기억
	//html과 자바코드를 섞어쓰지만 실행주체가 다르고 실행되는 시점이 다르다 - 설명할 수 있다 - 넌 대단해!!!
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색기</title>
<%@include file="/common/bootstrap_common.jsp"%>
<link rel="stylesheet" href="/css/zipcode.css">
<script type="text/javascript">
	const zipcodeSearch = () => {
		const dong = document.querySelector("#dong").value;//사용자가 입력한 동이름이 담김
		console.log(dong);
		location.href="./memberCRUD?method=zipcodeList&dong="+dong;
	}
</script>
</head>
<body>
	<div class="container">
	<!-- =======================[[ 검색기 ]]======================= -->
		<div class="row">
			<div class="col-8">
				<input type="text" id="dong" class="form-control" placeholder="동이름을 입력하세요">
			</div>
			<div class="col-4">
				<button type="button" class="btn btn-info" onclick="zipcodeSearch()">찾기</button>
			</div>			
		</div>
		<div style="margin-bottom:10px"></div>
	<!-- =======================[[ 검색기 ]]======================= -->
	<!-- =======================[[ 검색결과 출력 ]]======================= -->
		<div class="main">
			<table class="table table-hover" width="600px">
				<thead>
					<tr>
						<th width="20%">우편번호</th>
						<th width="80%">주소</th>
					</tr>
				</thead>
				<tbody>
<%
	//조회 결과가 있니? - if문
	if(size>0) {
			//조회 결과가 여러개야 - for문
		for(int i=0;i<size;i++) {
			Map<String,Object> rmap = zList.get(i);
%>
					<tr>
						<td><a href="#"><%=rmap.get("ZIPCODE") %></a></td>
						<td><%=rmap.get("ADDRESS") %></td>
					</tr>
<%
		}//end of for
	} else {
	//else문
%>
					<tr>
						<td colspan="2">조회결과가 없습니다.</td>
					</tr>
<%
	}
%>	
				</tbody>
			</table>
		</div>
	<!-- =======================[[ 검색결과 출력 ]]======================= -->
	</div>
</body>
</html>