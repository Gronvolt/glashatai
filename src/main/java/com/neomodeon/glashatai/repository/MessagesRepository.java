package com.neomodeon.glashatai.repository;

import com.neomodeon.glashatai.entity.Message;
import com.neomodeon.glashatai.repository.projection.MessageSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessagesRepository extends JpaRepository<Message, UUID> {
    Page<MessageSummary> findAllBy(Pageable pageable);
}
