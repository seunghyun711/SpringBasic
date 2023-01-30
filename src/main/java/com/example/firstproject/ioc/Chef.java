package com.example.firstproject.ioc;

public class Chef {
    // 셰프는 식재료 공장을 알고있음
    private IngredientFactory ingredientFactory;

    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        // 재료 준비
        // 아래 방식은 pork에 대한 내용이 필요하면 Pork 인스턴스를 생성해야하고, Beef에 대한 내용이 필요하면 Beef인스턴스를 직접 작성해야 한다.
//        Pork pork = new Pork("한돈 등심");
//        Beef beef = new Beef("한우 꽃등심");
        Ingredient ingredient = ingredientFactory.get(menu);

        // 요리 반환
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
