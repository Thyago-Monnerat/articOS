package com.articos.service;

import com.articos.model.UserModel;
import com.articos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(UUID id) {
        if(userRepository.findById(id).isPresent()){
            return Optional.of(userRepository.findById(id).get());
        }
        return Optional.empty();
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
