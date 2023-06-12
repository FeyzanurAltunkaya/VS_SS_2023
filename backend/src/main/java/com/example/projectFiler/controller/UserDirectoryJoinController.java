/*
package com.example.projectFiler.controller;

import com.example.projectFiler.entity.DirectoryEntity;
import com.example.projectFiler.entity.UserDirectoryJoinEntity;
import com.example.projectFiler.entity.UserEntity;
import com.example.projectFiler.entity.UserGroupJoinEntity;
import com.example.projectFiler.repository.DirectoryRepository;
import com.example.projectFiler.repository.UserDirectoryJoinRepository;
import com.example.projectFiler.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/userdirectory")
public class UserDirectoryJoinController {
  private final UserDirectoryJoinRepository userDirectoryJoinRepository;
  private final UserRepository userRepository;
  private final DirectoryRepository directoryRepository;

  @Autowired
  public UserDirectoryJoinController(
    UserDirectoryJoinRepository userDirectoryJoinRepository,
    UserRepository userRepository,
    DirectoryRepository directoryRepository
  ) {
    this.userDirectoryJoinRepository = userDirectoryJoinRepository;
    this.userRepository = userRepository;
    this.directoryRepository = directoryRepository;
  }

  @PostMapping("/{userId}/directories/{directoryId}")
  public ResponseEntity<UserDirectoryJoinRepository> addUserToDirectory(
    @PathVariable Long userId,
    @PathVariable Long directoryId
  ) {
    Optional<UserEntity> optionalUser = userRepository.findById(userId);
    Optional<DirectoryEntity> optionalDirectory = directoryRepository.findById(
      directoryId
    );

    if (optionalUser.isPresent() && optionalDirectory.isPresent()) {
      UserEntity user = optionalUser.get();
      DirectoryEntity directory = optionalDirectory.get();

      UserDirectoryJoinEntity userDirectoryJoin = new UserDirectoryJoinEntity();
      userDirectoryJoin.setUser(user);
      userDirectoryJoin.setDirectory(directory);

      UserDirectoryJoinEntity savedUserDirectoryJoin = userDirectoryJoinRepository.save(
        userDirectoryJoin
      );
      return ResponseEntity.status(HttpStatus.OK).body(savedUserDirectoryJoin);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{userId}/directories/{directoryId}")
  public ResponseEntity<Void> removeUserFromDirectory(
    @PathVariable Long userId,
    @PathVariable Long directoryId
  ) {
    Optional<UserEntity> optionalUser = userRepository.findById(userId);
    Optional<DirectoryEntity> optionalDirectory = directoryRepository.findById(
      directoryId
    );

    if (optionalUser.isPresent() && optionalDirectory.isPresent()) {
      UserEntity user = optionalUser.get();
      DirectoryEntity directory = optionalDirectory.get();

      userDirectoryJoinRepository.deleteByUserAndDirectory(user, directory);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}


 */
