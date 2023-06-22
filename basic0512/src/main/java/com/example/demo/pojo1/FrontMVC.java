package com.example.demo.pojo1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class FrontMVC extends HttpServlet {
	Logger logger = Logger.getLogger(FrontMVC.class);//필요하든 필요하지 않든 넣어두기

	//사용자 정의 메소드
	//아래 메소드를 호출하는 URL 패턴은 무엇입니까?
	//http://localhost:9000/notice/noticeList.pj1 - 테스트
	//http://localhost:9000/notice/XXX.pj1 
	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doService");
		//응답 화면에 대한 결정과 redirect로 할지 forward로 할지는 NoticeController가 결정할 수 있다.
		ActionForward af = null;
		String url = req.getRequestURI();//=> /notice/noticeList.pj1
		logger.info(url);
		//context정보는 server.xml에서 확인 가능함
		String context = req.getContextPath();// => /
		String command = url.substring(context.length()+1);
		int end = command.lastIndexOf(".");//.pj1이 있는 위치 정보를 가져옴
		command = command.substring(0, end);//.pj1이 잘려나간 문자열만 남음 -> /notice/noticeList
		String upmu[] = null;//upmu[0]=notice , upmu[1]=noticeList -> 페이지이름과 메소드이름으로 통일할게여
		//슬래시를 기준으로 문자열을 썰어서 배열에 순서대로 담아줌
		upmu = command.split("/");
		//테스트하기 - http://localhost:9000/notice/noticeList.pj1엔터
		for(String str:upmu) {//개선된 for문. 전체 출력할 때
			//logger.info(str);2번 출력됨
		}
		//여기까지 pj1으로 요청이 들어왓을 때 web.xml을 통해서 FrontMVC클래스의 doService메소드 호출됨을
		//확인하였다. 그 다음은 NoticeController의 execute 메소드 호출하기이다
		NoticeController noticeController = new NoticeController();
		if("notice".equals(upmu[0])) {
			//바로 아래 코드때문에 NoticeController는 서블릿이 아니어도 괜찮고
			//그럼에도 request와 response 객체를 누릴수 있다. - NullPointerException발생하지 않음 - 잘된 설계
			//선언이 사용ㅇ보다 먼저 이다
			req.setAttribute("upmu", upmu);//배열의 주소번지
			af = noticeController.execute(req, resp);
		}
		//꼭 필요한 부분이다 - 페이지를 출력하는데 
		////////////////////[[ spring에서는 ViewResolver 클래스가 지원하는 부분임]] ////////////////////
		if(af !=null) {
			if(af.isRedirect()) {
				//sendRedirect는 response 객체가 선언하고 있다
				//메소드 호출앞에 .앞에 오는 변수는 인스턴스 변수이다. - 소유주
				//메소드 파라미터 자리를 채울 수 있는 상태인가?(리턴타입과 파라미터)
				resp.sendRedirect(af.getPath());
				//응답이 이미 커밋된 후에는 forward할 수 없습니다.
				//redirect이 후에 return을 줘야만 JSP절차가 정상적으로 종료됨
				return;
			}//end of redirect - insert, update, delete
			else {//forward - 유지, 주소안변함, 그런데 페이지는 바뀌었다
				RequestDispatcher view = req.getRequestDispatcher(af.getPath());//상수가 아니라 변수 변수아니라 메소드 처리하는 코드 - 세련됨
				view.forward(req, resp);
			}//end of forward - select
		}//end of if - ActionForward결과로 후처리하는 코드 끝나는 부분이었습니다.....
		//힌트 - 업무폴더이름으로 가능한가? 아니면 페이지 이름으로 하는게 좋은가?
		////////////////////[[ ]] ////////////////////
	}
	//서블릿에서 정의된 메소드를 재정의하는것
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}
	//서블릿에서 정의된 메소드를 재정의하는 것
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}
	
}
/*
 * doGet으로 요청이 들어오든지 또는 doPost로 요청이 오든지 모두 doService메소드로 경유하도록 설계 하였다.
 * 메소드 설계는 리턴타입과 파라미터를 포함한다
 * 이때 파라미터로 톰캣 서버로 부터 주입받은 request객체와 response 객체를 넘겨받는다 - 중요
 * 왜 서블릿이어야 하나? - 요청과 객체와 응답객체를 받아야 하니까
 * doGet의 파라미터 자리는 바꿀 수 없다 - what? 파라미터 갯수나 타입을 바꿀수 없다
 * 왜냐면 메소드 오버라이딩 조건을 어기는 거니까... -> 컴파일 에러 -> 실행 불가한 상태 말함
 * 
 * 요청객체와 응답객체는 반드시 서버로 부터 주입 받아야 하는 객체이다.
 * 따라서 서블릿 안에 다른 메소드를 정의할 수 는 있지만 아무 의미 없다
 * 왜냐면 요청객체와 응답객체가 없이는 아무것도 할 수가 없다 - web에서는 
 */
