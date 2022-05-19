package br.com.letscode.comprasvalidator.producer.service;

import br.com.letscode.comprasvalidator.compra.model.Compra;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService implements Subscriber<Compra> {

    private final KafkaTemplate<String, Compra> kafkaTemplate;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);

    }

    @Override
    public void onNext(Compra compra) {
        kafkaTemplate.send("COMPRA_VALIDADA", compra);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {
        log.info("Subscriber finalizado!");
    }
}
