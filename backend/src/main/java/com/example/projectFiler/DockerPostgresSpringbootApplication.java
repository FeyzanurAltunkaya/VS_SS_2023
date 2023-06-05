package com.example.projectFiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.projectFiler.repository")
@EntityScan("com.example.projectFiler.entity")
public class DockerPostgresSpringbootApplication {

  public static void main(String[] args) {
    SpringApplication.run(DockerPostgresSpringbootApplication.class, args);
  }
}
