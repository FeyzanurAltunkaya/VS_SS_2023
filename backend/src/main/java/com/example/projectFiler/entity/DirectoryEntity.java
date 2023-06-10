package com.example.projectFiler.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DirectoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String dirname;

  @ManyToOne
  private UserEntity users;
}
