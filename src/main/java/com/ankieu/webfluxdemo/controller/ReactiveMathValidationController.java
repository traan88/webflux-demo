package com.ankieu.webfluxdemo.controller;

import com.ankieu.webfluxdemo.dto.MultiplyRequestDTO;
import com.ankieu.webfluxdemo.dto.Response;
import com.ankieu.webfluxdemo.exception.InputValidationException;
import com.ankieu.webfluxdemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathValidationController {

    @Autowired
    private ReactiveMathService mathService;

    @GetMapping("square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable int input) {
        if (input < 10 || input > 20) {
            throw new InputValidationException(input);
        }
        return mathService.findSquare(input);
    }

    @GetMapping("square/{input}/mono-error")
    public Mono<Response> findSquareMono(@PathVariable int input) {
        return Mono.just(input)
                .handle((integer, sink) -> {
                    if (integer >= 10 && integer <= 20)
                        sink.next(integer);
                    else
                        sink.error(new InputValidationException(integer));

                })
                .cast(Integer.class)
                .flatMap(i -> mathService.findSquare(i));
    }

    @GetMapping("square/{input}/assignment")
    public Mono<ResponseEntity<Response>> findSquareAssignment(@PathVariable int input) {
        return Mono.just(input)
                .filter(i -> i >= 10 && i <= 20)
                .flatMap(i -> mathService.findSquare(i))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
