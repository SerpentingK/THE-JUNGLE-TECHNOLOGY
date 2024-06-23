import java.util.ArrayList;

public class compra {
    private String codigoFactura;
        private String nombreCliente;
        private String fecha;
        private ArrayList<dispositivo> listaDispositivos;
        private float totalPago;
        private float abono;
        private float deuda;

    public String getCodigoFactura() {
        return codigoFactura;
    }
    public String getNombreCliente() {
            return nombreCliente;
    }
    public String getFecha() {
        return fecha;
    }
    public float getTotalPago() {
        return totalPago;
    }
    public float getAbono() {
        return abono;
    }
    public float getDeuda() {
        return deuda;
    }
    public ArrayList<dispositivo> getListaDispositivos() {
        return listaDispositivos;
    }



    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }
    
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setListaDispositivos(ArrayList<dispositivo> listaDispositivos) {
        this.listaDispositivos = listaDispositivos;
    }
    public void setTotalPago(float totalPago) {
        this.totalPago = totalPago;
    }
    public void setAbono(float abono) {
        this.abono = abono;
    }
    public void setDeuda(float deuda) {
        this.deuda = deuda;
    }
    public compra(String nombreCliente, ArrayList<dispositivo> listaDispositivos, float abono) {
        setListaDispositivos(listaDispositivos);
        setNombreCliente(nombreCliente);
        setAbono(abono);
        setCodigoFactura("0001-A");
    }

    public void String(){
        System.out.println("Nombre: " + getNombreCliente());
        System.out.println("Numero factura: " + getCodigoFactura());
        for (dispositivo p : listaDispositivos){
            System.out.printf("Producto: %s || Precio: %.2f || Estado: %b || Entregado: %b%n",p.getMarca() + " " + p.getModelo(), p.getPrecio(), p.isTerminado(), p.isEntregado());
        }
    }

    

    
}
