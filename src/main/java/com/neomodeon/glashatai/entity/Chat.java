package com.neomodeon.glashatai.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private UUID chatId;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();

    private void addMessage(Message message) {
        this.messages.add(message);
        this.setChatId(this.chatId);
    }
}
