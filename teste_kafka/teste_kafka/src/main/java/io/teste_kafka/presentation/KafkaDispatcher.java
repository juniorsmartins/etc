package io.teste_kafka.presentation;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

class KafkaDispatcher implements Closeable {

    private final KafkaProducer<String, String> producer;

    KafkaDispatcher() {
        this.producer = new KafkaProducer<String, String>(properties());
    }

    private static Properties properties() {
        var propriedades = new Properties();
        propriedades.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        propriedades.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        propriedades.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return propriedades;
    }

    void send(String topic, String chave, String value) throws ExecutionException, InterruptedException {
        var registro = new ProducerRecord<>(topic, chave, value);
        Callback callback = (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println("Sucesso enviando: " + data.topic() + ":::partition " + data.partition() + "/ offset " +
                    data.offset() + "/timestamp " + data.timestamp());
        };
        producer.send(registro, callback).get();
    }

    @Override
    public void close() {
        producer.close();
    }
}

