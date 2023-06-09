package com.example.projectFiler.repository;

import com.example.projectFiler.entity.DirectoryEntity;
import com.example.projectFiler.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepository extends JpaRepository<DirectoryEntity, Long> {}
