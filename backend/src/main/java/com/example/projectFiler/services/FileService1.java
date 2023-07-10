package com.example.projectFiler.services;

import com.example.projectFiler.entity.FileEntity1;
import com.example.projectFiler.repository.FileRepo1;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService1 {
  private final FileRepo1 fileRepo;

  @Autowired
  public FileService1(FileRepo1 fileRepo) {
    this.fileRepo = fileRepo;
  }

  public void saveFile(MultipartFile file) throws IOException {
    String fileName = file.getOriginalFilename();
    byte[] fileData = file.getBytes();
    FileEntity1 fileEntity = new FileEntity1();
    fileEntity.setFileName(fileName);
    fileEntity.setFileData(fileData);
    fileRepo.save(fileEntity);
  }
}
