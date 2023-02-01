package com.example.firstproject.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {
    @Test
    public void 자바_객체를JSON으로_변환() throws JsonProcessingException {
        // 준비
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("치즈","패티","양파");
        Burger burger = new Burger("콰트로 치즈 와퍼", 7000, ingredients);

        // 수행
        String json = objectMapper.writeValueAsString(burger);

        // 예상
        String expected = "{\"name\":\"콰트로 치즈 와퍼\",\"price\":7000,\"ingredients\":[\"치즈\",\"패티\",\"양파\"]}";

        // 검증
        assertEquals(expected, json);
        // JsonNode를 사용하면 JSON값을 더 보기 쉽게 출력한다.
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }

}