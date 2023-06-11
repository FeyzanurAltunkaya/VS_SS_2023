package com.example.projectFiler.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.projectFiler.controller.FileController;
import com.example.projectFiler.entity.FileEntity;
import com.example.projectFiler.message.ResponseMessage;
import com.example.projectFiler.services.FileStorageService;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class FileControllerTest {
  @Mock
  private FileStorageService fileStorageService;

  @InjectMocks
  private FileController fileController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testUploadFile() throws IOException {
    // testfile kreieren für file upload
    MockMultipartFile mockFile = new MockMultipartFile(
      "file",
      "testFile.txt",
      "text/plain",
      "Test content".getBytes()
    );

    doAnswer(
        invocation -> {
          Object[] args = invocation.getArguments();
          MultipartFile file = (MultipartFile) args[0];

          return null;
        }
      )
      .when(fileStorageService)
      .store(mockFile);

    // req an controller senden
    ResponseEntity<ResponseMessage> response = fileController.uploadFile(mockFile);

    // überprüfung der antwort
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(
      "Uploaded the file successfully: testFile.txt",
      response.getBody().getMessage()
    );

    verify(fileStorageService, times(1)).store(mockFile);
  }
}
