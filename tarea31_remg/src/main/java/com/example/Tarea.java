package com.example;

public class Tarea {
  private final int id;
  private final String titulo;
  private final String descripcion;
    
    public Tarea(int id, String titulo, String descripcion  ){
        this.id = id;
        this.titulo = titulo;
        this.descripcion =descripcion;
    
    }

public int getId() {
    return id;
  }

  public String getClient() {
    return titulo;
  }

  public String getAmount() {
    return descripcion;
  }
 

  

}
