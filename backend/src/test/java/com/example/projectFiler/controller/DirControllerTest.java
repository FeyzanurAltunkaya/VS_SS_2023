package com.example.projectFiler.controller;
import com.example.projectFiler.controller.DirController;
import com.example.projectFiler.entity.DirectoryEntity;
import com.example.projectFiler.repository.DirectoryRepository;
import com.example.projectFiler.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DirControllerTest {

    @Mock
    private DirectoryRepository directoryRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DirController dirController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDirectory() {
        DirectoryEntity directory = new DirectoryEntity();
        directory.setDirname("Test Directory");

        when(directoryRepository.save(directory)).thenReturn(directory);

        ResponseEntity<DirectoryEntity> response = dirController.createDirectory(directory);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(directory, response.getBody());

        verify(directoryRepository, times(1)).save(directory);
    }

    @Test
    void testGetAllDirectories() {
        List<DirectoryEntity> directories = new ArrayList<>();
        directories.add(new DirectoryEntity());
        directories.add(new DirectoryEntity());

        when(directoryRepository.findAll()).thenReturn(directories);

        List<DirectoryEntity> response = dirController.getAllDirectories();

        assertEquals(directories.size(), response.size());
        assertEquals(directories.get(0), response.get(0));
        assertEquals(directories.get(1), response.get(1));

        verify(directoryRepository, times(1)).findAll();
    }

    @Test
    void testGetDirectoryById_ExistingId() {
        Long id = 1L;
        DirectoryEntity directory = new DirectoryEntity();
        directory.setId(id);
        directory.setDirname("Test Directory");

        when(directoryRepository.findById(id)).thenReturn(Optional.of(directory));

        ResponseEntity<DirectoryEntity> response = dirController.getDirectoryById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(directory, response.getBody());

        verify(directoryRepository, times(1)).findById(id);
    }

    @Test
    void testGetDirectoryById_NonExistingId() {
        Long id = 1L;

        when(directoryRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<DirectoryEntity> response = dirController.getDirectoryById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(directoryRepository, times(1)).findById(id);
    }

    @Test
    void testUpdateDirectory_ExistingId() {
        Long id = 1L;
        DirectoryEntity existingDirectory = new DirectoryEntity();
        existingDirectory.setId(id);
        existingDirectory.setDirname("Existing Directory");

        DirectoryEntity updatedDirectory = new DirectoryEntity();
        updatedDirectory.setId(id);
        updatedDirectory.setDirname("Updated Directory");

        when(directoryRepository.findById(id)).thenReturn(Optional.of(existingDirectory));
        when(directoryRepository.save(updatedDirectory)).thenReturn(updatedDirectory);

        ResponseEntity<DirectoryEntity> response = dirController.updateDirectory(id, updatedDirectory);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDirectory, response.getBody());

        verify(directoryRepository, times(1)).findById(id);
        verify(directoryRepository, times(1)).save(updatedDirectory);
    }

    @Test
    void testUpdateDirectory_NonExistingId() {
        Long id = 1L;
        DirectoryEntity directory = new DirectoryEntity();
        directory.setId(id);
        directory.setDirname("Test Directory");

        when(directoryRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<DirectoryEntity> response = dirController.updateDirectory(id, directory);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(directoryRepository, times(1)).findById(id);
        verify(directoryRepository, times(0)).save(directory);
    }

    @Test
    void testDeleteDirectory_ExistingId() {
        Long id = 1L;
        DirectoryEntity directory = new DirectoryEntity();
        directory.setId(id);
        directory.setDirname("Test Directory");

        when(directoryRepository.findById(id)).thenReturn(Optional.of(directory));

        ResponseEntity<Void> response = dirController.deleteDirectory(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(directoryRepository, times(1)).findById(id);
        verify(directoryRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteDirectory_NonExistingId() {
        Long id = 1L;

        when(directoryRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = dirController.deleteDirectory(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(directoryRepository, times(1)).findById(id);
        verify(directoryRepository, times(0)).deleteById(id);
    }
}
