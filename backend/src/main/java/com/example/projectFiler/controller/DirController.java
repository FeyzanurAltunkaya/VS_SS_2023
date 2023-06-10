package com.example.projectFiler.controller;

import com.example.projectFiler.entity.DirectoryEntity;
import com.example.projectFiler.entity.GroupEntity;
import com.example.projectFiler.repository.DirectoryRepository;
import com.example.projectFiler.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*")
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

  @GetMapping
  public ResponseEntity<List<DirectoryEntity>> getAllDirectories() {
    List<DirectoryEntity> directories = directoryRepository.findAll();
    return ResponseEntity.ok(directories);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DirectoryEntity> getDirectoryById(@PathVariable Long id) {
    Optional<DirectoryEntity> directory = directoryRepository.findById(id);
    return directory.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
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
}
