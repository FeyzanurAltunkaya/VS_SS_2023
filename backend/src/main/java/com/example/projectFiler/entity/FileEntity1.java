package com.example.projectFiler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity1 {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String fileName;

  @Lob
  @Column(length = 10485760) // Adjust the length based on your database's maximum size limit for storing binary data
  private byte[] fileData;



  public FileEntity1(String fileName, byte[] fileData) {
    this.fileName = fileName;
    this.fileData = fileData;
  }
}
