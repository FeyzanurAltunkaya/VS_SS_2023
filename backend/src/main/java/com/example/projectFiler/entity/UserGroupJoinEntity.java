package com.example.projectFiler.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
public class UserGroupJoinEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "UserEntitiy")
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "GroupEntitiy")
  private GroupEntity group;

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public void setGroup(GroupEntity group) {
    this.group = group;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public GroupEntity getGroup() {
    return group;
  }
}
