package com.example.crm.repository;

import com.example.crm.entity.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository extends JpaRepository<OutboxEvent, String> {
}
