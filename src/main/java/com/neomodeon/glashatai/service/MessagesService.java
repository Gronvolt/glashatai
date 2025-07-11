package com.neomodeon.glashatai.service;

import com.neomodeon.glashatai.dto.CreateMessageRequest;
import com.neomodeon.glashatai.dto.CreateMessageResponse;
import com.neomodeon.glashatai.dto.GetMessagesResponse;

public interface MessagesService {
    CreateMessageResponse createMessage(CreateMessageRequest message);
    GetMessagesResponse getMessages(int page, int size);
}
