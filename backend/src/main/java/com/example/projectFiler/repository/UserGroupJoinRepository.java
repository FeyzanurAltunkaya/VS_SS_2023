package com.example.projectFiler.repository;

import com.example.projectFiler.entity.GroupEntity;
import com.example.projectFiler.entity.UserEntity;
import com.example.projectFiler.entity.UserGroupJoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupJoinRepository
  extends JpaRepository<UserGroupJoinEntity, Long> {
  void deleteByUserAndGroup(UserEntity user, GroupEntity group);
  // Add custom query methods if needed
}
