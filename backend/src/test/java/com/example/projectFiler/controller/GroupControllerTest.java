package com.example.projectFiler.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.projectFiler.entity.GroupEntity;
import com.example.projectFiler.repository.GroupRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GroupControllerTest {
  @Mock
  private GroupRepository groupRepository;

  @InjectMocks
  private GroupController groupController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createGroup_shouldReturnCreatedGroup() {
    GroupEntity group = new GroupEntity();
    group.setId(1L);
    group.setName("Test Group");

    when(groupRepository.save(any(GroupEntity.class))).thenReturn(group);

    ResponseEntity<GroupEntity> response = groupController.createGroup(group);

    // assert group created?
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(group, response.getBody());
    verify(groupRepository, times(1)).save(group);
  }

  @Test
  void getGroupById_nonExistingGroupId_shouldReturnNotFound() {
    Long groupId = 1L;

    when(groupRepository.findById(groupId)).thenReturn(Optional.empty());

    ResponseEntity<GroupEntity> response = groupController.getGroupById(groupId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(groupRepository, times(1)).findById(groupId);
  }

  @Test
  void deleteGroup_nonExistingGroupId_shouldReturnNotFound() {
    Long groupId = 1L;

    when(groupRepository.findById(groupId)).thenReturn(Optional.empty());

    ResponseEntity<Void> response = groupController.deleteGroup(groupId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(groupRepository, times(1)).findById(groupId);
    verifyNoMoreInteractions(groupRepository);
  }
}
