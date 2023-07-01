package com.ankieu.webfluxdemo;

import com.ankieu.webfluxdemo.dto.MultiplyRequestDTO;
import com.ankieu.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec08AttributeTest extends BaseTest{

    @Autowired
    private WebClient webClient;

    @Test
    public void attributeTest(){
        Mono<Response> responseMono = webClient
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(buildDTO(5, 2))
//                .attribute("auth", "oauth")
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    private MultiplyRequestDTO buildDTO(int first, int second) {
        MultiplyRequestDTO dto = new MultiplyRequestDTO();
        dto.setFirst(first);
        dto.setSecond(second);
        return dto;
    }
}
