package com.example.projectFiler.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Entity
@Repository
public class DirectoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String dirname;

  @ManyToOne
  private UserEntity users;

  public void setDirname(String dirname) {
    this.dirname = dirname;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsers(UserEntity users) {
    this.users = users;
  }
}
