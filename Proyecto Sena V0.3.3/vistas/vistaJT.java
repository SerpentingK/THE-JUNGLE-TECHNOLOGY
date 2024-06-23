package vistas;

import controladores.*;
import java.util.ArrayList;
import java.util.Scanner;
import modelos.*;

// Definición de la clase vistaJT
public class vistaJT {
    private static Scanner scan = new Scanner(System.in);
    private static controladorJT c = new controladorJT();

    //metodo ejeucutar
    public static void Ejecutar(){
        c.Ejecutar();
    }

    // Método para mostrar el menú y obtener la opción seleccionada por el usuario
    public static int Menu(){
        System.out.println("1. Nueva Compra\n2. Consultar lista de compras.\n3. Consultar Cliente por codigo\n4. Consultar Lista de clientes por fecha\n5. Consultar cliente por nombre\n6. Cambiar estado terminado\n7. Cambiar estado entregado");
        System.out.println("Elije opcion: ");
        int r = scan.nextInt();
        scan.nextLine();
        return r;
    }
    public static void ErrorListaVacia(){
        System.out.println("Lista vacia.");
    }
    public static void ErrorFueraDeRango(){
        System.out.println("Fuera del rango");
    }
    public static void ErrorCelularEntregado(){
        System.out.println("Celular ya ha sido entregado");
    }

    // Método para recibir los datos del cliente
    public static String[] RecibirDatosCliente(){
        String[] datos = {"", ""};
        System.out.println("Ingresa nombre cliente: ");
        String dato = scan.nextLine().toLowerCase();
        datos[0] = dato;
        System.out.println("Ingrese numero de telefono de cliente.");
        dato = scan.nextLine();
        datos[1] = dato;
        return datos;
    }

    // Método para recibir los detalles de los dispositivos
    public static ArrayList<dispositivo> RecibirDispositivos(){
        boolean x = true;
        ArrayList<dispositivo> dispositivos = new ArrayList<>();
        while(x){
            dispositivo d = AñadirDispositivo();
            dispositivos.add(d);
            boolean y = true;
            while(y){
                System.out.println("Deseas añadir otro dispositivo?");
                String r = scan.nextLine();
                if(r.toLowerCase().equals("si")){
                    y = false;
                }else{
                    y = false;
                    x = false;
                }
            }
        }
        return dispositivos;
    }

    // Método para recibir los detalles de un dispositivo
    public static dispositivo AñadirDispositivo(){
        System.out.println("Ingrese Marca producto: ");
        String marca = scan.nextLine();
        System.out.println("Ingrese modelo del dispositivo: ");
        String modelo = scan.nextLine();
        System.out.println("Ingrese El valor del mantenimiento: ");
        float precio = scan.nextFloat();
        scan.nextLine();
        System.out.println("Ingrese la descripcion del mantenimiento : ");
        String descripcion = scan.nextLine();
        boolean y = true;
        while(y){
            System.out.println("Ingrese que tipo de dispositivo es:\n1. Celular Android.\n2. Celular Iphone.\n3. Tablet.\n4. Ipad.\n5. Smartwach.\n6.Bafle.\n7.Otros.");
            int r = scan.nextInt();
            scan.nextLine();
            dispositivo.tipo tipo = dispositivo.tipo.celularAndroid;
            switch (r) {
                case 1:
                    y = false;
                    break;
                case 2:
                    y = false;
                    tipo = dispositivo.tipo.celularIphone;
                    break;
                case 3:
                    y = false;
                    tipo = dispositivo.tipo.tablet;
                    break;
                case 4:
                    y = false;
                    tipo = dispositivo.tipo.ipad;
                    break;
                case 5:
                    y = false;
                    tipo = dispositivo.tipo.smartwach;
                    break;
                case 6:
                    y = false;
                    tipo = dispositivo.tipo.bafle;
                    break;
                case 7:
                    y = false;
                    tipo = dispositivo.tipo.otros;
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
            dispositivo nuevoDispositivo = new dispositivo(marca, modelo, tipo, precio, descripcion);
            return nuevoDispositivo;
        }
        return new dispositivo();
    }

    // Método para recibir el abono de la compra
    public static float RecibirAbono(){
        System.out.println("Ingrese abono: ");
        float abono = scan.nextFloat();
        scan.nextLine();
        return abono;
    }

    // Método para imprimir los detalles de una compra
    public static void ImprimirDatosCompra(compra c){
        System.out.println("THE JUNGLE TECHNOLOGY");
        System.out.println("Tecnico asignado: " + c.getTecnicoAsignado().getNombre() );
        System.out.println("Numero factura: " + c.getCodigoFactura());
        System.out.println("Fecha: " + c.getFecha());
        System.out.println("Nombre Cliente: " + c.getNombreCliente());
        System.out.println("Numero de telefono: " + c.getNumeroTelefono());
        ImprimirListaDispositivos(c.getListaDispositivos()); 
        System.out.println("Total de pago: " + c.getTotalPago());
        System.out.println("Abono: " + c.getAbono());
        System.out.println("Deuda: " + c.getDeuda());
        System.out.println("---------------------------------");
    }

    // Método para imprimir la lista de dispositivos de una compra
    public static void ImprimirListaDispositivos(ArrayList<dispositivo> dispositivos){
        for(dispositivo d: dispositivos){
            System.out.printf("Celular: %s %s || Precio Mantenimiento: %f || Terminado: %b || Entregado: %b\n", d.getMarca(), d.getModelo(), d.getPrecio(), d.isTerminado(), d.isEntregado());
            System.out.printf("Descipcion Mantenimiento:\n%s\n", d.getDescripcion());
            System.out.println("______________________________________________________________________");
        }
    }


    public static String RecibirCodigo(){
        System.out.println("Ingrese codigo: ");
        String codigo = scan.nextLine();
        return codigo;

    }

    public static String RecibirFecha(){
        System.out.println("Ingrese la fecha (yyyy-MM-dd): ");
        String fecha = scan.nextLine();
        return fecha;
    }

    public static String RecibirNombre(){
        System.out.println("Ingrese el nombre:");
        String nombre = scan.nextLine();
        return nombre;
    }

    public static int RecibirInt(){
        System.out.println("Elija opcion.");
        int r = scan.nextInt();
        scan.nextLine();
        return r;
    }

    
}
