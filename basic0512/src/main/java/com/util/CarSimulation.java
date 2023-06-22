package com.util;
class Car {
	int speed = 0;
	//public abstract void go();//추상메소드
	public void stop() {
		System.out.println("5번 라인(Car클래스의) - stop");
		if(speed>0) {
			speed = speed -1;
		}
		else {
			speed = 0;
		}
	}
}
//A is a B 소나타는 자동차이다
//Sonata is a Car
//520! is a Car
class Sonata extends Car{

//	@Override
//	public void go() {
//		
//	}
	@Override
	public void stop() {
		System.out.println("26번 라인(Sonata클래스의) - stop");
		if(speed>0) {
			speed = speed -2;
		}
		else {
			speed = 0;
		}
	}
	
}

public class CarSimulation {

	public static void main(String[] args) {
		//추상클래스와 인터페이스는 반드시 구현체 클래스가 있어야 함 - 스프링 프레임워크가 추구하는 코딩방향
		//단독으로 인스턴스화 할 수 없다
		Car meCar = new Car();
		Car myCar = new Sonata();
		myCar.stop();
		meCar.stop();
		Sonata herCar = new Sonata();
		Car himCar = null;
		Sonata yourCar = null;
		//형전환연산자(캐스팅연산자)을 통해서 강제로 형전환을 해도 런타입시에는 결국 망한다 - Exception이 발동
		//yourCar = (Sonata)myCar; //실행시 오류 발생
		yourCar = herCar;
	}

}
