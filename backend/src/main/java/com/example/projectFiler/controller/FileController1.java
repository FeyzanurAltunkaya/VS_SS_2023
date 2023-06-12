package com.example.projectFiler.controller;

import com.example.projectFiler.services.FileService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files1")
public class FileController1 {
  private final FileService1 fileService;

  @Autowired
  public FileController1(FileService1 fileService) {
    this.fileService = fileService;
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
}
