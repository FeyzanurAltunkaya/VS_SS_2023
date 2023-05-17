package com.example.projectFiler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDto {
  private Long id;

  @NotBlank(message = "FirstName Can't Be Null Or Empty")
  private String firstName;

  @NotBlank(message = "LastName Can't Be Null Or Empty")
  private String lastName;
}
