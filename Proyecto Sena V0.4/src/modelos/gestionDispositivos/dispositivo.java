package modelos.gestionDispositivos;

import java.util.HashMap;

// Definición de la clase dispositivo
public class dispositivo {

    private HashMap<String, Object> atributos;

    // Enumeración para los tipos de dispositivos
    public static enum tipo {
        celularAndroid,
        celularIphone,
        tablet,
        ipad,
        smartwach,
        bafle,
        otros
    }

    // Constructor predeterminado
    public dispositivo() {
        atributos = new HashMap<>();
        atributos.put("marca", "No definida");
        atributos.put("modelo", "No definido");
        atributos.put("tipoDispositivo", tipo.otros);
        atributos.put("precio", 0f);
        atributos.put("descripcion", "No definida");
        atributos.put("terminado", false);
        atributos.put("entregado", false);
    }

    public dispositivo(String marca, String modelo, tipo tipoDispositivo, float precio, String descripcion, boolean terminado, boolean entregado){
        atributos = new HashMap<>();
        atributos.put("marca", marca);
        atributos.put("modelo", modelo);
        atributos.put("tipoDispositivo", tipoDispositivo);
        atributos.put("precio", precio);
        atributos.put("descripcion", descripcion);
        atributos.put("terminado", terminado);
        atributos.put("entregado", entregado);
    }

    // Constructor con parámetros para inicializar los atributos
    public dispositivo(String marca, String modelo, tipo tipoDispositivo, float precio, String descripcion) {
        atributos = new HashMap<>();
        atributos.put("marca", marca);
        atributos.put("modelo", modelo);
        atributos.put("tipoDispositivo", tipoDispositivo);
        atributos.put("precio", precio);
        atributos.put("descripcion", descripcion);
        atributos.put("terminado", false);
        atributos.put("entregado", false);
    }

    public void cambiarEstadoTerminado() {
        boolean estadoActual = (boolean) atributos.get("terminado");
        atributos.put("terminado", !estadoActual);
    }

    public void cambiarEstadoEntregado() {
        atributos.put("terminado", true);
        atributos.put("entregado", true);
    }

    // Métodos setters para asignar valores a los atributos
    public void setDescripcion(String descripcion) {
        atributos.put("descripcion", descripcion);
    }

    public void setEntregado(boolean entregado) {
        atributos.put("entregado", entregado);
    }

    public void setMarca(String marca) {
        atributos.put("marca", marca);
    }

    public void setModelo(String modelo) {
        atributos.put("modelo", modelo);
    }

    public void setPrecio(float precio) {
        atributos.put("precio", precio);
    }

    public void setTerminado(boolean terminado) {
        atributos.put("terminado", terminado);
    }

    public void setTipoDispositivo(tipo tipoDispositivo) {
        atributos.put("tipoDispositivo", tipoDispositivo);
    }

    // Métodos getters para obtener los valores de los atributos
    public String getDescripcion() {
        return (String) atributos.getOrDefault("descripcion", "No definida");
    }

    public String getMarca() {
        return (String) atributos.getOrDefault("marca", "No definida");
    }

    public String getModelo() {
        return (String) atributos.getOrDefault("modelo", "No definido");
    }

    public float getPrecio() {
        return (float) atributos.getOrDefault("precio", 0f);
    }

    public tipo getTipoDispositivo() {
        return (tipo) atributos.getOrDefault("tipoDispositivo", tipo.otros);
    }

    public boolean isEntregado() {
        return (boolean) atributos.getOrDefault("entregado", false);
    }

    public boolean isTerminado() {
        return (boolean) atributos.getOrDefault("terminado", false);
    }

    public HashMap<String, Object> getAtributos() {
        return atributos;
    }
}
