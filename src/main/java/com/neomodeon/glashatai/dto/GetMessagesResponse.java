package com.neomodeon.glashatai.dto;

import com.neomodeon.glashatai.repository.projection.MessageSummary;
import org.springframework.data.domain.Page;

public record GetMessagesResponse(Page<MessageSummary> messages) {
}
