package com.example.firstproject.ioc;

public class Chef {

    public String cook(String menu) {
        // 재료 준비
        // 아래 방식은 pork에 대한 내용이 필요하면 Pork 인스턴스를 생성해야하고, Beef에 대한 내용이 필요하면 Beef인스턴스를 직접 작성해야 한다.
//        Pork pork = new Pork("한돈 등심");
        Beef beef = new Beef("한우 꽃등심");

        // 요리 반환
        return beef.getName() + "으로 만든 " + menu;
    }
}
