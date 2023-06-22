package com.example.demo.member;

import java.util.ArrayList;
import java.util.List;

import com.vo.Member0518;
//제네릭타입에 VO사용하기
//제네릭타입에 Map 사용하기
public class ListStep3 {
	//밖에 있는 타입이 List(Interface-클래스 설계관계된 키워드, Abstract - 추상클래스)
	//내 안에 있는 타입이 제네릭 타입이라고 한다
	List<Member0518> mList = new ArrayList<>();
	public void initList() {
		Member0518 mVO = new Member0518();//
		mVO.setMem_id("tomato");//@abcd1234.mem_id = > tomato
		mVO = new Member0518();//새로 주소번지가 따진다
		mVO.setMem_id("kiwi");//@abcd1235.mem_id => kiwi - 2년차 미만에게는 복잡한 알고리즘보다 이렇게 바뀌는것을 아는게 더 가치가 있다 그 후에 알아도 됨
		mVO = new Member0518();//새로 주소번지가 따진다
		mVO.setMem_id("apple");//@abcd1236.mem_id => apple
		mList.add(mVO);
	}
	public static void main(String[] args) {
		// non-static타입은 static영역에서 사용이 불가하다.
		// 단 인스턴스화를 하면 사용 가능하다
		ListStep3 ls3 = new ListStep3();
		ls3.initList();
		System.out.println(ls3.mList.size());// 0
		System.out.println(ls3.mList);
	}
}
