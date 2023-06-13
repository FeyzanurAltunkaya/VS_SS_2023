package com.example.projectFiler.controller;

import com.example.projectFiler.entity.FileEntity1;
import com.example.projectFiler.repository.FileRepo1;
import com.example.projectFiler.services.FileService1;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@RestController
@RequestMapping("/files1")
public class FileController1 {
  private final FileService1 fileService;

  @Autowired
  private final FileRepo1 fileRepo1;

  @Autowired
  public FileController1(FileService1 fileService, FileRepo1 fileRepo1) {
    this.fileService = fileService;
    this.fileRepo1 = fileRepo1;
  }

  @PostMapping
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      fileService.saveFile(file);
      return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
    } catch (Exception e) {
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Failed to upload file");
    }
  }

  @GetMapping
  public ResponseEntity<List<FileEntity1>> getAllFiles() {
    List<FileEntity1> files = fileRepo1.findAll();
    if (files.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(files);
    }
  }

  @PutMapping("/{fileId}")
  public ResponseEntity<String> updateFileName(
    @PathVariable Long fileId,
    @RequestParam String newFileName
  ) {
    Optional<FileEntity1> optionalFile = fileRepo1.findById(fileId);
    if (optionalFile.isPresent()) {
      FileEntity1 file = optionalFile.get();
      file.setFileName(newFileName);
      fileRepo1.save(file);
      return ResponseEntity.ok("Filename updated successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
