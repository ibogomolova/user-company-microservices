package com.usersmicroservice.user.config;

import com.usersmicroservice.user.event.CompanyDeletedEventPayload;
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
public class UserKafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap;

    @Bean
    public ConsumerFactory<String, CompanyDeletedEventPayload> companyDeletedConsumerFactory() {
        JsonDeserializer<CompanyDeletedEventPayload> jsonDeserializer =
                new JsonDeserializer<>(CompanyDeletedEventPayload.class, false);
        jsonDeserializer.addTrustedPackages("*");

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "user-service");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(
                props, new StringDeserializer(), jsonDeserializer
        );
    }

    @Bean(name = "companyDeletedKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, CompanyDeletedEventPayload> companyDeletedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CompanyDeletedEventPayload> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(companyDeletedConsumerFactory());
        return factory;
    }
}