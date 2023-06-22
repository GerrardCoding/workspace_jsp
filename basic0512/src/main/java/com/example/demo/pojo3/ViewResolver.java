package com.example.demo.pojo3;

import org.apache.log4j.Logger;
/*
 * 클래스 설계시에 메소드에서 하는 역할은 생성자 안에서도 가능함(제어문사용, 메소드 호출, 변수선언...)
 * 같은 이름의 생성자를 여러개 선언할 수 있다. - 메소드 오버로딩이랑 같은 규칙이구나
 * POJO-1
 * POJO-2
 *  upmu[] - 0번방:업무이름(폴더명), 1번방: 메소드이름== mybatis 쿼리 id값
 *  pageMove[] - pageMove[0]=redirect or forward, pageMove[1]=boardList
 *  위 배열 모두를 누가 생성했나요? - FrontMVC, ActionServlet, Actionsupport - upmu.......
 *  reason : FrontMVC(참조), ActionServlet(c참조)upmu배열은 requestURI -> URL주소로 결정되는 값이다
 *  응답페이지 처리에 대한 책임도 이 클래스가 가져야하는거죠? - DispatcherServlt
 *  생성은 FrontMVC(1-1), ActionServlet(1-2), ActionSupport(1-3)가 그런데 사용은 ViewResolver가 사용할거니까
 *  생성자의 파라미터로 원본을(얕은복사)가져온다 - 인스턴스화(게으른 인스턴스화 or 이른 인스턴스화), 의존성 주입(Dependency Injection)
 * 이 클래스의 역할은? - 화면처리하기
 * 선택지가 2가지이다
 * 1) webapp 아래 배치된 경우
 * : 다시 두 가지로 나누어야 함 -> redirect, forward
 * 2) WEB-INF/views/아래 배치된 경우
 * 
 */
public class ViewResolver {
	Logger logger = Logger.getLogger(ViewResolver.class);
	public ViewResolver() {}
	public ViewResolver(String pageMove[]) {}
}
