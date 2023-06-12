package com.example.projectFiler.controller;

import com.example.projectFiler.entity.GroupEntity;
import com.example.projectFiler.entity.UserDirectoryJoinEntity;
import com.example.projectFiler.entity.UserEntity;
import com.example.projectFiler.entity.UserGroupJoinEntity;
import com.example.projectFiler.repository.GroupRepository;
import com.example.projectFiler.repository.UserGroupJoinRepository;
import com.example.projectFiler.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/usergroup")
public class UserGroupJoinController {
  private final UserGroupJoinRepository userGroupJoinRepository;
  private final UserRepository userRepository;
  private final GroupRepository groupRepository;

  @Autowired
  public UserGroupJoinController(
    UserGroupJoinRepository userGroupJoinRepository,
    UserRepository userRepository,
    GroupRepository groupRepository
  ) {
    this.userGroupJoinRepository = userGroupJoinRepository;
    this.userRepository = userRepository;
    this.groupRepository = groupRepository;
  }

  @GetMapping
  public List<UserGroupJoinEntity> getAllUserGroups() {
    return userGroupJoinRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserGroupJoinEntity> getUserGroupById(@PathVariable Long id) {
    Optional<UserGroupJoinEntity> usergroup = userGroupJoinRepository.findById(id);
    return usergroup.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserGroupJoinEntity> updateUserGroup(
    @PathVariable Long id,
    @RequestBody UserGroupJoinEntity usergroup
  ) {
    Optional<UserGroupJoinEntity> existingUserGroup = userGroupJoinRepository.findById(
      id
    );
    if (existingUserGroup.isPresent()) {
      usergroup.setId(id);
      UserGroupJoinEntity updatedUserGroup = userGroupJoinRepository.save(usergroup);
      return ResponseEntity.ok(updatedUserGroup);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<UserGroupJoinEntity> createUserGroup(
    @RequestBody UserGroupJoinEntity userdroup
  ) {
    UserGroupJoinEntity createdGroup = userGroupJoinRepository.save(userdroup);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
  }

  @PostMapping("/{userId}/groups/{groupId}")
  public ResponseEntity<UserGroupJoinEntity> addUserToGroup(
    @PathVariable Long userId,
    @PathVariable Long groupId
  ) {
    Optional<UserEntity> optionalUser = userRepository.findById(userId);
    Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);

    if (optionalUser.isPresent() && optionalGroup.isPresent()) {
      UserEntity user = optionalUser.get();
      GroupEntity group = optionalGroup.get();

      UserGroupJoinEntity userGroupJoin = new UserGroupJoinEntity();
      userGroupJoin.setUser(user);
      userGroupJoin.setGroup(group);

      UserGroupJoinEntity savedUserGroupJoin = userGroupJoinRepository.save(
        userGroupJoin
      );
      return ResponseEntity.status(HttpStatus.OK).body(savedUserGroupJoin);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{userId}/groups/{groupId}")
  public ResponseEntity<Void> removeUserFromGroup(
    @PathVariable Long userId,
    @PathVariable Long groupId
  ) {
    Optional<UserEntity> optionalUser = userRepository.findById(userId);
    Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);

    if (optionalUser.isPresent() && optionalGroup.isPresent()) {
      UserEntity user = optionalUser.get();
      GroupEntity group = optionalGroup.get();

      userGroupJoinRepository.deleteByUserAndGroup(user, group);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
