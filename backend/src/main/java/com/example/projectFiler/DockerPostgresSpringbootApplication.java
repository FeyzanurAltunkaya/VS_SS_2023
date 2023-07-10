package com.example.projectFiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories("com.example.projectFiler.repository")
@EntityScan("com.example.projectFiler.entity")
public class DockerPostgresSpringbootApplication {

  public static void main(String[] args) {
    SpringApplication.run(DockerPostgresSpringbootApplication.class, args);
  }

  @Bean
  public WebMvcConfigurer configure() {
    return new WebMvcConfigurer() {

      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
          .addMapping("/**")
          .allowedOrigins("*")
          .allowedMethods("*")
          .allowedHeaders("*");
      }
    };
  }
}
