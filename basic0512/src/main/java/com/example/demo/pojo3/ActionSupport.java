package com.example.demo.pojo3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.demo.pojo2.BoardController;
import com.example.demo.pojo2.Controller;
import com.example.demo.pojo2.Member2Controller;

@SuppressWarnings("serial")
public class ActionSupport extends HttpServlet {
	Logger logger = Logger.getLogger(ActionSupport.class);
	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doService");
		String url = req.getRequestURI();// => /board3/boardList.pj3
		// context정보는 server.xml에서 확인 가능함
		String context = req.getContextPath();// => <<Context docBase="basic0512" path="/"
		String command = url.substring(context.length() + 1); //http:localhost:9000/ <- 빠지고 뒤에서부터라는 +1 뜻
		int end = command.lastIndexOf(".");// . <-도 빠져 필요없음 .pj3이 있는 위치 정보를 가져옴
		command = command.substring(0, end);// .pj3이 잘려나간 문자열만 남음 -> /board3/boardList
		String upmu[] = null;// upmu[0]=notice   , upmu[1]=noticeList -> 페이지이름과 메소드이름으로 통일할게여
		// 슬래시를 기준으로 문자열을 썰어서 배열에 순서대로 담아줌
		upmu = command.split("/");
		//요청객체에다가 upmu배열의 주소번지를 저장하자
		//왜냐하면 BoardController에서 if문으로 5가지 경우를 나눠야 한다.
		Object obj = null;

		//insert here - HandlerMapping과 비벼보기
		//HandlerMapping(static).getController(배열, 요청객체, 응답객체);
		obj = HandlerMapping.getController(upmu, req, resp);
		if(obj !=null) {
			String pageMove[] = null;
			ModelAndView mav = null;
			//안에 콜론 있니? 예)redirect:board/boardList.pj2, forward:board/boardList.pj2, board/boardList.pj2-> 스프링이 지원하고 있어요
			//Object안에는 두 가지 타입이 있다 - String, ModelAndView
			if(obj instanceof String) {
				
				if(((String)obj).contains(":")) {
					logger.info("내 안에 콜론있어요");
					pageMove = obj.toString().split(":");
				}
				else if(((String)obj).contains("/")) {
					logger.info("내 안에 슬래쉬 있어요");
					pageMove = obj.toString().split("/");
				}
				else {
					logger.info("내 안에 콜론도 없고 슬래쉬도 없어요");
				}//end of if -> 응답문자열을 배열에 담기
				
			}//end of String - 컨트롤클래스의 리턴타입이 String일때 처리기
			//리턴타입이 ModelAndView야? - HandlerMapping, 내 안에서 인스턴스화가 됨 - POJO1-2와 차이점 
			//ModelAndView는 스프링에서 UI{select}를 지원하기 위해 설계되었다
			else if(obj instanceof ModelAndView) {
				mav = (ModelAndView)obj;
				pageMove = new String[2];
				pageMove[0] = "forward";
				pageMove[1] = "board3/boardList.pj3";//boardList or memberList or noticeList
			}
			/////////////////////////////////////////////////////////////////////////
			///////////////////////////[[ ViewResolver]]///////////////////////////
			//insert here
			//pageMove 원소의 갯수가 2개일 때 - 백엔드 처리된 결과를 화면으로 처리할 때
			if(pageMove != null && pageMove.length == 2) {
				logger.info("pageMove원소의 갯수가 2개 일때");
				String path = pageMove[1];
				//redirect로 할까?
				if("redirect".equals(pageMove[0])) {//return "redirect:dept/getDeptList"
					resp.sendRedirect(path);
				}
				//forward로 해야돼?
				else if("forward".equals(pageMove[0])){//return "forward:dept/get/DeptList"
					RequestDispatcher view = req.getRequestDispatcher("/"+path+".jsp");
					view.forward(req,  resp);
				}
				//보안 때문에 WEB-INF로 보내줄까?
				else {//redirect도 없고 forward도 없는 경우야?
					RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/"+path+".jsp");
					view.forward(req,  resp);
				}
			}//////////////////////// end of pageMove 원소의 갯수가 2개 일 때 ////////////////////////////
			//pageMove원소의 갯수가 1개일 때 - 화면이 아닌 경우를 처리하기 위해서 설계함 - 단순문자열이거나 JSON포맷(React배려하는 설계임)
			else if(pageMove != null && pageMove.length == 1) {
				logger.info("pageMove원소의 갯수가 1개 일때");
				
			}/////////////////////// end of pageMove 갯수가 1개일 때 - 화면이 아닌 문자열이나 JSON 포맷지원할 때 (spring - @RestController대신 해줌)
			//원시적인 방법 또는 레거시 시스템을 공부하는 건 자동으로 처리하다가 문제가 발생하거나 해당 F/W가 지원을 안해주더라도
			//표준적인 방법을 알고 있으면 해결할 수 있다(실마리, 컨벤션, 힌트, 아이디어 제공....)
			
			///////////////////////////[[ ViewResolver]]///////////////////////////
		}//page가 null이 아니면..
	}// end of doService
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(req, resp);
	}
	
}
