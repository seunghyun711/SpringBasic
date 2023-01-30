package com.example.firstproject.ioc;

public class IngredientFactory {

    public Ingredient get(String menu) {
        switch (menu){
            case "돈가스":
                return new Pork("맷돼지 등심");
            case "스테이크":
                return new Beef("염소 꽃등심");
            default:
                return null;
        }
    }
}
