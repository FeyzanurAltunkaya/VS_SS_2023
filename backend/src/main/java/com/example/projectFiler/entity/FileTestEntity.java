package com.example.projectFiler.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EnableAutoConfiguration
public class FileTestEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String fileName;

  @Lob
  @Column(length = 10485760) // Adjust the length based on your database's maximum size limit for storing binary data
  private byte[] data;
}
