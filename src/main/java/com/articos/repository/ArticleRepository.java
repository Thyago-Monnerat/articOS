package com.articos.repository;

import com.articos.model.ArticleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleModel, UUID>{

    boolean existsByTitle(String title);
}
