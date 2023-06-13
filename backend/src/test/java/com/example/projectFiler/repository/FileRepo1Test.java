package com.example.projectFiler.repository;

import com.example.projectFiler.entity.FileEntity1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class FileRepo1Test {
  private FileEntity1 file;

  @BeforeEach
  public void setup() {
    file = new FileEntity1();
    file.setName("TestFile.txt");
  }

  @Test
  public void testSetName() {
    String newName = "NewFileName.txt";
    file.setName(newName);
    Assertions.assertEquals(newName, file.getName());
  }
}
