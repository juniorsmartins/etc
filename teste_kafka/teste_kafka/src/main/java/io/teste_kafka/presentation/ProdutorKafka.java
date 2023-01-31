package io.teste_kafka.presentation;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProdutorKafka {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var produtor = new KafkaProducer<String, String>(properties());
        var value = "id_compra, id_user, valor_compra";
        var registro = new ProducerRecord<>("TEMA_TESTE_KAFKA", value, value);
        produtor.send(registro, (data, ex) -> {
            if(ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println("Sucesso enviando: " + data.topic() + ":::partition " + data.partition() + "/ offset " +
                    data.offset() + "/timestamp " + data.timestamp());
        }).get();
    }

    private static Properties properties() {
        var propriedades = new Properties();
        propriedades.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        propriedades.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        propriedades.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return propriedades;
    }
}
