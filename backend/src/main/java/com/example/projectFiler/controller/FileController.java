package com.example.projectFiler.controller;

import com.example.projectFiler.entity.FileEntity;
import com.example.projectFiler.entity.GroupEntity;
import com.example.projectFiler.repository.FileRepository;
import com.example.projectFiler.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
  //private final UserRepository userRepository;
  private final FileRepository fileRepository;

  @Autowired
  /*public FileController(UserRepository userRepository, FileRepository fileRepository) {
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }*/
  public FileController(FileRepository fileRepository) {
    this.fileRepository = fileRepository;
  }/*
 @PostMapping
    public ResponseEntity<FileEntity> createFile(@RequestBody FileEntity file) {
    FileEntity createdFile = fileRepository.save(file);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdFile);*/

  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      FileEntity fileEntity = new FileEntity();
      fileEntity.setFilename(file.getOriginalFilename());
      fileEntity.setMimeType(file.getContentType());
      fileEntity.setData(file.getBytes());

      fileRepository.save(fileEntity);

      return ResponseEntity.ok("File uploaded successfully.");
    } catch (Exception e) {
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Failed to upload file.");
    }
  }

  @GetMapping
  public ResponseEntity<List<FileEntity>> getAllFiles() {
    List<FileEntity> files = fileRepository.findAll();
    return ResponseEntity.ok(files);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FileEntity> getFileById(@PathVariable Long id) {
    Optional<FileEntity> file = fileRepository.findById(id);
    return file.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<FileEntity> updateFile(
    @PathVariable Long id,
    @RequestBody FileEntity file
  ) {
    Optional<FileEntity> existingFile = fileRepository.findById(id);
    if (existingFile.isPresent()) {
      file.setId(id);
      FileEntity updatedFile = fileRepository.save(file);
      return ResponseEntity.ok(updatedFile);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
    Optional<FileEntity> file = fileRepository.findById(id);
    if (file.isPresent()) {
      fileRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
