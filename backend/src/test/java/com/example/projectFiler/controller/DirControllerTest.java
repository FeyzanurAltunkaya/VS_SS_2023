package com.example.projectFiler.controller;

import com.example.projectFiler.entity.DirectoryEntity;
import com.example.projectFiler.repository.DirectoryRepository;
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

public class DirControllerTest {

    @Mock
    private DirectoryRepository directoryRepository;

    @InjectMocks
    private DirController dirController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDirectory() {
        DirectoryEntity directory = new DirectoryEntity();
        directory.setDirectoryName("Test Directory");

        DirectoryEntity savedDirectory = new DirectoryEntity();
        savedDirectory.setId(1L);
        savedDirectory.setDirectoryName("Test Directory");

        when(directoryRepository.save(directory)).thenReturn(savedDirectory);

        ResponseEntity<DirectoryEntity> response = dirController.createDirectory(directory);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedDirectory, response.getBody());

        verify(directoryRepository, times(1)).save(directory);
    }

    @Test
    public void testGetAllDirectories() {
        List<DirectoryEntity> directories = new ArrayList<>();
        directories.add(new DirectoryEntity());
        directories.add(new DirectoryEntity());

        when(directoryRepository.findAll()).thenReturn(directories);

        List<DirectoryEntity> response = dirController.getAllDirectories();

        assertEquals(directories.size(), response.size());

        verify(directoryRepository, times(1)).findAll();
    }

    @Test
    public void testGetDirectoryById() {
        DirectoryEntity directory = new DirectoryEntity();
        directory.setId(1L);
        directory.setDirectoryName("Test Directory");

        when(directoryRepository.findById(1L)).thenReturn(Optional.of(directory));

        Optional<DirectoryEntity> response = dirController.getDirectoryById(1L);

        assertEquals(Optional.of(directory), response);

        verify(directoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateDirectory() {
        DirectoryEntity existingDirectory = new DirectoryEntity();
        existingDirectory.setId(1L);
        existingDirectory.setDirectoryName("Existing Directory");

        DirectoryEntity updatedDirectory = new DirectoryEntity();
        updatedDirectory.setId(1L);
        updatedDirectory.setDirectoryName("Updated Directory");

        when(directoryRepository.findById(1L)).thenReturn(Optional.of(existingDirectory));
        when(directoryRepository.save(updatedDirectory)).thenReturn(updatedDirectory);

        ResponseEntity<DirectoryEntity> response = dirController.updateDirectory(1L, updatedDirectory);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDirectory, response.getBody());

        verify(directoryRepository, times(1)).findById(1L);
        verify(directoryRepository, times(1)).save(updatedDirectory);
    }

    @Test
    public void testDeleteDirectory() {
        DirectoryEntity directory = new DirectoryEntity();
        directory.setId(1L);
        directory.setDirectoryName("Test Directory");

        when(directoryRepository.findById(1L)).thenReturn(Optional.of(directory));

        ResponseEntity<Void> response = dirController.deleteDirectory(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(directoryRepository, times(1)).findById(1L);
        verify(directoryRepository, times(1)).deleteById(1L);
    }
}