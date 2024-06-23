package modelos;

import java.util.ArrayList;
import java.time.LocalDate;

// Definición de la clase compra
public class compra {
    // Variables de Factura
    private String codigoFactura;
    private static int contadorFactura = 1;
    private static char letraReferencia = 'A';

    // Variables de compra
    private usuario tecnicoAsignado;
    private LocalDate fecha;
    private ArrayList<dispositivo> listaDispositivos;

    // Variables de pago
    private float totalPago;
    private float abono;
    private float deuda;

    // Variables Cliente
    private String nombreCliente;
    private String numeroTelefono;

    // Constructor para crear una compra con los datos necesarios
    public compra(usuario tecnicoAsignado, ArrayList<dispositivo> listaDispositivos, float abono, String nombreCliente, String numeroTelefono) {
        // Asignación de valores a los atributos
        setTecnicoAsignado(tecnicoAsignado);
        setListaDispositivos(listaDispositivos);
        setAbono(abono);
        setNombreCliente(nombreCliente);
        setNumeroTelefono(numeroTelefono);
        setFecha();
        setTotalPago();
        setDeuda();
        setCodigoFactura(numeroTelefono);
    }

    // Método toString para obtener una representación de cadena de la compra
    @Override
    public String toString() {
        return "compra [tecnicoAsignado=" + tecnicoAsignado.getNombre() + ", codigoFactura=" + codigoFactura + ", fecha=" + fecha
                + ", listaDispositivos=" + listaDispositivos + ", totalPago=" + totalPago + ", abono=" + abono
                + ", deuda=" + deuda + ", nombreCliente=" + nombreCliente + "]";
    }

    // Método para calcular el total del pago
    public void setTotalPago() {
        for (dispositivo d : listaDispositivos) {
            totalPago += d.getPrecio();
        }
    }

    // Método para calcular la deuda
    public void setDeuda() {
        deuda = totalPago - abono;
    }

    // Método para establecer la fecha de la compra como la fecha actual
    public void setFecha() {
        this.fecha = LocalDate.now();
    }

    // Método para generar el código de factura basado en el número de teléfono del cliente
    public void setCodigoFactura(String numeroTelefono) {
        String ultimosDosDigitosTelefono = numeroTelefono.substring(numeroTelefono.length() - 2);
        String numeroFactura = String.format("%04d", contadorFactura); // Asegura que el número de factura tenga al menos 4 dígitos, con ceros a la izquierda si es necesario
        codigoFactura = ultimosDosDigitosTelefono + "-" + numeroFactura + "-" + letraReferencia;
        contadorFactura += 1;
        if (contadorFactura > 9999) {
            letraReferencia++;
            contadorFactura = 0;
        }
    }

    // Métodos getters y setters para acceder a los atributos de la compra
    public usuario getTecnicoAsignado() {
        return tecnicoAsignado;
    }
    public void setTecnicoAsignado(usuario tecnicoAsignado) {
        this.tecnicoAsignado = tecnicoAsignado;
    }
    public String getCodigoFactura() {
        return codigoFactura;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public ArrayList<dispositivo> getListaDispositivos() {
        return listaDispositivos;
    }
    public void setListaDispositivos(ArrayList<dispositivo> listaDispositivos) {
        this.listaDispositivos = listaDispositivos;
    }
    public float getTotalPago() {
        return totalPago;
    }
    public float getAbono() {
        return abono;
    }
    public void setAbono(float abono) {
        this.abono = abono;
    }
    public float getDeuda() {
        return deuda;
    }
    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    public String getNumeroTelefono() {
        return numeroTelefono;
    }
}
