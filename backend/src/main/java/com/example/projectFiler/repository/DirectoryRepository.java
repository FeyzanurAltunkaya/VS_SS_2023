package com.example.projectFiler.repository;

import com.example.projectFiler.entity.DirectoryEntity;
import com.example.projectFiler.entity.GroupEntity;
import com.example.projectFiler.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepository extends JpaRepository<DirectoryEntity, Long> {
  List<DirectoryEntity> findByUser(UserEntity userEntity);

  Optional<DirectoryEntity> findByIdAndUser(Long directoryId, UserEntity userEntity);
}
