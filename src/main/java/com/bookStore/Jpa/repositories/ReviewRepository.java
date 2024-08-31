package com.bookStore.Jpa.repositories;

import com.bookStore.Jpa.model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewModel, UUID> {
}
