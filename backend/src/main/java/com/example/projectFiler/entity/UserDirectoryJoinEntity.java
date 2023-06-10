package com.example.projectFiler.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
public class UserDirectoryJoinEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "UserEntitiy")
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "DirectoryEntitiy")
  private DirectoryEntity directory;

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public void setDirectory(DirectoryEntity directory) {
    this.directory = directory;
  }
}
