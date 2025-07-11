package com.neomodeon.glashatai.service.serviceImpl;

import com.neomodeon.glashatai.dto.CreateMessageRequest;
import com.neomodeon.glashatai.dto.CreateMessageResponse;
import com.neomodeon.glashatai.dto.GetMessagesResponse;
import com.neomodeon.glashatai.entity.Message;
import com.neomodeon.glashatai.repository.MessagesRepository;
import com.neomodeon.glashatai.repository.projection.MessageSummary;
import com.neomodeon.glashatai.security.JwtService;
import com.neomodeon.glashatai.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService implements MessagesService {
    private final MessagesRepository messagesRepository;
    private final JwtService jwtService;

    @Override
    public CreateMessageResponse createMessage(CreateMessageRequest messageRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("Пользователь не авторизован!");
        }

        Message newMessage = new Message();
        newMessage.setMessage(messageRequest.message());
        newMessage.setSenderUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        //Получение UUID пользователя, и его передача в новую сущность
        newMessage.setSenderId(jwtService.extractUuid(authentication));

        messagesRepository.save(newMessage);

        return new CreateMessageResponse(
                newMessage.getMessage(),
                newMessage.getSenderUsername(),
                newMessage.getCreatedAt()
        );
    }

    @Override
    public GetMessagesResponse getMessages(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending());

        Page<MessageSummary> messages = messagesRepository.findAllBy(pageable);

        return new GetMessagesResponse(messages);
    }
}
