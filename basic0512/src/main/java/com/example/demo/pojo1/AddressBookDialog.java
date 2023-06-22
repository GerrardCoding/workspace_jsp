package com.example.demo.pojo1;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class AddressBookDialog extends JDialog {
	//선언부
	//그럼에도 호출하면 어떻게 되는데? - NullPointerException이 발동할거야
	AddressBook aBook = null;//선언만함 - 아직 메모리에 로딩이 안됨 - 그래서 누릴수 없음, 호출할 수 없음
	
	//AddressBook aBook = new AddressBook();
	//생성자
	public AddressBookDialog() {
		initDisplay();
	}
	//화면 처리부
	public void initDisplay( ) {
		this.setTitle("나는 다이얼로그창");//창 이름
		this.setSize(300, 500);
		this.setVisible(false);
	}
	//24-25-13-14-17-18-19-20-15-26
	//
	/*
	public static void main(String[] args) {
		AddressBookDialog aDialog = new AddressBookDialog();//생성자 호출하기
		aDialog.setVisible(true);
	}
	*/
}
