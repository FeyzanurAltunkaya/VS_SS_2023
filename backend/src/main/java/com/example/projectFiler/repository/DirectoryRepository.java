package com.example.projectFiler.repository;

import com.example.projectFiler.entity.DirectoryEntity;
import com.example.projectFiler.entity.GroupEntity;
import com.example.projectFiler.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectoryRepository extends JpaRepository<DirectoryEntity, Long> {
    List<DirectoryEntity> findByUser(UserEntity userEntity);
}
