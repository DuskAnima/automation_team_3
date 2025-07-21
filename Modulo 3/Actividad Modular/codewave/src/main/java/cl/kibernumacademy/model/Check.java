package cl.kibernumacademy.model;

public class Check {
    private String nombre;
    private String tipo;
    private String horario;

    public Check (String nombre, String tipo, String horario) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.horario = horario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getHorario() {
        return horario;
    }

    public void setNombre(String newNombre) {
        this.nombre = newNombre;
    }

    public void setTipo(String newTipo) {
        this.tipo = newTipo;
    }

    public void setHorario(String newHorario) {
        this.horario = newHorario;
    }
}
