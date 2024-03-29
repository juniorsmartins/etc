package io.teste_kafka.producer;

import io.teste_kafka.controller.CarDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class CarProducer {

    private final String topic;
    private final KafkaTemplate<String, CarDto> kafkaTemplate;

    public CarProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, CarDto> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(CarDto carDto) {
        kafkaTemplate.send(topic, carDto).addCallback(
                success -> System.out.println("Mensagem enviada." + success.getProducerRecord().value()),
                failure -> System.out.println("Mensagem não enviada." + failure.getMessage())
        );
    }
}

