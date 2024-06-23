package modelos.gestionDispositivos;

import modelos.gestionUsuarios.usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

// Definición de la clase compra
public class compra {
    private HashMap<String, Object> atributos;

    // Variables de Factura
    public static int contadorFactura = 1;
    public static char letraReferencia = 'A';

    // Constructor para crear una compra con los datos necesarios
    public compra(usuario tecnicoAsignado, ArrayList<dispositivo> listaDispositivos, float abono, String nombreCliente, String numeroTelefono) {
        atributos = new HashMap<>();
        setTecnicoAsignado(tecnicoAsignado);
        setListaDispositivos(listaDispositivos);
        setAbono(abono);
        setNombreCliente(nombreCliente);
        setNumeroTelefono(numeroTelefono);
        setFecha();
        setTotalPago();
        setDeuda();
        setCodigoFactura();
    }

    public compra(usuario tecnicoAsignado, ArrayList<dispositivo> listaDispositivos, float abono, String nombreCliente, String numeroTelefono, String fecha, float totalPago, float deuda, String codigoFactura){
        atributos = new HashMap<>();
        setTecnicoAsignado(tecnicoAsignado);
        setListaDispositivos(listaDispositivos);
        setAbono(abono);
        setNombreCliente(nombreCliente);
        setNumeroTelefono(numeroTelefono);
        atributos.put("fecha", fecha);
        atributos.put("totalPago", totalPago);
        atributos.put("deuda", deuda);
        atributos.put("codigoFactura", codigoFactura);
    }

    // Método toString para obtener una representación de cadena de la compra


    @Override
    public String toString() {
        return "compra{" +
                "atributos=" + atributos +
                '}';
    }

    // Método para calcular el total del pago
    public void setTotalPago() {
        float total = 0;
        for (dispositivo d : getListaDispositivos()) {
            total += d.getPrecio();
        }
        atributos.put("totalPago", total);
    }

    // Método para calcular la deuda
    public void setDeuda() {
        atributos.put("deuda", getTotalPago() - getAbono());
    }

    // Método para establecer la fecha de la compra como la fecha actual
    public void setFecha() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        atributos.put("fecha", LocalDate.now().format(formatter)); // Convierte LocalDate a String
    }

    // Método para generar el código de factura basado en el número de teléfono del cliente
    public void setCodigoFactura() {
        String numeroFactura = String.format("%04d", contadorFactura); // Asegura que el número de factura tenga al menos 4 dígitos, con ceros a la izquierda si es necesario
        String codigoFactura = numeroFactura + "-" + letraReferencia;
        atributos.put("codigoFactura", codigoFactura);
        contadorFactura += 1;
        if (contadorFactura > 9999) {
            letraReferencia++;
            contadorFactura = 0;
        }
    }
    public LocalDate getFechaAsLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse((String) atributos.get("fecha"), formatter); // Convierte String a LocalDate
    }

    // Métodos getters y setters para acceder a los atributos de la compra
    public usuario getTecnicoAsignado() {
        return (usuario) atributos.get("tecnicoAsignado");
    }

    public void setTecnicoAsignado(usuario tecnicoAsignado) {
        atributos.put("tecnicoAsignado", tecnicoAsignado);
    }

    public String getCodigoFactura() {
        return (String) atributos.get("codigoFactura");
    }

    public String getFecha() {
        return (String) atributos.get("fecha");
    }

    public ArrayList<dispositivo> getListaDispositivos() {
        return (ArrayList<dispositivo>) atributos.get("listaDispositivos");
    }

    public void setListaDispositivos(ArrayList<dispositivo> listaDispositivos) {
        atributos.put("listaDispositivos", listaDispositivos);
    }

    public float getTotalPago() {
        return (float) atributos.get("totalPago");
    }

    public float getAbono() {
        return (float) atributos.get("abono");
    }

    public void setAbono(float abono) {
        atributos.put("abono", abono);
    }

    public float getDeuda() {
        return (float) atributos.get("deuda");
    }

    public String getNombreCliente() {
        return (String) atributos.get("nombreCliente");
    }

    public void setNombreCliente(String nombreCliente) {
        atributos.put("nombreCliente", nombreCliente);
    }

    public void setNumeroTelefono(String numeroTelefono) {
        atributos.put("numeroTelefono", numeroTelefono);
    }

    public String getNumeroTelefono() {
        return (String) atributos.get("numeroTelefono");
    }
}
