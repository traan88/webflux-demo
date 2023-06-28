package com.ankieu.webfluxdemo;

import com.ankieu.webfluxdemo.dto.InputFailedValidationResponse;
import com.ankieu.webfluxdemo.dto.Response;
import com.ankieu.webfluxdemo.exception.InputValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec06ExchangeTest extends BaseTest{

    @Autowired
    private WebClient webClient;

    //exchange = retrieve + additional info http status code
    @Test
    public void badRequestTest(){
        Mono<Object> responseMono = webClient.
                get()
                .uri("reactive-math/square/{input}/throw", 5)
                .exchangeToMono(this::exchange)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    private Mono<Object> exchange(ClientResponse cr){
        if (cr.rawStatusCode() == 400)
            return cr.bodyToMono(InputFailedValidationResponse.class);
        else
            return cr.bodyToMono(Response.class);
    }
}
