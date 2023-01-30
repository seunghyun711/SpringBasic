package com.example.firstproject.ioc;

public class IngredientFactory {

    public Ingredient get(String menu) {
        switch (menu){
            case "돈가스":
                return new Pork("맷돼지 등심");
            case "스테이크":
                return new Beef("염소 꽃등심");
            case "신호등 치킨":
                return new Chicken("국내산 10호 닭");
            default:
                return null;
        }
    }
}
