package com.example.projectFiler.repository;

import com.example.projectFiler.entity.FileEntity1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo1 extends JpaRepository<FileEntity1, Long> {}
