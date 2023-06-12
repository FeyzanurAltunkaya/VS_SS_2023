package com.example.projectFiler.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Entity
@Data
public class DirectoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String directoryName;

  @ManyToOne
  private UserEntity user;
}
