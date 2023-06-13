package com.example.projectFiler.controller;

import com.example.projectFiler.entity.DirectoryEntity;
import com.example.projectFiler.entity.UserEntity;
import com.example.projectFiler.repository.DirectoryRepository;
import com.example.projectFiler.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/directory")
public class DirController {
  private final DirectoryRepository directoryRepository;
  private final UserRepository userRepository;

  @Autowired
  public DirController(
    DirectoryRepository directoryRepository,
    UserRepository userRepository
  ) {
    this.directoryRepository = directoryRepository;
    this.userRepository = userRepository;
  }

  @PostMapping
  public ResponseEntity<DirectoryEntity> createDirectory(
    @RequestBody DirectoryEntity directory
  ) {
    DirectoryEntity createdDirectory = directoryRepository.save(directory);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdDirectory);
  }

  @PostMapping("/user/{userId}")
  public ResponseEntity<DirectoryEntity> createDirectoryByUser(
    @PathVariable Long userId,
    @RequestBody DirectoryEntity directory
  ) {
    Optional<UserEntity> user = userRepository.findById(userId);
    if (user.isPresent()) {
      directory.setUser(user.get());
      DirectoryEntity createdDirectory = directoryRepository.save(directory);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdDirectory);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public List<DirectoryEntity> getAllDirectories() {
    return directoryRepository.findAll();
  }

  @GetMapping("/{id}")
  public Optional<DirectoryEntity> getDirectoryById(@PathVariable Long id) {
    return directoryRepository.findById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<DirectoryEntity> updateDirectory(
    @PathVariable Long id,
    @RequestBody DirectoryEntity directory
  ) {
    Optional<DirectoryEntity> existingDirectory = directoryRepository.findById(id);
    if (existingDirectory.isPresent()) {
      directory.setId(id);
      DirectoryEntity updatedDirectory = directoryRepository.save(directory);
      return ResponseEntity.ok(updatedDirectory);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<DirectoryEntity>> getDirectoriesByUserId(
    @PathVariable Long userId
  ) {
    Optional<UserEntity> user = userRepository.findById(userId);
    if (user.isPresent()) {
      List<DirectoryEntity> directories = directoryRepository.findByUser(user.get());
      return ResponseEntity.ok(directories);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDirectory(@PathVariable Long id) {
    Optional<DirectoryEntity> directory = directoryRepository.findById(id);
    if (directory.isPresent()) {
      directoryRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/user/{userId}/directory/{directoryId}")
  public ResponseEntity<DirectoryEntity> getOneDirectoryByOneUser(
    @PathVariable Long userId,
    @PathVariable Long directoryId
  ) {
    Optional<UserEntity> user = userRepository.findById(userId);
    if (user.isPresent()) {
      Optional<DirectoryEntity> directory = directoryRepository.findByIdAndUser(
        directoryId,
        user.get()
      );
      if (directory.isPresent()) {
        return ResponseEntity.ok(directory.get());
      } else {
        return ResponseEntity.notFound().build();
      }
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
