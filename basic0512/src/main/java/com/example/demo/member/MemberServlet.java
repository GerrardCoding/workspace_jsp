package com.example.demo.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.util.HashMapBinder;

// 개발자 입장에서 보면 get방이든 post방식이든 처리를 해주어야 하는 것은 같다

@SuppressWarnings("serial")
@WebServlet("/member/memberCRUD")
public class MemberServlet extends HttpServlet {
	Logger logger = Logger.getLogger(MemberServlet.class);
	MemberDao memberDao = new MemberDao();

	/*****************************************************************************************************************
	 * 회원(CRUD)관리 구현 URL : 업무명(폴더명) - member 호출이름 - memberCRUD 쿼리스트링- 조회 :
	 * ?method=memberSelect SELECT mem_no, mem_id, mem_pw, mem_name FROM member0518;
	 * WHERE mem_no = ? -> 메소드의 파라미터 자리에 온 값이다. 타입이다 쿼리스트링- 등록 :
	 * ?method=memberInsert insert into member0518(mem_no,mem_id, mem_pw,mem_name)
	 * values(3,'banana','345','나초보'); 쿼리스트링- 수정: ?method=memberUpdate UPDATE
	 * member0518 SET mem_name = '토마토' WHERE mem_no=1; 쿼리스트링- 삭제 :
	 * ?method=memberDelete DELETE FROM member0518 WHERE mem_no=:x
	 *****************************************************************************************************************/
	// get방식으로 요청을 받든 아니면 post방식으로 요청을 받든 메소드를 하나로 통일하자
	// 메소드 오버라이딩을 이용하지 않고 사용자 정의 메소드를 추가하여서 처리를 해 본다
	// 파라미터로 주입받는 요청객체와 응답객체를 다른 메소드에 넘길 수 있다
	// @Override doService 앞에는 오버라이드 어노테이션을 쓸 수가 없어요
	// 왜냐면 HttpServlet이 제공하는 메소드에만 쓸 수 있으니까....(상속받아야됨)
	// 그럼 왜 서블릿이 되어야 하나요? - 웹 서비스를 제공해야 하니까(그래야 주문도 가능하고, 뭐든 할수 있다)
	//doService메소드에 @Override를 붙일 수 없는건 부모클래스가 정의하고 있지 않아서 입니다
	public void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//모든 요청은 doService로 들어오게 되고 입력인지 조회인지를 구분하는건 쿼리스트링의 method키값을 통해서 분기할 수 있다
		//키값:?method=memberSelect, method=memberInsert
		List<Map<String, Object>> mList = null;
		int result = -1;
		String methodName = req.getParameter("method");// memberSelect or memberInsert or memberUpdate or memberDelete
		//리턴타입과 파라미터 결정해 보기 - 대화 - 소스리뷰
		//지워야 한다 - 왜냐하면 입력, 수정, 삭제, 조건검색일때 모두 다른 케이스 이므로 조건절로 위치를 옮겼다
		//회원 조회 - 전체조회만 담당함 -> 혹시 조건검색이나 상세조회할 때도 아래 if문을 사용할 수 있는건가?
		if ("memberSelect".equals(methodName)) {// 조건검색 경우라면 나도 (사용자가 입력한 정보가 )필요해
			logger.info("memberSelect");
			// 여기에서는 mList가 null참조하는 상황 - 38
			logger.info("before : " + mList);// 아무것도 없음 {} []
			HashMap<String, Object> pMap = new HashMap<>();
			HashMapBinder hmb = new HashMapBinder(req);
			hmb.bind(pMap);
			mList = memberDao.memberSelect(pMap); // 이 메소드 안에서 오라클 서버 경유한 경우라면 더 이상이 null이 아님
			req.setAttribute("mList", mList);
			RequestDispatcher view = req.getRequestDispatcher("memberList.jsp");
			view.forward(req, resp);
			// [] - List -> ArrayList{List인터페이스이고 ArrayList구현체 클래스임}
			// {} - Map<String, Object>
			logger.info("after : " + mList);// [{},{},{},{}] - 객체배열 형태임
			
		}// end of 회원조회
		else if ("memberDetail".equals(methodName)) {// 조건검색 경우라면 나도 (사용자가 입력한 정보가 )필요해
			logger.info("memberDetail");
			// 여기에서는 mList가 null참조하는 상황 - 38
			logger.info("before : " + mList);// 아무것도 없음 {} []
			HashMap<String, Object> pMap = new HashMap<>();
			HashMapBinder hmb = new HashMapBinder(req);
			hmb.bind(pMap);
			mList = memberDao.memberSelect(pMap); // 이 메소드 안에서 오라클 서버 경유한 경우라면 더 이상이 null이 아님
			req.setAttribute("mList", mList);
			RequestDispatcher view = req.getRequestDispatcher("memberDetail.jsp");
			view.forward(req, resp);
			// [] - List -> ArrayList{List인터페이스이고 ArrayList구현체 클래스임}
			// {} - Map<String, Object>
			logger.info("after : " + mList);// [{},{},{},{}] - 객체배열 형태임
			
		}// end of 회원조회
		// 회원 등록
		// 사용자가 입력한 값을 파라미터를 통해서 넘겨야 합니다.
		// 쿼리문을 작성한 이유는 화면은 없지만 쿼리문을 보고 사용자가 입력한 정보들을 생각(상상, 예측)해 보자
		// insert into member0518(mem_no,mem_id, mem_pw,mem_name)
		// values(3,'banana','345','나초보');
		else if ("memberInsert".equals(methodName)) {
			//자바스크립트에는 List나 Map이 없어요 -> 배열
			//공통코드에 HashMap의 저장소 주소번지 원본 (-> 복사본 -> 깊은복사{복사본}, 얕은복사{원본이 바뀐다})
			HashMap<String, Object> pMap = new HashMap<>();
			//req.getParameter반복(while문)되는 것을 해결해줌
			//req.getParameter("mem_id");//<input name='mem_id'>
			//req.getParameter("mem_pw");//<input name='mem_pw'>
			//req.getParameter("mem_name");//<input name='mem_name'>
			HashMapBinder hmb = new HashMapBinder(req);
			hmb.bind(pMap);//요기에 넘김
			result = memberDao.memberInsert(pMap);
			//위치를 선택할 수 있다 - 잘하는 사람
			if(result == 1) {
				resp.sendRedirect("/member/memberCRUD?method=memberSelect");
			} else {
				System.out.println("가입 실패했어요!!");
			}
		}//end of 회원등록
		// 회원 수정
		else if ("memberUpdate".equals(methodName)) {
			HashMap<String, Object> pMap = new HashMap<>();
			HashMapBinder hmb = new HashMapBinder(req);
			hmb.bind(pMap);
			result = memberDao.memberUpdate(pMap);
			if(result == 1) {
				resp.sendRedirect("/member/memberCRUD?method=memberSelect");
			} else {
				System.out.println("가입 실패했어요!!");
			}

		}//end of 회원수정
		// 회원 삭제
		// DELETE FROM member0518 WHERE mem_no=:x 숫자타입 number(5)
		else if ("memberDelete".equals(methodName)) {
			// 사용자가 선택한 회원일련번호
			String mem_no = req.getParameter("mem_no");// 문자열
			int user_no = 0;
			result = 0;
			user_no = Integer.parseInt(mem_no);// 1저장
			logger.info("사용자가 삭제를 선택한 회원일련번호 - " + user_no);
			//저장 버튼을 눌렀나?
			//서버를 재기동 해서 확인할 것
			//MemberDao에서 리턴값으로 1을 받아오지 못하는 사람
			result = memberDao.memberDelete(user_no);
			if(result == 1) {
				resp.sendRedirect("/member/memberCRUD?method=memberSelect");
			} else {
				System.out.println("삭제 실패했어요!!");
			}
		} // end of 회원 삭제
		// 우편번호 검색기 구현
		else if("zipcodeList".equals(methodName)) { // 로그를 많이 출력해 보기, 확인하기
			// 아래 로그를 출력하라면 URL을 어떻게 작성해야 될까 -> /member/memberCRUD?method=zipcodeList
			logger.info("zipcodeList");
			List<Map<String, Object>> zList = new ArrayList<>(); // NullPointerException 안 뜨게 하기 위해서 ArrayList로 해줌
			Map<String, Object> pMap = new HashMap<>();	// <input type="text" id="dong"> pMap.put("dong", "가산동");
			HashMapBinder hmb = new HashMapBinder(req);
			hmb.bind(pMap);
			
			// insert here - 오라클 서버를 경유하는 코드 추가
			// 왜 여기인가? - 사용자가 입력한 동이름이 pMap에 담겨야 하니까
			// 왜 setAttribute 앞에 와야 하나? 입력받은 동이름으로 조회된 결과를 쥐고 있어야 request객체에 담을 수 있기 때문
			// 단위테스트 하는 방법에 대해서도 말해보기
			zList = memberDao.zipcodeList(pMap); // pMap 안에는 화면{zipcodeSearch.jsp}에서 입력한 dong이름이 있어야 함
			
			req.setAttribute("zList", zList);	// 첫 번째 자리는 Stringm 두 번째 파라미터에는 주소번지는 문자열 상수이기 때문에 ""붙이면 안 됨
			RequestDispatcher view = req.getRequestDispatcher("zipcodeSearch.jsp");
			view.forward(req, resp);
			
		}
		// mList = memberDao.memberList(); - CRUD를 조건문으로 분기하면서 memberSelect영역으로 이관했음
		//forward를 연속해서 두 번 요청하게 되면 서버에러(500번을 던짐)가 발생함
		
	}
	// Restful API에서 리소스에 대한 전송하는 방식을 제안하고 있다. - doGet, doPost 선언 해두었다.
	// 파라미터를 통해서 톰캣 서버로 부터 주입 받은 요청객체와 응답객체를 사용자 정의 메소드의 파라미터로 호출하여 넘긴다
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//모든 요청은 doGet 혹은 doPost로 들어온다
		//둘 중에서도 단위테스트(디폴트로 호출)가 가능한 건 doGet이다
		//그 메소드 내부에서 doService호출한다
		//doGet의 파라미터로 주입받은 요청객체와 응답객체가 메소드의 파라미터로 (doGet이 주입받은 원본이)전달되는 것이다.
		doService(req, resp);//doService메소드 호출하기
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}
}