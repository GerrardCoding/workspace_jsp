package com.example.demo.design;
//추상클래스로 설계하였다.
//추상클래스는 일반 메소드와 추상메소드 모두를 가질수 있어서
//추상메소드인 경우 일반메소드와 구분하기 위해서 abstract생략하면 안돼
//어떤 관계일때 혹은 어떤 기준으로 상속관계를 정하면 되나요?
//A is a B관계가 성립되면 둘은 서로 상속관계로 정의한다
public abstract class Duck {
	FlyBehavior flyBehavior = null;
	//추상메소드 - 결정되지 않앗다. 결정할 수 없다. 잘 모르겠다
	public abstract void display();//나는 추상메소드라고 해 日本語で書けるのかよ！！最高じゃん!!
	public void swimming() {//일반메소드다 왜냐면 좌중괄호 우중괄호가 있는 것 만으로 구현이 된다
		System.out.println("모든 오리는 물위에 뜬다");
	}
	
	
	
}
