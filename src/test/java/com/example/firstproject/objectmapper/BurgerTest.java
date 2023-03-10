package com.example.firstproject.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    @Test
    public void JSON을_자바_객체로_변환() throws JsonProcessingException{
        // 준비
        ObjectMapper objectMapper = new ObjectMapper();
        /*
        {
            "name" : "코트로 치즈 와퍼"  => 이 형식으로 나오게 만든다.
            "price : 7000
            "ingredients" : ["치즈", "패티", "양파"]
         }
         */
        // JsonNode를 만든다.
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "콰트로 치즈 와퍼");
        objectNode.put("price", 7000);

        ArrayNode arrayNode = objectMapper.createArrayNode();// JSON 배열을 만든다.
        arrayNode.add("치즈");
        arrayNode.add("패티");
        arrayNode.add("양파");

        objectNode.set("ingredients", arrayNode);
        String json = objectNode.toString();

        // 수행
        // readValue를 통해 burger를 만들때 디폴트 생성자가 호출되므로 Burger클래스에 @NoArgsContructor를 추가해 디폴트 생성자를 추가한다.
        Burger burger = objectMapper.readValue(json, Burger.class);

        // 예상
        List<String> ingredients = Arrays.asList("치즈","패티","양파");
        Burger expected = new Burger("콰트로 치즈 와퍼", 7000, ingredients);

        // 검증
        assertEquals(expected.toString(), burger.toString());
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
        System.out.println(burger.toString());
    }

}