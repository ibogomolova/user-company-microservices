package com.companymicroservice.company.event;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class CompanyDeletedEventKafkaPublisher {

    private final KafkaTemplate<String, CompanyDeletedEventPayload> kafkaTemplate;

    @Value("${app.topics.company-deleted}")
    private String topic;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCompanyDeleted(CompanyDeletedDomainEvent domainEvent) {
        var payload = new CompanyDeletedEventPayload(domainEvent.getCompanyId(), Instant.now());
        kafkaTemplate.send(topic, payload.companyId().toString(), payload);
    }
}
