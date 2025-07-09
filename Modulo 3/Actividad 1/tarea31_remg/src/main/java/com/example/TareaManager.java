package com.example;

public class TareaManager {
  private final int id;
  private final String titulo;
  private final String descripcion;
    
    public Tarea crearTarea(int id, String titulo, String descripcion  ){
        this.id = id;
        this.titulo = titulo;
        this.descripcion =descripcion;
    
    }

public int getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }
 

  

}
