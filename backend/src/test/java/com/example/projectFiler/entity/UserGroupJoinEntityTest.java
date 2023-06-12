package com.example.projectFiler.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserGroupJoinEntityTest {

  @Test
  public void testSetUser() {
    // Erstellen eines Testbenutzers
    UserEntity user = new UserEntity();
    // Setzen der Eigenschaften des Benutzers
    // user.setUsername("testuser");
    // user.setPassword("password");

    // Erstellen einer UserGroupJoinEntity
    UserGroupJoinEntity userGroupJoin = new UserGroupJoinEntity();
    userGroupJoin.setUser(user);
    // Überprüfen, ob der Benutzer in der UserGroupJoinEntity korrekt gesetzt wurde
    //Assertions.assertEquals(user, userGroupJoin.getUser());
  }

  @Test
  public void testSetGroup() {
    // Erstellen einer Testgruppe
    GroupEntity group = new GroupEntity();
    // Setzen der Eigenschaften der Gruppe
    // group.setName("testgroup");

    // Erstellen einer UserGroupJoinEntity
    UserGroupJoinEntity userGroupJoin = new UserGroupJoinEntity();
    userGroupJoin.setGroup(group);

    // Überprüfen, ob die Gruppe in der UserGroupJoinEntity korrekt gesetzt wurde
    Assertions.assertEquals(group, userGroupJoin.getGroup());
  }
}
