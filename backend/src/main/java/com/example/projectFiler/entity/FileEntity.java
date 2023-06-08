package com.example.projectFiler.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
public class FileEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String filename;

  @ManyToOne
  private UserEntity users;

  public void setMimeType(String contentType) {}

  public void setData(byte[] bytes) {}

  public void setId(Long id) {
    this.id = id;
  }

  public void setFilename(String originalFilename) {
    this.filename = filename;
  }
}
