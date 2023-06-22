package com.example.demo.member;

public class StringTest {

	public static void main(String[] args) {
		String insa = "hello";
		insa.replace('e', 'o');//원본이 안바뀐다
		System.out.println(insa);//hello
		insa = insa.replace('e', 'o');//새로운 객체 할당도미 - 이렇게 바꿀수 있다
		System.out.println(insa);//hollo
		StringBuilder sb = new StringBuilder("hello");
		sb.append(" world!!");//원본에 추가하기이다. 덮어쓰기가 아니라 
		System.out.println(sb.toString());//hello world!!
	}

}
