package com.example.demo.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.DBConnectionMgr;
import com.util.MyBatisCommonFactory;

public class MemberDao {
	Logger logger = Logger.getLogger(MemberDao.class);
	// getConnection메소드를 호출하려고 선언함
	DBConnectionMgr dbMgr = null;// 선언만 되었다. 객체가 생성되지 않았다(사용하면 NullPointerException발생)
	// 선언만 했다 - 재정의 , 재생성 해야 되니까(입력, 수정, 삭제, 조회)
	Connection con = null;
	// 전령의 역할 - 쿼리문(DML)을 오라클 서버에게 전달하고 처리를 요청(executeQuery():select-조회,
	// executeUpdate():INS|UPD|DEL):int한다
	PreparedStatement pstmt = null;
	// 오라클 살고 있는 커서를 움직이는 메소드를 재정의하고 있는 인터페이스 이다
	ResultSet rs = null;

	// 아래는 MemberDao클래스의 디폴트(파라미터가 한개도 없는) 생성자이다.
	public MemberDao() {
		dbMgr = DBConnectionMgr.getInstance();
	}

	public List<Map<String, Object>> memberList() {
		logger.info("memberList");
		List<Map<String, Object>> mList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT mem_id, mem_pw, mem_name FROM member0518");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Map<String, Object> rmap = null;
			// rs.next()호출하면 커서가 다음 행으로 가 버림
			// rs.next();
			// rs.next();
			// 위(while문앞에서)에서 두 번 rs.next하면 while문은 한 번만 실행됨.

			while (rs.next()) {// 2번째 행부터 조회가 된다
				rmap = new HashMap<>();
				rmap.put("mem_id", rs.getString("mem_id"));
				rmap.put("mem_pw", rs.getString("mem_pw"));
				rmap.put("mem_name", rs.getString("mem_name"));
				mList.add(rmap);
			}
			logger.info(mList);
		} catch (SQLException se) {
			logger.info(se.toString());
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return mList;
	}// end of memberList
//    public static void main(String[] args) {
//		MemberDao mDao = new MemberDao();
//		List<Map<String,Object>> mList = mDao.memberList();
//		System.out.println(mList);
//	}
	// 회원 목록 조회 구현

	public List<Map<String, Object>> memberSelect(HashMap<String, Object> pMap) {
		logger.info("memberSelect");
		// List<Map<String, Object>> mList = null;
		List<Map<String, Object>> mList = new ArrayList<>();// NullPointerException을 피할 수 있다
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT mem_no, mem_id, mem_name, mem_address");
		sql.append(" FROM member0518                                  ");
		// 한줄은 상관없지만 두 줄 이상은 앞에 띄어쓰기 해줘야 함

		// member/memberCRUD?method=memberSelect
		// &gubun=(mem_id|mem_name|mem_address)
		// &keyword=나|tom|가산동

		// 너 분류를 선택했어?
		if (pMap.get("gubun") != null && !"분류선택".equals(pMap.get("gubun"))) {
			if ("mem_id".equals(pMap.get("gubun"))) {// 너 뭘 찾고 싶어? 너 아이디로 검색할거야?
				sql.append(" WHERE mem_id LIKE '%'||?||'%'");// WHERE 앞에 띄어쓰기 반드시 할것
			} else if ("mem_name".equals(pMap.get("gubun"))) {
				sql.append(" WHERE mem_name LIKE '%'||?||'%'");
			} else if ("mem_address".equals(pMap.get("gubun"))) {
				sql.append(" WHERE mem_address");
			}
			// insert here - 조건 검색을 하는 코드 영역

		} else {
			logger.info("조건 검색이 아닐때");
			// 이 조건일 때는 그냥 전체 조회를 하면 되는거야. where절이 필요없는거야
		}
		// insert here
		if (pMap.get("mem_no") != null) {
			sql.append(" WHERE mem_no=?");
		}
		sql.append(" ORDER BY mem_no desc");
		try {
			// 물리적으로 떨어져 있는 오라클 서버에 연결하기
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());// 선언된 쿼리문을 읽기

			// 여기서? 자리에 치환될 값을 결정해 줘야해!! - 왜냐면??
			if (pMap.get("gubun") != null && !"분류선택".equals(pMap.get("gubun"))) {// 너 조건검색을 원해?? 그러면 ? 자리에 keyword를
																					// 치환해줘야되 아니면 500번 에러 뜰거야
				pstmt.setString(1, pMap.get("keyword").toString());
			}
			// 92번 코드가 만약에 추가되었다면.....?가 하나 있잖아. 그 자리에 값이 치환되어야 에러가 안난대 - SQLException 원인은
			// 오라클
			if (pMap.get("mem_no") != null) {// 위에 물음표 자리에 치환 해줘야하니 다시 적는다
				// 여기를 경유한다면 전체 조회가 아니라 상세조회이다 -> memberList.jsp로 가야하나?
				// 아니야 memberDetail.jsp가야지? - if문 하나를 가지고 두 가지 경우를 처리하려면.....
				// 결론 - MemberServlet.java에 상세보기를 위한 if문을 추가해 줘
				pstmt.setString(1, pMap.get("mem_no").toString());
			}
			rs = pstmt.executeQuery();
			Map<String, Object> rmap = null;
			while (rs.next()) {
				rmap = new HashMap<>();
				rmap.put("mem_no", rs.getInt("mem_no"));
				rmap.put("mem_id", rs.getString("mem_id"));
				rmap.put("mem_name", rs.getString("mem_name"));
				rmap.put("mem_address", rs.getString("mem_address"));
				mList.add(rmap);
			}
			logger.info(mList);// 4사람의 정보가 출력됨
		} catch (Exception e) {
			// try...catch바디에 문제가 없으면 실행기회는 없다
			logger.info("Exception : " + e.toString());// 익셉션의 이름을 출력해줌
		}
		// memberList()경유하지 않았다
		logger.info(mList.size());// 0
		return mList;
		// return null;
	}

	// 회원 가입 구현
	// MemberDao에는 request객체와 response객체가 없다
	// 나는 서블릿이 아니야 - Tomcat서버로부터 request와 response 객체를 주입 받을 수가 없어.....
	//
	public int memberInsert(HashMap<String, Object> pMap) {
		logger.info("memberInsert");
		logger.info("사용자가 입력한 값 : " + pMap);
		int result = 0;// 1이면 입력 성공 0이면 입력 실패
		StringBuilder sql = new StringBuilder();
		// insert문 삽입, sql문에 ; 조심하기 / seq_member0518.nextval: 시퀀스 입력
		sql.append("insert into member0518(mem_no,mem_id, mem_pw, mem_name) values(seq_member0518.nextval, ?, ?, ?)");// insert문
																														// 삽입
		try {
			con = dbMgr.getConnection();// 물리적으로 떨어진 서버와 연결통로 확보(myBatis생략 가능함)
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pMap.get("mem_id").toString());
			pstmt.setString(2, pMap.get("mem_pw").toString());
			pstmt.setString(3, pMap.get("mem_name").toString());
			result = pstmt.executeUpdate(); // int의 리턴타입은 result
			logger.info(result);// 1이면 가입성공 0이면 가입실패
		} catch (SQLException se) {
			logger.info(se.toString());
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return result;
	}

	// executeQuery():ResultSet - 커서관련있음 - select - commit이나 rollback의 대상이 아님
	// executeUpdate():int - INSERT | UPDATE | DELETE - commit 혹은 rollback대상임
	public int memberUpdate(HashMap<String, Object> pMap) {
		logger.info("memberUpdate");
		logger.info("사용자가 입력한 값 : " + pMap);
		int result = 0;

		// 쿼리문을 작성하기 - UPDATE문
		// 쿼리문 작성시 String 사용하지 않기 - 왜냐면 원본이 바뀌지 않아서 쿼리문이 늘어날 때마다 매번 새로운 객체를 만들어야 함 - 유지
		StringBuilder sql = new StringBuilder();// String보다는 이클래스가 합리적임 - append지원됨
		// 테이블이나 뷰가 존재하지 않습니다. - 오라클 서버의 ip주소와 포트번호
		// 부적합한 식별자 - 컬럼명 오타, setInt(값);오타
		// 열인덱스가 어쩌구저쩌구 - ?갯수와 매핑되는 숫자 안맞는것임
		sql.append("UPDATE member0518");
		sql.append("		  SET mem_id=?");
		sql.append("		  	    , mem_pw=?");
		sql.append("		  	    , mem_name=?");
		sql.append(" WHERE mem_no=?");
		try {
			// Connection은 인터페이스라서 단독으로 인스턴스화 불가함 - 반드시 구현체 클래스가 있어야 함
			// 인터페이스는 메소드의 리턴 타입으로 객체를 주입 받을 수도 있다.
			con = dbMgr.getConnection();
			// PreaparedStatement는 동적쿼리 처리하는 인터페이스 이다. 그래서 ?로 치환이 되어있다. - 바뀌는 부분들이.......
			// 그러니 먼저 update문을 스캔해서 ?가 몇개 있는지 어느 자리인지 파악을 먼저 해야 하지 않나?
			pstmt = con.prepareStatement(sql.toString());// ?가 몇개 있나?
			pstmt.setString(1, pMap.get("mem_id").toString());
			pstmt.setString(2, (String) pMap.get("mem_pw"));
			pstmt.setString(3, pMap.get("mem_name").toString());
			int user_no = 0;
			if (pMap.get("mem_no") != null) {
				user_no = Integer.parseInt(pMap.get("mem_no").toString());
			}
			pstmt.setInt(4, user_no);
			// result에 1을 왜 담아요?
			result = pstmt.executeUpdate();
		} catch (SQLException se) { // 무조건 만들어 놓기****************
			logger.info(se.toString());// Exception이름이 출력됨
			logger.info(se.getMessage());// Exception이름이 출력됨
		} catch (Exception e) {
			// stack영역에 쌓여 있는 에러메시지를 한꺼번에 볼 수 있음 - 기억
			// 주의할것:print메소드에 넣어서 출력하는게 아니다
			e.printStackTrace();// 소개 - 예외발생의 이력을 모두 다 출력해줌
		}
		return result;// 1이 나오거나 0이 나오거나 - n이 반환되는 경우는 드물지 않을까
	}

	// MemberServlet -> MemberDao
	/************************************************************************************
	 * 회원정보 삭제 구현하기
	 * 
	 * @param user_no - 삭제하고자 하는 회원번호 - 파라미터로 넘어오는 타입이 int이면 pstmt.setInt(0하고 만일
	 *                파라미터로 넘어오는 타입이 String이면 pstmt.setString()한다
	 * @return - 1이면 삭제 성공 0이면 삭제실패
	 *************************************************************************************/
	public int memberDelete(int user_no) {
		logger.info("memberDelete");
		logger.info("사용자가 입력한 값 : " + user_no);// 1

		int result = 0;// 1이면 입력 성공 0이면 입력 실패
		StringBuilder sql = new StringBuilder();
		// insert문 삽입, sql문에 ; 조심하기 / seq_member0518.nextval: 시퀀스 입력
		sql.append("DELETE FROM member0518 WHERE mem_no=?");// insert문 삽입
		try {
			con = dbMgr.getConnection();// 물리적으로 떨어진 서버와 연결통로 확보(myBatis생략 가능함)
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, user_no);
			result = pstmt.executeUpdate();// 1을 반환해서 result변수에 담아줌
			logger.info(result);// 1이면 가입성공 0이면 가입실패
		} catch (SQLException se) {
			logger.info(se.toString());
		} catch (Exception e) {
			logger.info(e.toString());
		}

		return result;// 여기 주의!!!
	}

	/*************************************************************************
	 * 우편번호를 조회하기
	 * 
	 * @param pMap - zipcodeSearch화면에서 사용자가 입력한 동이름을 받아서 담아둔 변수 - 주소번지
	 * @return
	 *************************************************************************/
	public List<Map<String, Object>> zipcodeList(Map<String, Object> pMap) {
		List<Map<String, Object>> zList = null;
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = MyBatisCommonFactory.getSqlSessionFactory();
			SqlSession sqlSession = sqlSessionFactory.openSession();
			zList = sqlSession.selectList("zipcodeList", pMap); 
			System.out.println(zList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zList;
	}

	public static void main(String[] args) {
		MemberDao mDao = new MemberDao();
		Map<String,Object> pMap = new HashMap<>();
		pMap.put("dong", "당산동");
		mDao.zipcodeList(pMap);
	}
}//// end of MemberDao
