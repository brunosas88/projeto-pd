package br.com.letscode.comprasvalidator.config;

import br.com.letscode.comprasvalidator.compra.dto.RequisicaoCompraDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("kafka.address")
    private String kafkaAddress;

    public ConsumerFactory<String, RequisicaoCompraDTO> consumerFactory() {

        JsonDeserializer<RequisicaoCompraDTO> deserializer = new JsonDeserializer<>(RequisicaoCompraDTO.class);
        deserializer.addTrustedPackages("*");
        deserializer.ignoreTypeHeaders();
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequisicaoCompraDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RequisicaoCompraDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


}
