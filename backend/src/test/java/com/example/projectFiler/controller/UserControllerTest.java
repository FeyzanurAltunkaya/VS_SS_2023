package com.example.projectFiler.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.projectFiler.entity.UserEntity;
import com.example.projectFiler.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class UserControllerTest {
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetUserById() {
    // Creating a test user
    UserEntity testUser = new UserEntity();
    testUser.setId(1L);
    testUser.setFirstName("John");
    testUser.setLastName("Doe");

    // Mocking the user repository
    when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
    when(userRepository.findById(2L)).thenReturn(Optional.empty());

    // Sending the request to the controller
    ResponseEntity<UserEntity> response1 = userController.getUserById(1L);
    ResponseEntity<UserEntity> response2 = userController.getUserById(2L);

    // Verifying the responses
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals(testUser, response1.getBody());

    assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());

    // Verifying the interaction with the user repository
    verify(userRepository, times(1)).findById(1L);
    verify(userRepository, times(1)).findById(2L);
  }
}
