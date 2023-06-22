package com.example.demo.member;
import java.util.HashMap;
//인터페이스는 단독으로 인스턴스화를 할 수 없다.
//반드시 구현체 클래스가 있어야 메모리에 올릴수 있다.(변수호출, 메소드 호출 - 누린다)
import java.util.Map;

public class ListStep1 {

	public static void main(String[] args) {
		Map<String,Object> rMap = new HashMap<>();
		rMap.put("first","토마토");
		rMap.put("second","키위");
		System.out.println(rMap.get("first"));//토마토
		System.out.println(rMap);
	    rMap = new HashMap<>();
		rMap.put("first","수박");
		rMap.put("second","딸기");
		System.out.println(rMap.get("first"));//수박
	}

}
