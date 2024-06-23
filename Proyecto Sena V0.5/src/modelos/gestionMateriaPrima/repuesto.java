package modelos.gestionMateriaPrima;

import java.util.HashMap;

public class repuesto {
    private HashMap<String, Object> atributos;
    public repuesto(String tipo,int unidades){
        atributos = new HashMap<>();
        atributos.put("dispositivo", tipo);
        atributos.put("unidades", unidades);
    }

    //Añadir y eliminar unidades

    public void actualizarUnidades(int unidades){
        setUnidades(getUnidades() + unidades);
    }
    // Métodos getters

    public String getTipo() {
        return (String) atributos.getOrDefault("dispositivo", "No definida");
    }

    public int getUnidades() {
        return (int) atributos.getOrDefault("unidades", 0);
    }

    public HashMap<String, Object> getAtributos() {
        return atributos;
    }

    // Métodos setters

    public void setUnidades(int unidades) {
        atributos.put("unidades", unidades);
    }

    public void setTipo(String tipo) {
        atributos.put("dispositivo", tipo);
    }

    public void setAtributos(HashMap<String, Object> atributos) {
        this.atributos = atributos;
    }
}
