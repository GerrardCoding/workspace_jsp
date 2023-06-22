package com.example.demo.pojo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
//나는 화면이 없이도 테스트가 가능하다 - MVC패턴
public class NoticeLogic {
	Logger logger = Logger.getLogger(NoticeLogic.class);
	//게으른 인스턴스화 - 필요할 때 생성자 호출해줄께 - 시점의 문제를 해결할 수 있니?
	//이른 인스턴스화 - 미리 생성자 호출해 둘께
	private NoticeDao noticeDao = new NoticeDao();
	//전체조회일때와 상세조회 일때 공유함 - noticeList, noticeDetail -> myBatis가 동적쿼리지원하니까....
	public List<Map<String, Object>> noticeList(Map<String, Object> pMap) {//select -> 유지 -> forward
		logger.info("noticeList");
		//아래에서 왜 생성자까지 호출하나요?
		//NoticeDao에서 생성해서 리턴타입으로 넘기면 되지 않나요?
		//그런데 만일 조회 결과가 없으면 null반환되고 그러면 NullPointerException을 만나게 되니까....
		List<Map<String,Object>> nList = null;
		nList = noticeDao.noticeList(pMap);
		//이렇게 하는 이유는 컨트롤러에 null이 전달되는 것을 방어하기 위함
		//왜요? - noticeList.jsp페이지가 터지니까....-> 500번 에러 메시지가 그대로 송출되니까...
		if(nList == null) {//그래서 나는 널 체크를 한다 - 간섭을 좀 하려구
			logger.info("nList==null일때");//select한 결과가 1건도 없으면 이리로 온다
			//조회결과가 만일 없어서 null이 넘어가는 것을 방어하자
			nList = new ArrayList<>();
		}
		
		return nList;//왜냐하면 select는 결과값이 존재하니까...
	}//end of noticeList

	public int noticeInsert(Map<String, Object> pMap) {
		logger.info("noticeInsert");
		int result = 0;
		result = noticeDao.noticeInsert(pMap);
		return result;
	}//end of noticeInsert

	public int noticeUpdate(Map<String, Object> pMap) {
		logger.info("noticeUpdate");
		int result = 0;
		result = noticeDao.noticeUpdate(pMap);
		return result;
	}//end of NoticeUpdate

	public int noticeDelete(Map<String, Object> pMap) {
		logger.info("noticeDelete");
		int result = 0;
		result = noticeDao.noticeDelete(pMap);
		return result;
	}//end of noticeDelete
}
