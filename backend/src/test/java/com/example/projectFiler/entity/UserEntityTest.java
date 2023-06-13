package com.example.projectFiler.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.projectFiler.entity.RoleEntity;
import com.example.projectFiler.entity.UserEntity;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEntityTest {

  @Test
  public void testGetUsername() {
    // Arrange
    String username = "testuser";
    String password = "password";
    UserEntity userEntity = new UserEntity(username, password);

    // Act
    String actualUsername = userEntity.getUsername();

    // Assert
    assertNotNull(actualUsername, "Username should not be null");
    assertEquals(username, actualUsername, "Username should match");
  }

  @Test
  public void testSetUsername() {
    // Arrange
    String username = "testuser";
    String password = "password";
    UserEntity userEntity = new UserEntity(username, password);

    // Act
    String newUsername = "newuser";
    userEntity.setUsername(newUsername);

    // Assert
    assertEquals(newUsername, userEntity.getUsername(), "Username should be updated");
  }

  @Test
  public void testSetRoles() {
    // Arrange
    String username = "testuser";
    String password = "password";
    UserEntity userEntity = new UserEntity(username, password);

    // Create roles
    RoleEntity role1 = new RoleEntity(ERole.ROLE_ADMIN);
    RoleEntity role2 = new RoleEntity(ERole.ROLE_USER);

    // Create set of roles
    Set<RoleEntity> roles = new HashSet<>();
    roles.add(role1);
    roles.add(role2);

    // Act
    userEntity.setRoles(roles);

    // Assert
    assertEquals(roles, userEntity.getRoles(), "Roles should be set");
  }

  @Test
  public void testGetRoles() {
    // Arrange
    String username = "testuser";
    String password = "password";
    UserEntity userEntity = new UserEntity(username, password);

    // Create roles
    RoleEntity role1 = new RoleEntity(ERole.ROLE_ADMIN);
    RoleEntity role2 = new RoleEntity(ERole.ROLE_USER);

    Set<RoleEntity> roles = new HashSet<>();
    roles.add(role1);
    roles.add(role2);

    userEntity.setRoles(roles);

    Set<RoleEntity> actualRoles = userEntity.getRoles();

    assertNotNull(actualRoles, "Roles should not be null");
    assertEquals(roles.size(), actualRoles.size(), "Number of roles should match");
    assertEquals(roles, actualRoles, "Roles should match");
  }
}
