package com.example.ahmedco.pdfreader;


public class PDFDoc {
  String name;
  String path;

  public String getName() {
    return name.toLowerCase();
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}