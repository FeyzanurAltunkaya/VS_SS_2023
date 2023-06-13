package com.example.projectFiler.controller;
import com.example.projectFiler.entity.FileEntity1;
import com.example.projectFiler.repository.FileRepo1;
import com.example.projectFiler.services.FileService1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FileController1Test {

    @Mock
    private FileService1 fileService;

    @Mock
    private FileRepo1 fileRepo1;

    @InjectMocks
    private FileController1 fileController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUploadFile_Success() throws IOException {
        MultipartFile file = new MockMultipartFile("test.txt", "Hello, World!".getBytes());

        ResponseEntity<String> response = fileController.uploadFile(file);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("File uploaded successfully", response.getBody());

        verify(fileService, times(1)).saveFile(file);
    }

    @Test
    public void testGetAllFiles_Success() {
        List<FileEntity1> files = new ArrayList<>();
        files.add(new FileEntity1());
        files.add(new FileEntity1());

        when(fileRepo1.findAll()).thenReturn(files);

        ResponseEntity<List<FileEntity1>> response = fileController.getAllFiles();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(files.size(), response.getBody().size());

        verify(fileRepo1, times(1)).findAll();
    }

    @Test
    public void testUpdateFileName_Success() {
        Long fileId = 1L;
        String newFileName = "newFileName";

        FileEntity1 file = new FileEntity1();
        file.setId(fileId);
        file.setFileName("oldFileName");

        when(fileRepo1.findById(fileId)).thenReturn(Optional.of(file));
        when(fileRepo1.save(file)).thenReturn(file);

        ResponseEntity<String> response = fileController.updateFileName(fileId, newFileName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Filename updated successfully", response.getBody());
        assertEquals(newFileName, file.getFileName());

        verify(fileRepo1, times(1)).findById(fileId);
        verify(fileRepo1, times(1)).save(file);
    }

    @Test
    public void testUpdateFileName_NotFound() {
        Long fileId = 1L;
        String newFileName = "newFileName";

        when(fileRepo1.findById(fileId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = fileController.updateFileName(fileId, newFileName);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(fileRepo1, times(1)).findById(fileId);
        verify(fileRepo1, never()).save(any(FileEntity1.class));
    }
}