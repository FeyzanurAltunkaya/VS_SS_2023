package com.example.projectFiler.entity;

import com.example.projectFiler.entity.DirectoryUserJoinEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DirectoryUserJoinEntityTest {

    @Test
    public void testConstructorAndGetters() {
        // Test Daten kreieren
        Long directoryId = 1L;
        Long userId = 2L;

        // erstelle DirectoryUserJoinEntity Instanz
        DirectoryUserJoinEntity joinEntity = new DirectoryUserJoinEntity(directoryId, userId);

        // Überprüfen der Werte mit getters
        assertEquals(directoryId, joinEntity.getDirectoryId());
        assertEquals(userId, joinEntity.getUserId());
    }
}