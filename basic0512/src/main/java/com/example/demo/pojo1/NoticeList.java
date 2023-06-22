package com.example.demo.pojo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeList {
/*
 * 비교하기 - 관전포인트 - 학습목표
 * 
 */
	public static void main(String[] args) {
		List<Map<String,Object>> nList = null;
//		System.out.println(nList.size());//NullPointerException
		nList = new ArrayList<>();//이 때 비로서 Heap메모리 영역에 로딩이 된다. 하드디크가 아니라 램,  클래스는 힙
		//선언부와 생성부의 타입이 다를 때 다형성(폴리모피즘)을 적용할 수 있으니까
		//같은 메소드라 하더라도 주소번지에 생성된 객체가 어떤 타입인가에 따라서 기능이 다른것.
		Map<String,Object> rmap = new HashMap<>();//권장사항 인터페이스 = new 구현체 클래스();
		nList.add(rmap);//위치가 바뀌었다 - 적용이 되었다 - 왜? 얕은복사(원본이다), 깊은복사(새로운객체다)
		System.out.println(nList.size());//0 -> 1
		System.out.println(nList.get(0));//@abcd1234
		System.out.println(nList.get(0).get("n_no"));//10 ->> 0이 맞지 않을까? - 아냐 10이야 : null
		rmap.put("n_no",10);
		System.out.println(nList.get(0));//@abcd1234 {n_no=10}
		System.out.println(nList.get(0).get("n_no"));//10 ->> 0이 맞지 않을까? - 아냐 10이야
	}

}
