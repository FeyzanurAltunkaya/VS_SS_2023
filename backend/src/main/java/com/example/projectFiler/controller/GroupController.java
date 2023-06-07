package com.example.projectFiler.controller;

import com.example.projectFiler.entity.GroupEntity;
import com.example.projectFiler.entity.UserEntity;
import com.example.projectFiler.repository.GroupRepository;
import com.example.projectFiler.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {
  private final GroupRepository groupRepository;
  private final UserRepository userRepository;

  @Autowired
  public GroupController(GroupRepository groupRepository, UserRepository userRepository) {
    this.groupRepository = groupRepository;
    this.userRepository = userRepository;
  }

  // Endpoint to add a user to a group
  /**@PostMapping("/{groupId}/users/{userId}")
  public ResponseEntity<GroupEntity> addUserToGroup(
    @PathVariable Long groupId,
    @PathVariable Long userId
  ) {
    Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);
    Optional<UserEntity> optionalUser = userRepository.findById(userId);

    if (optionalGroup.isPresent() && optionalUser.isPresent()) {
      GroupEntity group = optionalGroup.get();
      UserEntity user = optionalUser.get();
      groupRepository.addUser(user, group);
      return ResponseEntity.status(HttpStatus.OK).body(group);
    } else {
      return ResponseEntity.notFound().build();
    }
  }**/

  // Endpoint to remove a user from a group
  /**@DeleteMapping("/{groupId}/users/{userId}")
  public ResponseEntity<GroupEntity> removeUserFromGroup(
    @PathVariable Long groupId,
    @PathVariable Long userId
  ) {
    Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);
    Optional<UserEntity> optionalUser = userRepository.findById(userId);

    if (optionalGroup.isPresent() && optionalUser.isPresent()) {
      GroupEntity group = optionalGroup.get();
      UserEntity user = optionalUser.get();
      groupRepository.removeUser(user, group);
      return ResponseEntity.status(HttpStatus.OK).body(group);
    } else {
      return ResponseEntity.notFound().build();
    }
  } **/

  @PostMapping
  public ResponseEntity<GroupEntity> createGroup(@RequestBody GroupEntity group) {
    GroupEntity createdGroup = groupRepository.save(group);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
  }

  @GetMapping
  public ResponseEntity<List<GroupEntity>> getAllGroups() {
    List<GroupEntity> groups = groupRepository.findAll();
    return ResponseEntity.ok(groups);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GroupEntity> getGroupById(@PathVariable Long id) {
    Optional<GroupEntity> group = groupRepository.findById(id);
    return group.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<GroupEntity> updateGroup(
    @PathVariable Long id,
    @RequestBody GroupEntity group
  ) {
    Optional<GroupEntity> existingGroup = groupRepository.findById(id);
    if (existingGroup.isPresent()) {
      group.setId(id);
      GroupEntity updatedGroup = groupRepository.save(group);
      return ResponseEntity.ok(updatedGroup);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
    Optional<GroupEntity> group = groupRepository.findById(id);
    if (group.isPresent()) {
      groupRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
