package cl.kibernaumacademy;

public class Tarea {

    public int id;
    public String titulo;
    public String descripcion;

    public Tarea( int id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public void setID (int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    } 

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    } 

    public int getId() {
        return id;
    }
    
    public String getTitulo () {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
