package vistas;

import controladores.controladorJT;
import modelos.compra;
import modelos.dispositivo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class vistaJT {
    private static Scanner scan = new Scanner(System.in);
    private static controladorJT c = new controladorJT();

    public static void Ejecutar(){
        c.ejecutar();
    }

    public static int Menu(){
        return Integer.parseInt(JOptionPane.showInputDialog(null,
                "1. Nueva Compra\n2. Consultar lista de compras.\n3. Consultar Cliente por código\n4. Consultar Lista de clientes por fecha\n5. Consultar cliente por nombre\n6. Cambiar estado terminado\n7. Cambiar estado entregado",
                "Menú", JOptionPane.QUESTION_MESSAGE));
    }

    public static void ErrorListaVacia(){
        JOptionPane.showMessageDialog(null, "Lista vacía.");
    }

    public static void ErrorFueraDeRango(){
        JOptionPane.showMessageDialog(null, "Fuera del rango.");
    }

    public static void ErrorCelularEntregado(){
        JOptionPane.showMessageDialog(null, "Celular ya ha sido entregado.");
    }

    public static String[] RecibirDatosCliente(){
        String[] datos = new String[2];
        datos[0] = JOptionPane.showInputDialog(null, "Ingresa nombre cliente: ", "Datos Cliente", JOptionPane.QUESTION_MESSAGE).toLowerCase();
        datos[1] = JOptionPane.showInputDialog(null, "Ingrese número de teléfono de cliente: ", "Datos Cliente", JOptionPane.QUESTION_MESSAGE);
        return datos;
    }

    public static ArrayList<dispositivo> RecibirDispositivos(){
        ArrayList<dispositivo> dispositivos = new ArrayList<>();
        boolean añadirOtro = true;
        while (añadirOtro) {
            dispositivos.add(AñadirDispositivo());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Deseas añadir otro dispositivo?", "Añadir Dispositivo", JOptionPane.YES_NO_OPTION);
            añadirOtro = (respuesta == JOptionPane.YES_OPTION);
        }
        return dispositivos;
    }

    public static dispositivo AñadirDispositivo(){
        String marca = JOptionPane.showInputDialog(null, "Ingrese Marca producto: ", "Añadir Dispositivo", JOptionPane.QUESTION_MESSAGE);
        String modelo = JOptionPane.showInputDialog(null, "Ingrese modelo del dispositivo: ", "Añadir Dispositivo", JOptionPane.QUESTION_MESSAGE);
        float precio = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese El valor del mantenimiento: ", "Añadir Dispositivo", JOptionPane.QUESTION_MESSAGE));
        String descripcion = JOptionPane.showInputDialog(null, "Ingrese la descripción del mantenimiento: ", "Añadir Dispositivo", JOptionPane.QUESTION_MESSAGE);
        dispositivo.tipo tipo = seleccionarTipoDispositivo();
        return new dispositivo(marca, modelo, tipo, precio, descripcion);
    }

    private static dispositivo.tipo seleccionarTipoDispositivo() {
        String[] opciones = {"Celular Android", "Celular iPhone", "Tablet", "iPad", "Smartwatch", "Bafle", "Otros"};
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el tipo de dispositivo:", "Tipo Dispositivo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        switch (seleccion) {
            case 1:
                return dispositivo.tipo.celularIphone;
            case 2:
                return dispositivo.tipo.tablet;
            case 3:
                return dispositivo.tipo.ipad;
            case 4:
                return dispositivo.tipo.smartwach;
            case 5:
                return dispositivo.tipo.bafle;
            case 6:
                return dispositivo.tipo.otros;
            default:
                return dispositivo.tipo.celularAndroid;
        }
    }

    public static float RecibirAbono(){
        return Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese abono: ", "Recibir Abono", JOptionPane.QUESTION_MESSAGE));
    }

    public static void ImprimirDatosCompra(compra c){
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("THE JUNGLE TECHNOLOGY\n");
        mensaje.append("Técnico asignado: ").append(c.getTecnicoAsignado().getNombre()).append("\n");
        mensaje.append("Número factura: ").append(c.getCodigoFactura()).append("\n");
        mensaje.append("Fecha: ").append(c.getFecha()).append("\n");
        mensaje.append("Nombre Cliente: ").append(c.getNombreCliente()).append("\n");
        mensaje.append("Número de teléfono: ").append(c.getNumeroTelefono()).append("\n");
        mensaje.append(ImprimirListaDispositivos(c.getListaDispositivos())).append("\n");
        mensaje.append("Total de pago: ").append(c.getTotalPago()).append("\n");
        mensaje.append("Abono: ").append(c.getAbono()).append("\n");
        mensaje.append("Deuda: ").append(c.getDeuda()).append("\n");
        mensaje.append("---------------------------------");
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Detalles Compra", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String ImprimirListaDispositivos(ArrayList<dispositivo> dispositivos){
        StringBuilder mensaje = new StringBuilder();
        for (dispositivo d : dispositivos) {
            mensaje.append(String.format("Dispositivo: %s %s || Precio Mantenimiento: %.2f || Terminado: %b || Entregado: %b\n",
                    d.getMarca(), d.getModelo(), d.getPrecio(), d.isTerminado(), d.isEntregado()));
            mensaje.append(String.format("Descripción Mantenimiento:\n%s\n", d.getDescripcion()));
            mensaje.append("______________________________________________________________________\n");
        }
        return mensaje.toString();
    }

    public static String RecibirCodigo(){
        return JOptionPane.showInputDialog(null, "Ingrese código: ", "Recibir Código", JOptionPane.QUESTION_MESSAGE);
    }

    public static String RecibirFecha(){
        return JOptionPane.showInputDialog(null, "Ingrese la fecha (yyyy-MM-dd): ", "Recibir Fecha", JOptionPane.QUESTION_MESSAGE);
    }

    public static String RecibirNombre(){
        return JOptionPane.showInputDialog(null, "Ingrese el nombre: ", "Recibir Nombre", JOptionPane.QUESTION_MESSAGE);
    }

    public static int RecibirInt(String message){
        return Integer.parseInt(JOptionPane.showInputDialog(null, message, "Recibir Opción", JOptionPane.QUESTION_MESSAGE));
    }
}
