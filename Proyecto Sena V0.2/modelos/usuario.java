package modelos;

public class usuario {
    private String nombre;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public usuario(String nombre){
        setNombre(nombre);
    }
}
