package com.example.projectFiler.controller;

import com.example.projectFiler.entity.UserEntity;
import com.example.projectFiler.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private final UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // Get all users
  @GetMapping
  public List<UserEntity> getAllUsers() {
    return userRepository.findAll();
  }

  // Get a user by ID
  @GetMapping("/{id}")
  public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
    Optional<UserEntity> user = userRepository.findById(id);
    return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  // Create a new user
  @PostMapping("/createUser")
  public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
    UserEntity createdUser = userRepository.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }

  // Delete a user by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
    Optional<UserEntity> user = userRepository.findById(id);
    if (user.isPresent()) {
      userRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}