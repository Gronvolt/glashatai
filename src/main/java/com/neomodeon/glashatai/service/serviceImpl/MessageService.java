package com.neomodeon.glashatai.service.serviceImpl;

import com.neomodeon.glashatai.dto.CreateMessageRequest;
import com.neomodeon.glashatai.dto.CreateMessageResponse;
import com.neomodeon.glashatai.entity.Message;
import com.neomodeon.glashatai.repository.MessagesRepository;
import com.neomodeon.glashatai.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService implements MessagesService {
    private final MessagesRepository messagesRepository;

    @Override
    public CreateMessageResponse createMessage(CreateMessageRequest messageRequest) {
        Message newMessage = new Message();
        newMessage.setMessage(messageRequest.message());
        newMessage.setSenderUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        //Получение UUID пользователя, и его передача в новую сущность
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        newMessage.setSenderId((UUID) authentication.getCredentials());

        messagesRepository.save(newMessage);

        return new CreateMessageResponse(
                newMessage.getMessage(),
                newMessage.getSenderUsername(),
                newMessage.getCreatedAt()
        );
    }
}
