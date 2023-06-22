package com.example.demo.pojo2;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class BoardLogic {
	Logger logger = Logger.getLogger(BoardLogic.class);
	//BoardLogic에서 BoardDao에 선언된 메소드를 호출할거야
	//전변으로 선언하였다. 선언하고 생성자 호출도 하였다. -> 이른 인스턴스화
	private BoardDao boardDao = new BoardDao();//이른 인스턴스화
	public List<Map<String, Object>> boardList(Map<String, Object> pMap) {
		logger.info("boardList");//
		List<Map<String,Object>> bList = null;
		bList = boardDao.boardList(pMap);//조회결과가 없더라도 myBatis레이어에서 주입
		return bList;
	}
	public int boardInsert(Map<String, Object> pMap) {
		logger.info("boardInsert");
		int result = 0;
		result = boardDao.boardInsert(pMap);
		return result;//상수 싫어요 변수 좋아요
	}
	public int boardUpdate(Map<String, Object> pMap) {
		logger.info("boardUpdate");
		int result = 0;
		result = boardDao.boardUpdate(pMap);
		return result;
	}
	public int boardDelete(Map<String, Object> pMap) {
		logger.info("boardDelete");
		int result = 0;
		result = boardDao.boardDelete(pMap);
		return result;
	}

}
