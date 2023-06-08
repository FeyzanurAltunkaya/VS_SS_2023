package com.example.projectFiler.repository;

import com.example.projectFiler.entity.ERole;
import com.example.projectFiler.entity.RoleEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
  Optional<RoleEntity> findByName(ERole name);
}
