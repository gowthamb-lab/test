package org.example.configurations.kafka.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.model.entity.Administrator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class kafkaProducerConfigurations {
    @Value("${spring.kafka.producerService.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producerService.client-id}")
    private String clientId;

    @Value("${spring.kafka.producerService.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producerService.value-serializer}")
    private String valueSerializer;

    @Value("${spring.kafka.producerService.acks}")
    private String acks;

    @Value("${spring.kafka.producerService.retries}")
    private String retries;

    @Value("${spring.kafka.producerService.batch-size}")
    private String batchSize;

    @Value("${spring.kafka.producerService.buffer-memory}")
    private String bufferMemory;

    @Value("${spring.kafka.producerService.linger-ms}")
    private String lingerMs;

    @Value("${spring.kafka.producerService.enable-Idempotence}")
    private String enableIdempotence;

    @Value("${spring.kafka.producerService.transactionalId}")
    private String transactionalId;


    /**
     *
     * kafkaTemplate for String
     *
     *
     * */
    @Bean
    public ProducerFactory<String, String> producerFactoryString() throws UnknownHostException {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.CLIENT_ID_CONFIG, clientId + "_" + InetAddress.getLocalHost().getHostName() + "string");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.ACKS_CONFIG, acks);
        configProps.put(ProducerConfig.RETRIES_CONFIG, retries);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotence);
        configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, transactionalId);

    DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(configProps);
        if (transactionalId != null) {
        factory.setTransactionIdPrefix(transactionalId + "_" + InetAddress.getLocalHost().getHostName());
        factory.setProducerPerConsumerPartition(false);
    }

        return factory;
}

    @Bean
    public KafkaTemplate<String, String> kafkaTemplateString() throws UnknownHostException {
        return new KafkaTemplate<>(producerFactoryString());
    }

    /**
     *
     * kafkaTemplate for Json
     *
     * */
    @Bean
    public ProducerFactory<String, Administrator> producerFactoryJson() throws UnknownHostException {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.CLIENT_ID_CONFIG, clientId + "_" + InetAddress.getLocalHost().getHostName() + "json");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        configProps.put(ProducerConfig.ACKS_CONFIG, acks);
        configProps.put(ProducerConfig.RETRIES_CONFIG, retries);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotence);
        configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, transactionalId);

        DefaultKafkaProducerFactory<String, Administrator> factory = new DefaultKafkaProducerFactory<>(configProps);
        if (transactionalId != null) {
            factory.setTransactionIdPrefix(transactionalId + "_" + InetAddress.getLocalHost().getHostName());
            factory.setProducerPerConsumerPartition(false);
        }

        return factory;
    }

    @Bean
    public KafkaTemplate<String, Administrator> kafkaTemplateJson() throws UnknownHostException {
        return new KafkaTemplate<>(producerFactoryJson());
    }


}
