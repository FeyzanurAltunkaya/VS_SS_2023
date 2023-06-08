package com.example.projectFiler.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
  name = "user_entity",
  uniqueConstraints = { @UniqueConstraint(columnNames = "id") }
)
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //@Column(name = "first_name", nullable = false)
  @NotBlank
  @Size(max = 20)
  private String firstName;

  //@Column(name = "last_name", nullable = false)
  @NotBlank
  @Size(max = 40)
  private String lastName;

  //@Column(name = "password", nullable = false)
  @NotBlank
  @Size(max = 50)
  private String password;

  //@Column(name = "is_logged_in", nullable = false)
  private boolean isLoggedIn = false;

  //@Column(name = "is_admin", nullable = false)
  private boolean isAdmin = false;

  // new
  @NotBlank
  @Size(max = 20)
  private String username;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<RoleEntity> roles = new HashSet<>();

  @ManyToMany
  @JoinTable(
    name = "user_group",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "group_id")
  )
  private Set<GroupEntity> groups = new HashSet<>();

  //new
  public UserEntity() {}

  public UserEntity(String username, String password) {
    this.username = username;
    this.password = password;
  }

  // getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Set<RoleEntity> getRoles() {
    return roles;
  }

  public void setRoles(Set<RoleEntity> roles) {
    this.roles = roles;
  }
}
