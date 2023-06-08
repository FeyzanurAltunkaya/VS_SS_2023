package com.example.projectFiler.payload.response;

public class MessageResponse {
  private String message;

  public MessageResponse(String s) {
    this.message = s;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String s) {
    this.message = message;
  }
}
