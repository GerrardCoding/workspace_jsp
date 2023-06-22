package com.example.demo.pojo3;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class Board3Controller implements Controller3 {
	Logger logger = Logger.getLogger(Board3Controller.class);
	//이른 인스턴스화라함
	//개발자가 직접 인스턴스화를 한다는 건 객체 관리도 내가 하겠다는 의미임
	//이런 경우는 역제어, 제어역전이라고 하지 않음
	Board3Logic boardLogic = new Board3Logic();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse res, Map<String, Object> pMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object kakaoCallback(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object jsonQnaList(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object qnaList(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object qnaDetail(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object qnaInsert(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object qnaUpdate(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object qnaDelete(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}
	//아래 메소드를 호출할 수 있는 URL은 무엇입니까?
	//-> http://localhost:9000/board3/boardList.pj3
	//1-1과 1-2번에서는 정해진 하나의 메소드(execute(req, res))만 req와 res를 가질 수 있었다.
	//그런데 여기서는 사용자 정의 메소드도 요청객체와 응답객체를 갖게 되었다 - 이걸 설명해 보세요
	//나는 서블릿이 아니어도 됨 - 왜? 아니어도 괜찮은걸까? - 소스리뷰
	@Override
	public ModelAndView boardList(HttpServletRequest req, HttpServletResponse res) {
		logger.info("boardList");
		List<Map<String,Object>> bList = null;
		bList = boardLogic.boardList();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board3/boardList");
		return mav;
	}

}
