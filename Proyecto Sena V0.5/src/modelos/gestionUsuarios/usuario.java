package modelos.gestionUsuarios;
import java.util.HashMap;


public class usuario {
    private HashMap<String, Object> atributos;

    public usuario(String nombre, String clave, String documento){
        atributos = new HashMap<>();
        atributos.put("nombre", nombre);
        atributos.put("clave", clave);
        atributos.put("documento", documento);
    }

    // Métodos setters para asignar valores a los atributos
    public void setNombre(String nombre) {
        atributos.put("nombre", nombre);
    }

    public void setClave(String clave) {
        atributos.put("clave", clave);
    }

    public void setDocumento(String documento) {
        atributos.put("documento", documento);
    }

    // Métodos getters para obtener los valores de los atributos
    public String getNombre() {
        return (String) atributos.getOrDefault("nombre", "No definido");
    }

    public String getClave() {
        return (String) atributos.getOrDefault("clave", "No definida");
    }

    public String getDocumento() {
        return (String) atributos.getOrDefault("documento", "No definido");
    }

    // Método para obtener todos los atributos
    public HashMap<String, Object> getAtributos() {
        return atributos;
    }
}
