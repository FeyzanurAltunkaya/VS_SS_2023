package com.example.projectFiler.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserDirectoryJoinEntityTest {

  @Test
  public void setUser_ShouldSetUserEntity() {
    UserEntity user = new UserEntity();
    UserDirectoryJoinEntity userDirectoryJoin = new UserDirectoryJoinEntity();

    userDirectoryJoin.setUser(user);

    assertEquals(user, userDirectoryJoin.getUser());
  }

  @Test
  public void getUser_ShouldReturnUserEntity() {
    UserEntity user = new UserEntity();
    UserDirectoryJoinEntity userDirectoryJoin = new UserDirectoryJoinEntity();

    userDirectoryJoin.setUser(user);

    assertEquals(user, userDirectoryJoin.getUser());
  }

  @Test
  public void setDirectory_ShouldSetDirectoryEntity() {
    DirectoryEntity directory = new DirectoryEntity();
    UserDirectoryJoinEntity userDirectoryJoin = new UserDirectoryJoinEntity();

    userDirectoryJoin.setDirectory(directory);

    assertEquals(directory, userDirectoryJoin.getDirectory());
  }

  @Test
  public void getDirectory_ShouldReturnDirectoryEntity() {
    DirectoryEntity directory = new DirectoryEntity();
    UserDirectoryJoinEntity userDirectoryJoin = new UserDirectoryJoinEntity();

    userDirectoryJoin.setDirectory(directory);

    assertEquals(directory, userDirectoryJoin.getDirectory());
  }
}
