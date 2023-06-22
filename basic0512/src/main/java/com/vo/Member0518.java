package com.vo;
//데이터 클래스 설계 - VO(Value Object)패턴
/*
 * 자바측								오라클측
 * String								char,varchar2 - 문자열
 * int, long							number(5) - 99,999자리
 * float, double					number(7.2) - 99999.99  - 전체자리수가 7자리
 * 
 * 클래스 선언 - 변수와 메소드를 가질 수 있다
 * 
 * 
 */
public class Member0518 {
	//전역변수 선언
	 private int		mem_no 		 	  = 0;//
	 private String mem_id      	    	  = "";//NullPointException 방지로 "" 함
	 private String mem_pw       		  = "";//
	 private String mem_name     	  = "";//
	 private String mem_zipcode  	  = "";// 010 -> 숫자타입선언 10만 저장됨
	 private String mem_address  	  = "";//
	 //디폴트 생성자는 생략 가능함 - JVM이 대신 제공하니까.......
	 public Member0518() { } 
	 public Member0518(int mem_no) {
		 //this가 없는 쪽은 지변이다
		 //this가 있는 쪽은 전변이다
		 this.mem_no = mem_no;
	 }
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	//읽어오기
	//파라미터는 필요없지만 대신에 리턴타입은 필요하다 
	public String getMem_zipcode() {
		return mem_zipcode;
	}
	//setter는 write or save
	//무엇을 저장해 줄까?
	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}
	public String getMem_address() {
		return mem_address;
	}
	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	} 
}
