package com.articos.controller;

import com.articos.dto.ArticleDto;
import com.articos.dto.UserDto;
import com.articos.model.ArticleModel;
import com.articos.model.UserModel;
import com.articos.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> get(@PathVariable UUID id) {
        UserModel userModel = userService.findById(id).get();
        if (userModel == null) {
            return ResponseEntity.notFound().build();
        }
        List<ArticleDto> articleDtos = new ArrayList<>();
        userModel.getArticles().forEach(aDto -> articleDtos.add(new ArticleDto(aDto.getTitle(), aDto.getContent(), userModel.getId())));
        UserDto userDto = new UserDto(userModel.getId(), userModel.getUsername(), articleDtos);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> userDtos = new ArrayList<>();


        for(UserModel u : userService.getAll()){
            List<ArticleDto> articleDtos = new ArrayList<>();
            u.getArticles().forEach(aDto -> articleDtos.add(new ArticleDto(aDto.getTitle(), aDto.getContent(), u.getId())));
            userDtos.add(new UserDto(u.getId(),u.getUsername(), articleDtos));
        }

        return ResponseEntity.ok(userDtos);
    }

    @PostMapping("/add")
    public ResponseEntity<UserModel> add(@RequestBody UserDto UserDto) {
        if (userService.existsByUsername(userModel.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(userService.save(userModel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (userService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
