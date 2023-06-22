package com.example.demo.pojo1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/*
 * 다이얼로그창이 닫힐 때 부모창에 메소드를 호출해야 한다 - 요구사항
 * 다이얼로그창에서 입력을 하고 입력이 성공(insert)하면 다이얼로그창은 닫히고
 * 부모창의 목록 화면을 새로고침 처리해야하 하는 상황이다.
 * 
 */
import javax.swing.JFrame;
//상속을 했더니 내가 정의하지 않은 setSize도 호출할 수 있잖아
@SuppressWarnings("serial")
public class AddressBook extends JFrame  implements ActionListener {
	//선언부
	JButton jbtn_ins = new JButton("입력");
	AddressBookDialog aDialog = new AddressBookDialog();
	//생성자 - 리턴타입이 없다
	public AddressBook() {//파라미터가 없는 생성자를 디폴트 생성자라 한다.
		//super();//JFrame생성자를 호출한것
		initDisplay();
	}
	public void getAddressList() {
		System.out.println("getAddressList");
	}
	//화면그리기
	public void initDisplay() {
		System.out.println("initDisplay");
		//this는 AddressBook가리킴 - 이벤트 처리를 담당하는 클래스업
		jbtn_ins.addActionListener(this);
		this.add("North",jbtn_ins);
		this.setSize(400, 500);
		this.setVisible(true);
	}
	//메인메소드 - 여기가 제일 먼저 실행됨
	public static void main(String[] args) {
		new AddressBook();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jbtn_ins) {//버튼이 눌렸다
			System.out.println("입력");
		}
	}

}
