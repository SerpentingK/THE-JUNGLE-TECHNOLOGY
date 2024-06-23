public class dispositivo {
    private String marca;
    private String modelo;
    private tipo tipoDispositivo;
    private float precio;
    private String descripcion;

    private boolean terminado;
    private boolean entregado;
    
    public static enum tipo{
        celularAndroid,
        celularIphone,
        tablet,
        ipad,
        smartwach,
        bafle,
        otros
    }

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

    public dispositivo(String marca, String modelo, tipo tipoDispositivo, float precio, String descripcion){
        setDescripcion(descripcion);
        setEntregado(false);
        setTerminado(false);
        setMarca(marca);
        setModelo(modelo);
        setPrecio(precio);
        setTipoDispositivo(tipoDispositivo);
    }
}
