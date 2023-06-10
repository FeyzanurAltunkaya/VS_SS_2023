package com.example.projectFiler.controller;

import com.example.projectFiler.entity.FileTestEntity;
import com.example.projectFiler.repository.FileTestRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/files")
public class FileTestController {
  private final FileTestRepository fileTestRepository;

  @Autowired
  public FileTestController(FileTestRepository fileTestRepository) {
    this.fileTestRepository = fileTestRepository;
  }

  @GetMapping
  public ResponseEntity<List<FileTestEntity>> getAllFiles() {
    List<FileTestEntity> files = fileTestRepository.findAll();
    return ResponseEntity.ok(files);
  }

  @PostMapping
  public ResponseEntity<FileTestEntity> createFile(@RequestBody FileTestEntity file) {
    FileTestEntity createdFile = fileTestRepository.save(file);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdFile);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FileTestEntity> getFileById(@PathVariable Long id) {
    Optional<FileTestEntity> file = fileTestRepository.findById(id);
    return file.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<FileTestEntity> updateFile(
    @PathVariable Long id,
    @RequestBody FileTestEntity updatedFile
  ) {
    Optional<FileTestEntity> existingFile = fileTestRepository.findById(id);
    if (existingFile.isPresent()) {
      updatedFile.setId(id);
      FileTestEntity savedFile = fileTestRepository.save(updatedFile);
      return ResponseEntity.ok(savedFile);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
    Optional<FileTestEntity> file = fileTestRepository.findById(id);
    if (file.isPresent()) {
      fileTestRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
