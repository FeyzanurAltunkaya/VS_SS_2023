package com.example.projectFiler.repository;

import com.example.projectFiler.entity.FileEntity;
import com.example.projectFiler.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {}
