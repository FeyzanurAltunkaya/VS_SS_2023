package com.example.projectFiler.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.projectFiler.entity.GroupEntity;
import org.junit.jupiter.api.Test;

class GroupEntityTest {

  /* @Test
    void testGetName() {
        // Create a new group entity
        GroupEntity group = new GroupEntity();
        group.setName("TestGroup");

        // Verify the group's name
        assertEquals("TestGroup", group.getName());
    }*/

  @Test
  void testGetId() {
    // Create a new group entity
    GroupEntity group = new GroupEntity();
    group.setId(1L);

    // Verify the group's ID
    assertEquals(1L, group.getId());
  }

  @Test
  void testSetId() {
    // Create a new group entity
    GroupEntity group = new GroupEntity();
    group.setId(1L);

    // Verify the group's ID
    assertEquals(1L, group.getId());

    // Set a new ID
    group.setId(2L);

    // Verify the updated ID
    assertEquals(2L, group.getId());
  }
  /*@Test
    void testSetName() {
        // Create a new group entity
        GroupEntity group = new GroupEntity();

        // Set the group's name using the setName() method
        group.setName("TestGroup");

        // Verify the group's name
        assertEquals("TestGroup", group.getName());
    }*/
}
