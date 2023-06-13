package com.example.projectFiler.repository;

import com.example.projectFiler.entity.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserDirectoryJoinRepository
  extends JpaRepository<UserDirectoryJoinEntity, Long> {
  void deleteByUserAndDirectory(UserEntity user, DirectoryEntity directory);
  // Add custom query methods if needed
  List<UserDirectoryJoinEntity> findByUser(UserEntity user);

  UserDirectoryJoinEntity findByUserAndDirectory(
    UserEntity user,
    DirectoryEntity directory
  );

    List<DirectoryEntity> findAllDirectoriesByUserId(Long userId);
}
