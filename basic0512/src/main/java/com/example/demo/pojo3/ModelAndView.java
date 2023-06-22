package com.example.demo.pojo3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
/*
 * spring4.0 버전까지 응답페이지 처리시에 활용됨
 * spring boot에 와서는 Model이나 ModelMap으로(메소드의 파라미터로 제공) 대체되었다.
 * Model, ModelMap은 스프링에서 ui지원하는 객체임
 * ModelAndView의 scope(page|request|session|application)가 request였다
 * 응답페이지의 위치는 WEB-INF/views/XXX.jsp
 * 접두어 /WEB-INF/views/
 * 접미어 .jsp 붙여서 응답 url패턴을 완성
 * 예) /WEB-INF/views/화면이름.jsp
 * 기억할 부분 - 위에서 만들어진 URL 패턴(접두어와 접미어가 붙음)으로 직접 호출할 수 없다( 404번 에러 발생)
 * 그래서 내부에서 호출하는 건 가능하다
 * 
 *  클래스 설계를 체험하기
 *  - 요청 객체가 필요하다 - 왜냐면 spring에서 ModelAndView는 scope가 request이니까......
 *  요청객체는 서블릿에게만 톰캣이 제공해줌 - 그럼 우리는 어떻게 가져와야 할까?
 *  해결방법 - 생성자의 파라미터를 통해서 해결함
 */
public class ModelAndView {
	Logger logger = Logger.getLogger(ModelAndView.class);
	//전변인 req는 언제 초기화가 되는 걸까? - 생성자의 파라미터를 통해서 받아옴
	HttpServletRequest req = null;
	//출력으로 나갈(응답) 화면의 이름을 담기
	String viewName = null;//deptList
	List<Map<String,Object>> reqList = new ArrayList<>();
	public ModelAndView() {} //디폴트 생성자는 생략이 가능하다 왜냐면 JVM이 만들어 줄 수 있으니까....
	//나는 서블릿이 아니라서 직접 가질수 없다 - 생성자 호출시 파라미터로 받아와야 함
	public ModelAndView(HttpServletRequest req) {
		this.req = req;
	}
	public String getViewName() {
		return viewName;
	}
	//아래 메소드가 호출되면 전변인 viewName에 파라미터로 넘어온 값이 담김
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public void addObject(String name, Object obj) {
		Map<String,Object> pMap = new HashMap<>();
		//insert here
		pMap.put(name,  obj);
		req.setAttribute(name, obj);;
		reqList.add(pMap);
	}//end of addObject
}














