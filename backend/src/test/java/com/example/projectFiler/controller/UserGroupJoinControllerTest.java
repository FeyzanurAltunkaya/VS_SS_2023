package com.example.projectFiler.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.projectFiler.entity.GroupEntity;
import com.example.projectFiler.entity.UserEntity;
import com.example.projectFiler.entity.UserGroupJoinEntity;
import com.example.projectFiler.repository.GroupRepository;
import com.example.projectFiler.repository.UserGroupJoinRepository;
import com.example.projectFiler.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserGroupJoinControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserGroupJoinRepository userGroupJoinRepository;

  @MockBean
  private UserRepository userRepository;

  @MockBean
  private GroupRepository groupRepository;

  /*
  @Test
  public void testAddUserToGroup() throws Exception {
    // Mock user and group
    UserEntity user = new UserEntity();
    user.setId(1L);
    GroupEntity group = new GroupEntity();
    group.setId(1L);

    // Mock UserRepository and GroupRepository
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    when(groupRepository.findById(1L)).thenReturn(Optional.of(group));

    // Perform the POST request
    mockMvc
            .perform(post("/usergroup/{userId}/groups/{groupId}", user.getId(), group.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.user.id", is(user.getId().intValue())))
            .andExpect(jsonPath("$.group.id", is(group.getId().intValue())))
            .andReturn();

    // Verify that the save method was called
    verify(userGroupJoinRepository, times(1))
            .save(ArgumentMatchers.any(UserGroupJoinEntity.class));
  } */

  @Test
  public void testRemoveUserFromGroup() throws Exception {
    // Mock UserEntity
    UserEntity userEntity = new UserEntity();
    userEntity.setId(1L);

    // Mock GroupEntity
    GroupEntity groupEntity = new GroupEntity();
    groupEntity.setId(1L);

    // Mock UserRepository
    when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

    // Mock GroupRepository
    when(groupRepository.findById(1L)).thenReturn(Optional.of(groupEntity));

    // Perform the DELETE request
    mockMvc
      .perform(delete("/usergroup/{userId}/groups/{groupId}", 1L, 1L))
      .andExpect(status().isNoContent());

    // Verify the interactions
    verify(userRepository, times(1)).findById(1L);
    verify(groupRepository, times(1)).findById(1L);
    verify(userGroupJoinRepository, times(1))
      .deleteByUserAndGroup(userEntity, groupEntity);
  }
}
