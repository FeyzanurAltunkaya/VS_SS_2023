package com.example.projectFiler.repository;

import com.example.projectFiler.entity.FileEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class FileRepositoryTest {
  @Autowired
  private FileRepository fileRepository;

  private FileEntity file;

  @BeforeEach
  public void setup() {
    file = new FileEntity();
    file.setName("TestFile.txt");
    file.setData("This is a test from tugce and feyza".getBytes());
  }

  @Test
  public void testSetName() {
    String newName = "NewFileName.txt";
    file.setName(newName);
    Assertions.assertEquals(newName, file.getName());
  }

  @Test
  public void testSetData() {
    byte[] newData = "This is new file content".getBytes();
    file.setData(newData);
    Assertions.assertArrayEquals(newData, file.getData());
  }

  @Test
  public void testGetName() {
    Assertions.assertEquals("TestFile.txt", file.getName());
  }

  @Test
  public void testGetData() {
    byte[] expectedData = "This is a test from tugce and feyza".getBytes();
    Assertions.assertArrayEquals(expectedData, file.getData());
  }
}
