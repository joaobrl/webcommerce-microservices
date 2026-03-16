package com.petShop.NotificationService.infrastructure.messaging.notification.config;

import com.petShop.NotificationService.infrastructure.messaging.kafka.dto.BookingConfirmedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookingConfirmedEvent> kafkaListenerContainerFactory(
            ConsumerFactory<String, BookingConfirmedEvent> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, BookingConfirmedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
