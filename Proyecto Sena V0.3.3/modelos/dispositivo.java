package modelos;

// Definición de la clase dispositivo
public class dispositivo {
    // Atributos de la clase dispositivo
    private String marca;
    private String modelo;
    private tipo tipoDispositivo;
    private float precio;
    private String descripcion;
    private boolean terminado;
    private boolean entregado;

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
    public dispositivo() {}

    // Constructor con parámetros para inicializar los atributos
    public dispositivo(String marca, String modelo, tipo tipoDispositivo, float precio, String descripcion) {
        // Asignación de valores a los atributos
        setDescripcion(descripcion);
        setEntregado(false);
        setTerminado(false);
        setMarca(marca);
        setModelo(modelo);
        setPrecio(precio);
        setTipoDispositivo(tipoDispositivo);
    }

    public void CambiarEstadoTerminado(){
        this.terminado = !this.terminado;
    }

    public void CambiarEstadoEntregado(){
        this.terminado = true;
        this.entregado = true;
    }

    // Métodos setters para asignar valores a los atributos
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }
    public void setTipoDispositivo(tipo tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    // Métodos getters para obtener los valores de los atributos
    public String getDescripcion() {
        return descripcion;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public float getPrecio() {
        return precio;
    }
    public tipo getTipoDispositivo() {
        return tipoDispositivo;
    }
    public boolean isEntregado() {
        return entregado;
    }
    public boolean isTerminado() {
        return terminado;
    }
}
