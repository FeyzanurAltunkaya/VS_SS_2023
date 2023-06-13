package com.example.projectFiler.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileEntity1Test {

    @Test
    public void testGetFileName() {
        String fileName = "test.txt";
        byte[] fileData = { 0x54, 0x65, 0x73, 0x74 };
        FileEntity1 fileEntity = new FileEntity1(fileName, fileData);

        String actualFileName = fileEntity.getFileName();

        assertNotNull(actualFileName, "File name should not be null");
        assertEquals(fileName, actualFileName, "File name should match");
    }

    @Test
    public void testGetFileData() {
        String fileName = "test.txt";
        byte[] fileData = { 0x54, 0x65, 0x73, 0x74 };
        FileEntity1 fileEntity = new FileEntity1(fileName, fileData);

        byte[] actualFileData = fileEntity.getFileData();

        assertNotNull(actualFileData, "File data should not be null");
        assertEquals(fileData.length, actualFileData.length, "File data length should match");
        for (int i = 0; i < fileData.length; i++) {
            assertEquals(fileData[i], actualFileData[i], "File data should match");
        }
    }

    @Test
    public void testSetFileName() {

        String fileName = "test.txt";
        byte[] fileData = { 0x54, 0x65, 0x73, 0x74 };
        FileEntity1 fileEntity = new FileEntity1(fileName, fileData);

        String newFileName = "new.txt";
        fileEntity.setFileName(newFileName);

        assertEquals(newFileName, fileEntity.getFileName(), "File name should be updated");
    }

    @Test
    public void testSetFileData() {

        String fileName = "test.txt";
        byte[] fileData = { 0x54, 0x65, 0x73, 0x74 };
        FileEntity1 fileEntity = new FileEntity1(fileName, fileData);

        byte[] newFileData = { 0x4E, 0x65, 0x77, 0x20, 0x44, 0x61, 0x74, 0x61 };
        fileEntity.setFileData(newFileData);

        assertNotNull(fileEntity.getFileData(), "File data should not be null");
        assertEquals(newFileData.length, fileEntity.getFileData().length, "File data length should match");
        for (int i = 0; i < newFileData.length; i++) {
            assertEquals(newFileData[i], fileEntity.getFileData()[i], "File data should match");
        }
    }
}
