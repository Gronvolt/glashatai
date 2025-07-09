package com.neomodeon.glashatai.dto;

import java.time.LocalDateTime;

public record CreateMessageResponse(String message, String sender, LocalDateTime date) {
}
