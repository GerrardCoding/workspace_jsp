package com.example.demo.step1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/rest/test")
public class RestfulServlet extends HttpServlet {
	Logger logger = Logger.getLogger(RestfulServlet.class);
	//스프링에서는 annotation으로 지원하는 API가 존재함
	//request와 response없이도 웹서비스를 제공할 수 있게 되었다. - 서블릿에 대한 의존성을 낮추었다
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doDelete");//405번이면 대응하는 메소드 이름 오타
		String temp = null;
		String param = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		while((temp = br.readLine())!=null) {
			param += temp;//+ concat 붙여쓰기
		}
		logger.info(param);
		//사용한 IO클래스는 반드시 닫아주어야 한다. - 왜냐면 다른 사람이 탈취, 읽기
		br.close();
		
	}
	//GET 전송방식이 디폴트 이다
	//GET 방식은 쿼리스트링으로 값을 받을수 있다
	//GET 방식은 링크를 걸 수 있다
	//단 전송데이터의 크리는 브라우저 마다 제약이 따른다
	//사용자가 입력한 값이 그대로 노출됨 - 보안 취약 - 로그인 구현시에는 사용하지 말자
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet");//405번이면 대응하는 메소드 이름 오타
		//사용자가 화면에 입력한 값을 단위테스트 가능하다. - POST방식은 불가함 - 그래도 하고 싶으면 Postman도구
		// <input type = text id="mem_id" name="mem_id">
		//파라미터 자리에는 입력받는 콤포넌트의 name이 와야 한다
		String mem_id = req.getParameter("mem_id");
		String mem_name = req.getParameter("mem_name");
		//화면이 없이도 사용자가 입력한 값을 postman을 통해서 단위테스트 가능함
		//마임타입은 응답페이지와 관계있으니 응답객체를 사용한다
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();//메소드 호출의 리턴값으로 객체를 주입받음
		out.print("<div>"+mem_id+"</div>");
		//resp.sendRedirect("페이지이름써준다");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doPost");//405번이면 대응하는 메소드 이름 오타
		String mem_id = req.getParameter("mem_id");
		String mem_pw = req.getParameter("mem_pw");
		logger.info(mem_pw);//null출력
	}
	//rest api put메소드는 getParameter값을 읽을 수 없다
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doPut");//405번이면 대응하는 메소드 이름 오타
		String temp = null;
		String param="";
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		
		InputStreamReader isr = new InputStreamReader(req.getInputStream()); //위에 한문장을 밑에 두문장으로 적을수 있다는것을 보여줌
		BufferedReader br2 = new BufferedReader(isr);
		
		while((temp = br2.readLine()) != null) {
			param += temp;//+ concat 붙여쓰기
		}
		logger.info(param);
		//사용한 IO클래스는 반드시 닫아주어야 한다. -왜냐면 다른 사람이 탈취, 읽기
		br.close();
		br2.close();
		isr.close();
	}

}
