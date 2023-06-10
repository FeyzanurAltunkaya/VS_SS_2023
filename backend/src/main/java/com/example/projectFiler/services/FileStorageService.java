package com.example.projectFiler.services;

import com.example.projectFiler.entity.FileEntity;
import com.example.projectFiler.repository.FileRepository;
import java.io.IOException;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
  @Autowired
  private FileRepository fileRepository;

  public FileEntity store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileEntity FileDB = new FileEntity(fileName, file.getContentType(), file.getBytes());

    return fileRepository.save(FileDB);
  }

  public FileEntity getFile(String id) {
    return fileRepository.findById(id).get();
  }

  public Stream<FileEntity> getAllFiles() {
    return fileRepository.findAll().stream();
  }
}
