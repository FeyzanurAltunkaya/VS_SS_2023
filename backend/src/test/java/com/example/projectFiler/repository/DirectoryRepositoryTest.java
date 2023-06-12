package com.example.projectFiler.repository;

import com.example.projectFiler.entity.DirectoryEntity;
import com.example.projectFiler.repository.DirectoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class DirectoryRepositoryTest {
  @Autowired
  private DirectoryRepository directoryRepository;

  @Test
  public void testSaveDirectory() {
    // Create a new directory entity
    DirectoryEntity directory = new DirectoryEntity();
    directory.setDirectoryName("Test Directory");

    // Save the directory
    DirectoryEntity savedDirectory = directoryRepository.save(directory);

    // Verify that the directory is saved with a valid ID
    Assertions.assertNotNull(savedDirectory.getId());

    // Verify that the directory can be retrieved from the repository
    DirectoryEntity retrievedDirectory = directoryRepository
      .findById(savedDirectory.getId())
      .orElse(null);
    Assertions.assertNotNull(retrievedDirectory);
    Assertions.assertEquals("Test Directory", retrievedDirectory.getDirectoryName());
  }

  @Test
  public void testDeleteDirectory() {
    // Create a new directory entity
    DirectoryEntity directory = new DirectoryEntity();
    directory.setDirectoryName("Test Directory");

    // Save the directory
    DirectoryEntity savedDirectory = directoryRepository.save(directory);

    // Delete the directory
    directoryRepository.delete(savedDirectory);

    // Verify that the directory is no longer present in the repository
    DirectoryEntity deletedDirectory = directoryRepository
      .findById(savedDirectory.getId())
      .orElse(null);
    Assertions.assertNull(deletedDirectory);
  }
}
