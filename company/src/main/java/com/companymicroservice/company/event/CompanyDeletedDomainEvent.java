package com.companymicroservice.company.event;

import org.springframework.context.ApplicationEvent;

import java.util.UUID;

/**
 * Событие для уведомления других компонентов/сервисов о том, что компания была удалена.
 * Используется в EventListener в UserService для удаления сотрудников.
 */
public class CompanyDeletedDomainEvent extends ApplicationEvent {
    private final UUID companyId;

    public CompanyDeletedDomainEvent(Object source, UUID companyId) {
        super(source);
        this.companyId = companyId;
    }
    public UUID getCompanyId() { return companyId; }
}