package com.example.projectFiler.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EnableAutoConfiguration
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  //@Column(name = "first_name", nullable = false)
  private String firstName;

  //@Column(name = "last_name", nullable = false)
  private String lastName;

  //@Column(name = "password", nullable = false)
  private String password;

  //@Column(name = "is_logged_in", nullable = false)
  private boolean isLoggedIn = false;

  //@Column(name = "is_admin", nullable = false)
  private boolean isAdmin = false;

  @ManyToMany
  @JoinTable(
    name = "user_group",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "group_id")
  )
  private Set<GroupEntity> groups = new HashSet<>();
}
