package com.example.projectFiler.repository;

import com.example.projectFiler.entity.FileTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileTestRepository extends JpaRepository<FileTestEntity, Long> {}
