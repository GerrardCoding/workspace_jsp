package com.example.demo.pojo2;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
//Member2Controller와는 다르게 COntroller를 implements하지 않았다.
//왜냐하면 로직은 서비스 계층으로 처리(비지니스 로직 - 업무처리순서)를 담당하는 클래스이기 때문에 그렇다
//결론적으로 request객체와 response객체가 필요없다.
//XXXLogic이 붙은 클래스는 회사에 부장님 담당하는 업무 - 문제터졌을 때 해결능력, 일하는 순서 - 선택과 판단
//어떠한 인터페이스나 추상클래스를 상속받지 않는 순수한(메소드 오버라이딩 책임이 없는) 자바코드를 작성할것
//파라미터의 타입과 갯수를 마음대로 결정해도 됨
//기능 구현에 집중하는 클래스임
//모델계층입니다. - 업무에 대한 절차, 규칙 지키면서 업무처리가 되어야 함 - 선택(if문, switch)과 결정(업무처리)
//3번째 관전포인트
//객체 주입 후 호출되는 메소드의 파라미터와 리턴타입 결정하기
public class Member2Logic extends Object {
	Logger logger = Logger.getLogger(Member2Logic.class);
	Member2Dao memberDao = new Member2Dao();
	public List<Map<String, Object>> memberList(Map<String,Object>pMap) {
		logger.info("memberList");
		List<Map<String,Object>> mList = null;
		mList = memberDao.memberList(pMap);//배달사고 체크, 리턴타입 체크
		return mList;
	}
	public int memberInsert(Map<String, Object> pMap) {
		logger.info("memberInsert");//호출 여부 출력
		logger.info(pMap);//화면에서 작성된 값이 잘 전달 되었나 여부
		int result = -1;//색인 -1:수정 성공, 0이면 수정실패
		result = memberDao.memberInsert(pMap);
		return result;
	}
	public int memberUpdate(Map<String, Object> pMap) {
		logger.info("memberUpdate");//호출 여부 출력
		logger.info(pMap);//화면에서 작성된 값이 잘 전달 되었나 여부
		int result = -1;//색인 -1:수정 성공, 0이면 수정실패
		result = memberDao.memberUpdate(pMap);
		return result;
	}
	public int memberDelete(Map<String, Object> pMap) {
		logger.info("memberDelete");
		logger.info(pMap);//화면에서 작성된 값이 잘 전달 되었나 여부
		int result = -1;//색인 -1:수정 성공, 0이면 수정실패
		result = memberDao.memberDelete(pMap);
		return result;
	}
}
