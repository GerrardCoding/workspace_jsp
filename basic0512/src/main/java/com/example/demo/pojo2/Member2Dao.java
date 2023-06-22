package com.example.demo.pojo2;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

//오라클 서버와 연동하기
//입력|수정|삭제|조회 담당함
//XXXDao도 넓은 의미에서는 모델계층에 해당함
//XXXDao클래스가 있고 없고는 MVC패턴을 결정짓는 인자가 아니다.
//여기서는 왜 굳이 사용하나요? myBatis를 사용하니까 그래서 사용하자 - 제안
public class Member2Dao {
	Logger logger = Logger.getLogger(Member2Dao.class);
	//MyBatis Layer 설정 바탕으로 객체 주입이 필요하다
	SqlSessionFactory sqlSessionFactory = null;//오라클 서버 접속 담당
	SqlSession sqlSession = null;//커밋과 롤백 담당
	public Member2Dao() {//파라미터가 없는 생성자를 디폴트 생성자라고 한다.
		sqlSessionFactory = MyBatisCommonFactory.getSqlSessionFactory();
	}//end of Member2Dao
	public List<Map<String, Object>> memberList(Map<String, Object> pMap) {
		logger.info("memberList");
		List<Map<String,Object>> mList = null;
		try {
			//SqlSession을 생성하려면 먼저 SqlSessionFactory를 생성한다
			//어디서 했나 -> 생성자에서 진행함
			sqlSession = sqlSessionFactory.openSession();
			//첫번째 파라미터에는 id가 온다 - member.xml(내안에 DML있음)에 있다
			//두번째 파라미터는 사용자가 입력한 값을 쥐고 있다
			mList = sqlSession.selectList("memberList", pMap);
			//주의 : 커밋이나 롤백의 대상이 아니다
			logger.info(mList);
		} catch (Exception e) {
			e.printStackTrace();//에러메시지와 라인번호까지 같이 출력
		}
		return mList;
	}//end of memberList
	/********************************************************************************
	 * 회원가입 처리
	 * @param pMap - 화면에서 사용자가 입력한 회원정보를 받아옴
	 * @return 회원가입 성공시  1, 실패 0
	 * 작성자 : 나신입
	 * 작성일 : 2023년 6월 19일
	 * 내용 : 어떤 기능 구현하는 것인지를 적음
	 ********************************************************************************/
	public int memberInsert(Map<String, Object> pMap) {
		logger.info("memberInsert");
		int result = 0;
		sqlSession = sqlSessionFactory.openSession();
		try {
			result = sqlSession.insert("memberInsert", pMap);
			sqlSession.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}//end of memberInsert
	//회원 정보 수정 처리
	public int memberUpdate(Map<String, Object> pMap) {
		logger.info("memberUpdate");
		int result = 0;
		//mybatis가 제공하는 SqlSessionFactory는 물리적으로 떨어져 있는 오라클 서버와 연결
		sqlSession = sqlSessionFactory.openSession();
		try {
			//SqlSession객체는 SqlSessionFactory가 먼저 생성되어야 생성가능
			//역할 : selectList, insert, update, delete제공
			result = sqlSession.update("memberUpdate", pMap);
			//insert, update, delete는 커밋과 롤백에 대상이므로 반드시 커밋을 요청해야 물리적인 테이블 반영됨
			sqlSession.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}//end of memberUpdate
	//회원 정보 삭제 처리
		public int memberDelete(Map<String, Object> pMap) {
			logger.info("memberDelete");
			int result = 0;
			//mybatis가 제공하는 SqlSessionFactory는 물리적으로 떨어져 있는 오라클 서버와 연결
			sqlSession = sqlSessionFactory.openSession();
			try {
				//SqlSession객체는 SqlSessionFactory가 먼저 생성되어야 생성가능
				//역할 : selectList, insert, update, delete제공
				result = sqlSession.delete("memberDelete", pMap);
				//insert, update, delete는 커밋과 롤백에 대상이므로 반드시 커밋을 요청해야 물리적인 테이블 반영됨
				sqlSession.commit();
			} catch(Exception e) {
				e.printStackTrace();
			}
			return result;
		}//end of memberDelete
}
