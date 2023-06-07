package com.example.projectFiler.entity;

import jakarta.persistence.*;
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
public class GroupEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "groups")
  private Set<UserEntity> users = new HashSet<>();
}
