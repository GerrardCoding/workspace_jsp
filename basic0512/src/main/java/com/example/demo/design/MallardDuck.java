package com.example.demo.design;

//추상클래스를 상속 받아서 청동오리를 만들다
public class MallardDuck extends Duck {
	public MallardDuck() {
		flyBehavior = new FlyWithWings();
	}
	@Override
	public void display() {
		System.out.println("나는 청동오리입니다.");
	}

}
