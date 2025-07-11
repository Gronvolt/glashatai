package com.neomodeon.glashatai.repository.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface MessageSummary {
    UUID getUuid();
    UUID getSenderId();
    String getSenderUsername();
    String getMessage();
    LocalDateTime getCreatedAt();
}
