package com.example.demo.pojo1;

public class UrlPattern {

	public static void main(String[] args) {
		String url = "/notice/noticeList.pj1";
		//split의 소유주는 String 클래스이고 리턴타입은 String[]이고, 파라미터에는 String 넣어야 한다.
		//파라미터와 리턴타입을 맞추는 것 만으로도 컴파일을 완성함 - 시행착오법 적용해 볼 수 있다 - 권장하지 않아
		String upmu[] = url.split("/");//api를 볼 수 있는 사람
		//개선된 for문은 전체를 모두 출력하거나 돌릴 때
		//첫번째는 제네릭타입이나 배열의 내부 타입을 적음, 두번째는 배열이나 컬렉션의 주소번지를 적음
		for(String str:upmu) {
			System.out.println(str);
			if("notice".equals(str)) {
				//인서턴스화 -> 개발자가 직접 함 -> 자원관리도 개발자가 해야함 -> 그러나 스프링 자동으로 해줌
				//NoticeController noticeController = new NoticeController();
			}
			else if("board".equals(str)) {
				//인스턴스화 -> 개발자가 직접 함 -> 자원관리도 개발자가 해야함 -> 그러나 스프링 자동으로 해줌
				//BoardController boardController = new BoardController();
			}
		}
	}

}
