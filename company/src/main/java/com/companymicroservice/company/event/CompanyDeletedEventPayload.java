package com.companymicroservice.company.event;

import java.time.Instant;
import java.util.UUID;

public record CompanyDeletedEventPayload(UUID companyId, Instant deletedAt) {
}
