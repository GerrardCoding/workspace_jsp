package com.example.demo.pojo3;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/*
 * if문을 대신 할거야 - 왜냐면 직관적이지가 않으니까....
 * Controller controller = new MemberController();
 * controller = new OrderController();
 * controller = new PriceController();
 * 처리를 한다는 건 메소드를 정의하라
 */
public class HandlerMapping {
	static Logger logger = Logger.getLogger(HandlerMapping.class);
	//메소드 파라미터 자리는 지변이다.
	//메소드의 파라미터를 통해서 주소번지 즉 원본을 사용할 수 있다. - 생성자에서도 동일함
	public static Object getController(String[] upmu, HttpServletRequest req, HttpServletResponse res) {
		
		Object obj = null;
		Controller3 controller = null;
		if("board3".equals(upmu[0])) {
			logger.info("게시판 1-3버전");
			//조건을 만족한다면 객체 인스턴스화 - insert here{위치잡기}
			controller = new Board3Controller();
			//위에서 이번 요청에 대한 컨트롤러 클래스가 결정되었으니
			//그 다음에는 메소드 이름을 결정해야 한다.
			if("boardList".equals(upmu[1])) {
				//1-3번에서는 사용자가 정의하는 메소드도 req와 res를 사용할 수 있게 됨
				obj = controller.boardList(req,res);
				//위 메소드의 리턴타입으로 List<Map>을 받게 됨 - 왜냐하면 select이니깐 - 전체조회
				if(obj instanceof ModelAndView) {
					return (ModelAndView)obj;
				}//boardList메소드 실행 후에 리턴타입을 비교함
				else if(obj instanceof String) {
					return (String)obj;
				}//boardList메소드의 리턴타입이 String이니? - select임 - forward:board/boardList
				//pageMove[0] = forward
				//pageMove[1] = board/boardList
				//-> http://localhost:9000/board/boardList.jsp -> ViewResolver가 해줌
			}/////////////////// end of boardList()
		}///////////////////// end of 자유게시판 ///////////////////////
		else if("qna".equals(upmu[0])) {
			//insert here{위치잡기} - 인스턴스화
			controller = new QnAController();
		}//////////////////// /end of QnA 게시판 /////////////////////
		return obj;
		//return null;//null쓰면 NullPointerException 500번 에러 떠요
	}
}
