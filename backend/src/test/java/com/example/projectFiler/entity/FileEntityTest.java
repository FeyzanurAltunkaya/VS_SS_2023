package com.example.projectFiler.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileEntityTest {

  @Test
  public void testSetNameAndGetname() {
    // Erstellen einer FileEntity
    FileEntity file = new FileEntity();
    String expectedName = "testFile";

    // Setzen des Namens
    file.setName(expectedName);

    // Überprüfen des Namens
    String actualName = file.getName();
    assertEquals(expectedName, actualName);
  }

  @Test
  public void testGetDataAndSetData() {
    // Erstellen einer FileEntity
    FileEntity file = new FileEntity();
    byte[] expectedData = "Test data".getBytes();

    // Setzen der Daten
    file.setData(expectedData);

    // Überprüfen der Daten
    byte[] actualData = file.getData();
    assertEquals(expectedData, actualData);
  }

  @Test
  public void testGetType() {
    // Erstellen einer FileEntity
    FileEntity file = new FileEntity();
    String expectedType = "txt";

    // Setzen des Typs
    file.setType(expectedType);

    // Überprüfen des Typs
    String actualType = file.getType();
    assertEquals(expectedType, actualType);
  }
}
