package com.example.projectFiler.repository;

import com.example.projectFiler.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  //new
  Optional<UserEntity> findByUsername(String username);

  Boolean existsByUsername(String username);
}
