package io.teste_kafka.presentation;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Pattern;

public class ConsumidorLogService {

    public static void main(String[] args) {

        var consumidor = new KafkaConsumer<String, String>(properties());
        consumidor.subscribe(Pattern.compile("ECOMMERCE.*"));

        while(true) {
            var registros = consumidor.poll(Duration.ofMillis(100));
            if(!registros.isEmpty()) {
                System.out.println("Encontrei " + registros.count() + " registros!");
                for(var record : registros) {
                    System.out.println("--------------------------------");
                    System.out.println("LOG: " + record.topic());
                    System.out.println(record.key());
                    System.out.println(record.value());
                    System.out.println(record.partition());
                    System.out.println(record.offset());
                }
            }
        }
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, ConsumidorLogService.class.getSimpleName());

        return properties;
    }
}
