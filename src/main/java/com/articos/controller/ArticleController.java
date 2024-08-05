package com.articos.controller;

import com.articos.dto.ArticleDto;
import com.articos.dto.ArticleIdDto;
import com.articos.model.ArticleModel;
import com.articos.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ArticleIdDto>> getAll() {

        List<ArticleIdDto> articleDtos = new ArrayList<>();
        articleService.getAllArticles().forEach(aDto -> articleDtos.add(new ArticleIdDto(aDto.getId(), aDto.getTitle(), aDto.getContent(), aDto.getAuthor().getId())));


        return ResponseEntity.ok(articleDtos);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ArticleIdDto> get(@PathVariable UUID id) {
        ArticleModel articleModel = articleService.findById(id).orElseThrow();
        ArticleIdDto articleIdDto = new ArticleIdDto(articleModel.getId() ,articleModel.getTitle(), articleModel.getContent(), articleModel.getAuthor().getId());
        return ResponseEntity.ok(articleIdDto);
    }

    @PostMapping("/add")
    public ResponseEntity<ArticleDto> add(@RequestBody ArticleDto articleDto) {
        try{
            articleService.createArticle(articleDto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(articleDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (articleService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        articleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
