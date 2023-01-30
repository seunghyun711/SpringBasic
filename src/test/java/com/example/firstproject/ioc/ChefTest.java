package com.example.firstproject.ioc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChefTest {
    // IngredientFactory를 생성하고 Chef클래스에서 IngredientFactory를 주입받아 내부 코드 변경 없이 변경을 할 수 있다.
    @Test
    void 돈가스_요리하기(){
        // 준비
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef chef = new Chef(ingredientFactory); // DI
        String menu = "돈가스";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "맷돼지 등심으로 만든 돈가스";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 스테이크_요리하기(){
        // 준비
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef chef = new Chef(ingredientFactory);
        String menu = "스테이크";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "염소 꽃등심으로 만든 스테이크";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 신호등치킨_요리하기(){
        // 준비
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef chef = new Chef(ingredientFactory);
        String menu = "신호등 치킨";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "국내산 10호 닭으로 만든 신호등 치킨";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

}