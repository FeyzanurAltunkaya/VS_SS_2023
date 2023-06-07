package com.example.projectFiler.repository;

import com.example.projectFiler.entity.GroupEntity;
import com.example.projectFiler.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
  // Add a method to add a user to a group
  // void addUser(UserEntity user, GroupEntity group);
  // Add a method to remove a user from a group
  // void remove(UserEntity user, GroupEntity group);
  // void removeUser(UserEntity user, GroupEntity group);
}
