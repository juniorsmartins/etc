package io.teste_kafka.presentation;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

class KafkaService<T> implements Closeable {

    private final KafkaConsumer<String, T> consumer;
    private final ConsumerFunction parse;

    KafkaService(String grupoId, String topic, ConsumerFunction parse, Class<T> type) {
        this(parse, grupoId, type);
        consumer.subscribe(Collections.singletonList(topic));
    }

    KafkaService(String grupoId, Pattern topic, ConsumerFunction parse, Class<T> type) {
        this(parse, grupoId, type);
        consumer.subscribe(topic);
    }

    KafkaService(ConsumerFunction parse, String grupoId, Class<T> type) {
        this.parse = parse;
        this.consumer = new KafkaConsumer<>(properties(type, grupoId));
    }

    void run() {
        while(true) {
            var registros = consumer.poll(Duration.ofMillis(100));
            if(!registros.isEmpty()) {
                System.out.println("Encontrei " + registros.count() + " registros!");
                for(var record : registros) {
                    parse.consume(record);
                }
            }
        }
    }

    private Properties properties(Class<T> type, String groupId) {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        properties.setProperty(GsonDeserializer.TYPE_CONFIG, type.getName());
//        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        return properties;
    }

    @Override
    public void close() {
        consumer.close();
    }
}

