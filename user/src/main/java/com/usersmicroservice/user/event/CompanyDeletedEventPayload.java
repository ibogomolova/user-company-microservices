package com.usersmicroservice.user.event;

import java.time.Instant;
import java.util.UUID;

public record CompanyDeletedEventPayload(UUID companyId, Instant deletedAt) {
}
