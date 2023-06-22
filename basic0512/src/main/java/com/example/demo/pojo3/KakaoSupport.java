package com.example.demo.pojo3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class KakaoSupport extends HttpServlet {
	Logger logger = Logger.getLogger(KakaoSupport.class);
	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doService");
		//callback함수는 개발자가 호출하는게 아니고 시스템레벨에서 상태변화에 따라 자동으로 호출되는 함수말함
		//-> http://localhost:9000/auth/kakao/callback
		//프로토콜, 도메인, 포트번호를 제외한 나머지 정보
		//대기업 자체 프레임워크 만듦 - 소스리뷰가 가능하도록 연습
		String requestURI = req.getRequestURI();//-> /auth/kakao/callback
		String contextPath = req.getContextPath();//server.xml문서 <Context태그내에 path정보 /
		logger.info(contextPath);
		String command = requestURI.substring(contextPath.length()+1);
		logger.info(command);
		String result = "";
		if("auth/kakao/callback".equals(command)) {
			logger.info("카카오 로그인 콜백URL요청시 호출");
			KakaoController kakaoController = new KakaoController();
			result = kakaoController.kakaoCallback(req, resp);
			logger.info(result);//gym.jsp, 카카오컨트롤계층에서 반환하는 값 출력해 보기
			resp.setContentType("text/plain;charset=UTF-8");
			try {
				//출력을 담당하는 객체를 생성하기 전에 마임타입을 결정해줄것 - 왜냐면 장치에 있는 브라우저 - 동기화
				PrintWriter out = resp.getWriter();//출력객체:PrintWriter는 응답으로 나가는 정보 출력해야함
				//객체를 생성 할 때 response객체가 반드시 필요함 -> 인스턴스화 가능함 -> 왜냐면 로컬 PC설치된 브라우저를 통해 내보냄
				out.print(result);
				//res.setContentType("text/html;charset=UTF-8");
				//resp.sendRedirect("/"+result);//요청에 대한 응답이 페이지로 출력되는 경우도 있고
				return;//하나의 요청 종료하기
			} catch(Exception e) {
				
			}
		} else {
			System.out.println("kakao callback 요청이 아닙니다.");
			try {
				resp.sendRedirect(requestURI);
				return;//하나의 요청 종료하기
			} catch(Exception e) {
				
			}
			logger.info("로그인 실패하였습니다");
		}
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
