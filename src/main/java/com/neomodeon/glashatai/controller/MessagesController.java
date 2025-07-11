package com.neomodeon.glashatai.controller;

import com.neomodeon.glashatai.dto.CreateMessageRequest;
import com.neomodeon.glashatai.dto.CreateMessageResponse;
import com.neomodeon.glashatai.dto.GetMessagesResponse;
import com.neomodeon.glashatai.service.serviceImpl.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/messages")
public class MessagesController {

    private final MessageService messageService;

    //сохранение сообщение в БД
    @PostMapping
    public ResponseEntity<CreateMessageResponse> createMessage(@RequestBody CreateMessageRequest messageRequest) {
        return  ResponseEntity.ok(messageService.createMessage(messageRequest));
    }

    //Получение истории сообщений
    @GetMapping
    public ResponseEntity<GetMessagesResponse> getMessages (@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "20") int size) {

        return ResponseEntity.ok(messageService.getMessages(page, size));
    }
}
