package io.teste_kafka.presentation;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudeDetectorService {

    public static void main(String[] args) {
        var fraudeService = new FraudeDetectorService();
        try (var service = new KafkaService(FraudeDetectorService.class.getSimpleName(),
                "TOPICO_TESTE_KAFKA", fraudeService::parse)) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("--------------------------------");
        System.out.println("Processando..........:");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
        System.out.println("Ordem processada!");
    }
}

