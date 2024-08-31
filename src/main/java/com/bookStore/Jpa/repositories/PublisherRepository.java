package com.bookStore.Jpa.repositories;

import com.bookStore.Jpa.model.PublisherModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<PublisherModel, UUID> {
}
