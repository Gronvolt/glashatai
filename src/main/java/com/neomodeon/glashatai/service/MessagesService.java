package com.neomodeon.glashatai.service;

import com.neomodeon.glashatai.dto.CreateMessageRequest;
import com.neomodeon.glashatai.dto.CreateMessageResponse;

public interface MessagesService {
    CreateMessageResponse createMessage(CreateMessageRequest message);
}
