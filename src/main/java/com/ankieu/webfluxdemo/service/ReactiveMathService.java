package com.ankieu.webfluxdemo.service;

import com.ankieu.webfluxdemo.dto.MultiplyRequestDTO;
import com.ankieu.webfluxdemo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input) {
        return Mono.fromSupplier(() -> new Response(input * input));
    }

    public Flux<Response> multiplicationTable(int input) {
        return Flux.range(1 ,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Reactive math service is processing: " + i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequestDTO> requestDTOMono) {
        return requestDTOMono.map(dto -> dto.getFirst() * dto.getSecond())
                            .map(Response::new);
    }
}
