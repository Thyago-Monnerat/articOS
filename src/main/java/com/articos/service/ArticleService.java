package com.articos.service;

import com.articos.dto.ArticleDto;
import com.articos.model.ArticleModel;
import com.articos.model.UserModel;
import com.articos.repository.ArticleRepository;
import com.articos.repository.UserRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;
    private UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public ArticleModel createArticle(ArticleDto articleDto) {

        if (articleRepository.existsByTitle(articleDto.title())) {
            throw new DuplicateKeyException("Article already exists");
        }

        if (userRepository.existsById(articleDto.userRef())) {
            ArticleModel article = new ArticleModel();
            Optional<UserModel> user = userRepository.findById(articleDto.userRef());

            article.setAuthor(user.orElseThrow());
            article.setTitle(articleDto.title());
            article.setContent(articleDto.content());

            return articleRepository.save(article);
        } else {
            throw new DuplicateKeyException("User not found");
        }
    }

    public Optional<ArticleModel> findById(UUID id) {
        return articleRepository.findById(id);
    }

    public List<ArticleModel> getAllArticles() {
        return articleRepository.findAll();
    }

    public void deleteById(UUID id) {
        articleRepository.deleteById(id);
    }
}
