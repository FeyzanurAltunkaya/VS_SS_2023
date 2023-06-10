package com.example.projectFiler.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;

  private String name;

  private String type;

  @Lob
  private byte[] data;

  public FileEntity() {}

  public FileEntity(String name, String type, byte[] data) {
    this.name = name;
    this.type = type;
    this.data = data;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }
}
