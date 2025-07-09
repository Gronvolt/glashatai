package com.neomodeon.glashatai.controller;

import com.neomodeon.glashatai.dto.CreateMessageRequest;
import com.neomodeon.glashatai.dto.CreateMessageResponse;
import com.neomodeon.glashatai.service.serviceImpl.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/messages")
public class MessagesController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<CreateMessageResponse> createMessage(@RequestBody CreateMessageRequest messageRequest) {
        return  ResponseEntity.ok(messageService.createMessage(messageRequest));
    }
}
