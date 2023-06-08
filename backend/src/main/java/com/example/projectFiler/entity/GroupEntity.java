package com.example.projectFiler.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Entity
@Repository
public class GroupEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "groups")
  private Set<UserEntity> users = new HashSet<>();

  public String getName() {
    return name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
