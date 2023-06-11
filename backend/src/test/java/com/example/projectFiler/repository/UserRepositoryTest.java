package com.example.projectFiler.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.projectFiler.entity.UserEntity;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserRepositoryTest userRepositoryTest;

  @Test
  void findByUsername_ExistingUser_ReturnsOptionalUserEntity() {
    //  Variable username erstellen, als Eingabewert für den Testfall
    String username = "testUser";
    UserEntity expectedUserEntity = new UserEntity();
    expectedUserEntity.setUsername(username);
    when(userRepository.findByUsername(username))
      .thenReturn(Optional.of(expectedUserEntity));

    // Ergebnis in Variable speichern
    Optional<UserEntity> actualUserEntity = userRepository.findByUsername(username);

    // Assert, ob das UserEntity objekt vorhanden ist
    assertTrue(actualUserEntity.isPresent());
    assertEquals(expectedUserEntity, actualUserEntity.get());
    verify(userRepository, times(1)).findByUsername(username);
  }

  @Test
  void findByUsername_NonExistingUser_ReturnsEmptyOptional() {
    String username = "nonExistingUser";
    when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

    Optional<UserEntity> actualUserEntity = userRepository.findByUsername(username);

    // Überprüfung ob actualUserEntity Objekt leer ist
    assertFalse(actualUserEntity.isPresent());
    verify(userRepository, times(1)).findByUsername(username);
  }

  @Test
  void existsByUsername_ExistingUser_ReturnsTrue() {
    String username = "testUser";
    when(userRepository.existsByUsername(username)).thenReturn(true);

    boolean exists = userRepository.existsByUsername(username);

    // Existiert username?
    assertTrue(exists);
    verify(userRepository, times(1)).existsByUsername(username);
  }

  @Test
  void existsByUsername_NonExistingUser_ReturnsFalse() {
    String username = "nonExistingUser";
    when(userRepository.existsByUsername(username)).thenReturn(false);

    boolean exists = userRepository.existsByUsername(username);

    // überprüfen ob username nicht existiert?
    assertFalse(exists);
    verify(userRepository, times(1)).existsByUsername(username);
  }
}
