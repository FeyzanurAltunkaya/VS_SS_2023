package com.example.projectFiler.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

  @RequestMapping("/")
  public String home() {
    return "Dockerizing Spring Boot Application";
  }
}
