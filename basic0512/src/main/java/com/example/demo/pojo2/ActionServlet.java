package com.example.demo.pojo2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.demo.pojo1.NoticeController;

@SuppressWarnings("serial")
public class ActionServlet extends HttpServlet {
	Logger logger = Logger.getLogger(ActionServlet.class);
	// 사용자 정의 메소드
	// 아래 메소드를 호출하는 URL 패턴은 무엇입니까?
	// http://localhost:9000/notice/noticeList.pj1 - 테스트
	// http://localhost:9000/notice/XXX.pj1

	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doService");

		String url = req.getRequestURI();// => /notice/noticeList.pj1
		logger.info(url);
		// context정보는 server.xml에서 확인 가능함
		String context = req.getContextPath();// => /
		String command = url.substring(context.length() + 1);
		int end = command.lastIndexOf(".");// .pj1이 있는 위치 정보를 가져옴
		command = command.substring(0, end);// .pj1이 잘려나간 문자열만 남음 -> /notice/noticeList
		String upmu[] = null;// upmu[0]=notice , upmu[1]=noticeList -> 페이지이름과 메소드이름으로 통일할게여
		// 슬래시를 기준으로 문자열을 썰어서 배열에 순서대로 담아줌
		upmu = command.split("/");
		//요청객체에다가 upmu배열의 주소번지를 저장하자
		//왜냐하면 BoardController에서 if문으로 5가지 경우를 나눠야 한다.
		//insert here
		//
		Controller controller = null;
		String page = null;
		//테스트 시나리오
		//http://localhost:9000/member2/memberList.pj2
		//http://localhost:9000/member2/memberInsert.pj2
		//http://localhost:9000/member2/memberUpdate.pj2
		//http://localhost:9000/member2/memberDelete.pj2
		if("member2".equals(upmu[0])) {
			logger.info("member2");
			controller = new Member2Controller();
			//메소드이름을 가질 수는 없지만 if문으로 5가지 경우를 나눌수 있다
			//그래서 나는 execute메소드를 호출할 떄 넘기는 request객체에다가 upmu배열을 저장한다
			req.setAttribute("upmu", upmu);
			//오버라이드된 execute메소드 호출하기
			//컨트롤 클래스는 Controller인터페이스를  implements하고 있다 - 추상메소드 재정의해야 함
			//각 컨트롤러 클래스가 서블릿이 아니어도 되는건 execute메소드의 파라미터로 용청객체와 응답객체를 받을 수 있어서 그렇다
			page = controller.execute(req, resp);
		}
		//http://localhost:9000/board/memberList.pj2
		//http://localhost:9000/board/memberInsert.pj2
		//http://localhost:9000/board/memberUpdate.pj2
		//http://localhost:9000/board/memberDelete.pj2
		else if("board".equals(upmu[0])) {
			controller = new BoardController();
			//요청 객체에 String배열의 주소번지를 담음
			req.setAttribute("upmu", upmu);//배열의 주소번지
			//메소드 호출을 하기
			//메소드 호출할 때 첫번째 자리에 요청객체를 넘기자 -> 왜냐면 ActionServlet에서 생성한 String[]을 BoardController엥서 재사용해야 하니까....
			//request가 저장소의 역할도 가능한건가요? - yes
			page = controller.execute(req, resp);
		}//내려 갈 때 전처리 코드
		//아래 코드는 BoardController -> BoardLogic -> BoardDao 그리고 나서 실행되는 구간이 됨
		//올라올 때 후처리 코드 - 출력페이지
		//page에 담긴 문자열은 어떤 형태인가요?
		//redirect:board/boardList.jsp or forward:board/boardList.pj2
		if(page !=null) {
			String pageMove[] = null;
			//안에 콜론 있니? 예)redirect:board/boardList.pj2, forward:board/boardList.pj2, board/boardList.pj2-> 스프링이 지원하고 있어요
			if(page.contains(":")) {
				logger.info("내 안에 콜론있어요");
				pageMove = page.split(":");
			}else {
				logger.info("내 안에 콜론없어요");
				pageMove = page.split("/");
			}//end of if -> 응답문자열을 배열에 담기
			logger.info(pageMove[0]+","+pageMove[1]);
			//insert here - 아직 sendRedirect나 forward에 대한 코드가 없다.
			if(pageMove != null) {//요청에 대해 응답문자열이 나왔어
				String path = pageMove[1];//pageMove[0]-forward
				//너 안에 있는게 redirect니?
				if("redirect".equals(pageMove[0])) {
					logger.info("redirect");
					resp.sendRedirect(path);
					//리턴을 반드시 붙여줄것 - 없으면 에러남(응답이 커밋된 후에는 forward할수 없습니다.
					return;
				}
				//forward를 가진거야?
				else if("forward".equals(pageMove[0])) {
					logger.info("forward");
					// -> /memberList.jsp -> 404발동
					RequestDispatcher view = req.getRequestDispatcher("/"+path+".jsp");//memberList , path를 적어야 변수 적용, 상수 넣으면 변수시 안됨
					view.forward(req, resp);
				} else {
					//redirect도 없고 forward도 없어 - 스프링지원
				}
			}////////////end of 후처리
		}//page가 null이 아니면
	}// end of doService
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

}
