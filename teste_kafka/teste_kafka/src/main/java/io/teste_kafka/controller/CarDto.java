package io.teste_kafka.controller;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class CarDto {

    private final String id;
    private final String model;
    private final String color;
}

