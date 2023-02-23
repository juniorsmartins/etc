package io.teste_kafka.controller;

import io.teste_kafka.producer.CarProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping(path = "/cars")
public class CarController {

    @Autowired
    private CarProducer carProducer;

    @PostMapping
    public ResponseEntity<CarDto> create(@RequestBody CarDto carDto) {
        var car = CarDto.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .color(carDto.getColor())
                .model(carDto.getModel())
                .build();

        this.carProducer.send(car);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(car);
    }
}

