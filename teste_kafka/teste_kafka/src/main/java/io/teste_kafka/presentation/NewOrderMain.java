package io.teste_kafka.presentation;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(var dispatcher = new KafkaDispatcher()) {
            for (var i = 0; i < 50; i++) {

                var chave = UUID.randomUUID().toString();
                var value = chave + ", id_user, valor_compra";
                dispatcher.send("TOPICO_TESTE_KAFKA", chave, value);

                var email = "Recebemos a ordem, obrigado! Está em processamento.";
                dispatcher.send("ECOMMERCE_SEND_EMAIL", chave, email);
            }
        }
    }
}

