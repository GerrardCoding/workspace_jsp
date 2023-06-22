package com.example.demo.pojo2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//추상메소드만 가질 수 있는 바보 - 설계단계에서만 의미가 역할이 있는
//추상클래스 보다 더 추상적이다
//추상 : 결정되지 않았다. 구체적이지 않다
//단독으로는 인스턴스화를 할 수 없다 - 생성부에는 다른 이름이 와야 한다.
public interface Controller {
	//서블릿(servlet - api.jar:톰캣서버)에게 예외처리를 미룬다
	//예외처리를 직접 하지 않고 나를 호출한 곳에서 처리 받아라 - throws
	/************************************************************************************
	 * 
	 * @param req
	 * @param res
	 * @return "redirect:boardList.jsp", or "forward:boardList.jsp" or "redirect:boardList.pj2"
	 * @throws Exception
	 ************************************************************************************/
	public abstract String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;//추상메소드
}
/*
	Action에서는 ActionForward이었다. 리턴타입이...
	(String path, boolean isRedirect(true:sendRedirect()로처리, false이면 forward{select일때만}로 처리)
	path = "noticeList.pj1" -> substring잘라냄 -> noticeList만 남김
	그런데 여기서는 String으로 변경하였다.
*/
