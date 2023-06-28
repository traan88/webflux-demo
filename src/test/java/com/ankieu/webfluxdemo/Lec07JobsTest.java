package com.ankieu.webfluxdemo;

import com.ankieu.webfluxdemo.dto.InputFailedValidationResponse;
import com.ankieu.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.URI;
import java.util.Map;

public class Lec07JobsTest extends BaseTest{

    @Autowired
    private WebClient webClient;

    //exchange = retrieve + additional info http status code
    @Test
    public void queryParamTest(){
/*        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/jobs/search?count={count}&page={page}")
                .build(10, 20);*/

        Map<String, Integer> map = Map.of(
                "count", 10,
                "page", 20
        );

        Flux<Integer> responseFlux = webClient.get()
                .uri(b -> b.path("/jobs/search").query("count={count}&page={page}").build(map))
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseFlux)
                .expectNextCount(2)
                .verifyComplete();
    }
}
