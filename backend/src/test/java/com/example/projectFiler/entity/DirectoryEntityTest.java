package com.example.projectFiler.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.projectFiler.entity.DirectoryEntity;
import com.example.projectFiler.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DirectoryEntityTest {

  @Test
  public void testDirectoryEntity() {
    // Erstellen einer UserEntity für den Test
    UserEntity user = new UserEntity();
    user.setId(1L);
    user.setUsername("testUser");

    // Erstellen einer DirectoryEntity
    DirectoryEntity directory = new DirectoryEntity();
    directory.setId(1L);
    directory.setDirectoryName("testDirectory");
    directory.setUser(user);

    // Überprüfen der Werte
    assertNotNull(directory.getId());
    assertEquals(1L, directory.getId());
    assertEquals("testDirectory", directory.getDirectoryName());
    assertNotNull(directory.getUser());
    assertEquals(user, directory.getUser());
  }
}
