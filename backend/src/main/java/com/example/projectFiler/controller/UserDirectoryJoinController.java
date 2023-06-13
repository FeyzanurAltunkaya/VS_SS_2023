package com.example.projectFiler.controller;
/*
import com.example.projectFiler.entity.DirectoryEntity;
import com.example.projectFiler.entity.UserDirectoryJoinEntity;
import com.example.projectFiler.entity.UserEntity;
import com.example.projectFiler.entity.UserGroupJoinEntity;
import com.example.projectFiler.repository.DirectoryRepository;
import com.example.projectFiler.repository.UserDirectoryJoinRepository;
import com.example.projectFiler.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

  @GetMapping("/{userId}/directories")
  public ResponseEntity<List<DirectoryEntity>> getAllDirectoriesByUser(
    @PathVariable Long userId
  ) {
    Optional<UserEntity> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      UserEntity user = optionalUser.get();
      List<UserDirectoryJoinEntity> userDirectories = userDirectoryJoinRepository.findByUser(
        user
      );
      List<DirectoryEntity> directories = userDirectories
        .stream()
        .map(UserDirectoryJoinEntity::getDirectory)
        .collect(Collectors.toList());
      return ResponseEntity.ok(directories);
    } else {
      return ResponseEntity.notFound().build();
    }
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
      return ResponseEntity
        .status(HttpStatus.OK)
        .body((UserDirectoryJoinRepository) savedUserDirectoryJoin);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{userId}/directories/{directoryId}")
  public ResponseEntity<UserDirectoryJoinEntity> getOneDirectoryOfUser(
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

      // Assuming you have a UserDirectoryJoinEntity that represents the relationship between users and directories
      UserDirectoryJoinEntity userDirectoryJoin = userDirectoryJoinRepository.findByUserAndDirectory(
        user,
        directory
      );

      if (userDirectoryJoin != null) {
        return ResponseEntity.ok(userDirectoryJoin);
      } else {
        return ResponseEntity.notFound().build();
      }
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
