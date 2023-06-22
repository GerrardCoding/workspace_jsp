package com.example.demo.design;

public class DuckSimulation {
	//파라미터의 갯수가 다르거나 타입이 다르면 메소드 오버로딩 조건을 만족하니까 중복선언이 가능하다
	//메소드 이름이 같은데 둘은 서로 다른 메소드이다 - 메소드 오버로딩이다
	void methodA(WoodDuck wd) {
		System.out.println("methodA(WoodDuck)호출");
	}
	void methodA(Duck duck) {
		System.out.println("methodA(Duck)호출");
	}
	
//다형성이 뭐에요? 질문시 : 알고있었는데 긴장이 되서 설명을 잘 하기 어렵습니다.
	public static void main(String[] args) {//6번에 myDuck과 8번의 myDuck은 이름은 같지만 주소번지가 다르다
		//날고 날지 못하고는 해당 객체의 생성자에서 결정되었다
		//다형성이란 같은 이름의 메소드를 호출했는데 그 기능이 다르게 동작하는 것을 말한다
		//선언부의 타입과 생성부의 타입이 다를때 기대할 수 있다
		Duck myDuck = new MallardDuck(); //둘다 아빠가 같으니 위랑 밑에 형태로 생성 가능하다
		myDuck.flyBehavior.fly();//나는 날고 있어요. 출력
		myDuck = new WoodDuck();
		myDuck.flyBehavior.fly();//나는 날지 못합니다. 출력
		//static영역에서는 non-static 변수나 메소드를 호출할 수, 접근할 수 없다
		//내안에 있는 메소드이더라도 인스턴스화를 하면 호출할 수 있다 - 문제해결능력
		DuckSimulation ds = new DuckSimulation();
		ds.methodA(myDuck);
		WoodDuck himDuck = new WoodDuck();
		ds.methodA(himDuck);;
		
//		DuckSimulation wd = new DuckSimulation();//위에 DuckSimulation을 불러왓으니 굳이 불러올 필요가 없다
//		WoodDuck woodDuck = new WoodDuck();//WoodDuck 불러서 정의
//		wd.methodA(woodDuck);
		
	}

}
