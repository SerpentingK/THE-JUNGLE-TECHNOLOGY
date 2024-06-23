package controladores;

import vistas.*;
import modelos.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Definición de la clase controladorJT
public class controladorJT {
    // Listas para almacenar usuarios y compras
    //private ArrayList<usuario> baseUsuarios = new ArrayList<>();
    private bd_compras base_compras = new bd_compras();
    private usuario usuarioActual = new usuario("Gucci");

    // Método principal para ejecutar el programa
    public void Ejecutar(){
        //Añadir una compra por defecto PARA PRUEBAS
        
        boolean inProgram = true;
        while (inProgram) {
            // Mostrar el menú y obtener la opción seleccionada por el usuario
            int r = vistaJT.Menu();
            // Realizar acciones dependiendo de la opción seleccionada
            switch (r) {
                case 1:
                    // Solicitar y crear una nueva compra
                    ArrayList<dispositivo> dispositivos = vistaJT.RecibirDispositivos();
                    float abono = vistaJT.RecibirAbono();
                    String[] datos = vistaJT.RecibirDatosCliente();
                    compra nuevaCompra = new compra(usuarioActual, dispositivos, abono, datos[0], datos[1]);
                    base_compras.AñadirCompra(nuevaCompra);
                    break;
                case 2:
                    // Mostrar la lista de compras
                    for(compra c: base_compras.getBd_compras()){
                        vistaJT.ImprimirDatosCompra(c);
                    }
                    break;
                case 3:
                    //Buscar y mostrar compra por codigo
                    String codigo = vistaJT.RecibirCodigo();
                    vistaJT.ImprimirDatosCompra(base_compras.BuscarPorCodigo(codigo));
                    break;
                case 4:
                    //Buscar y mostrar compras por fecha
                    String fecha = vistaJT.RecibirFecha();
                    ArrayList<compra> comprasPorFecha = BuscarListaDeComprasPorFecha(fecha);
                    System.out.println("COMPRAS POR FECHA: " + fecha);
                    for(compra c: comprasPorFecha){
                        vistaJT.ImprimirDatosCompra(c);
                    }
                    break;
                case 5:
                    //Buscar y mostrar compra por nombre
                    String nombre = vistaJT.RecibirNombre();
                    ArrayList<compra> comprasPorNombre = BuscarPorNombre(nombre);
                    if(comprasPorNombre.isEmpty()){
                        vistaJT.ErrorListaVacia();
                    }else{
                        for(compra c: comprasPorNombre){
                            vistaJT.ImprimirDatosCompra(c);
                        }
                    }
                    break;
                case 6:
                    String codigoTerminado = vistaJT.RecibirCodigo();
                    base_compras.CambiarEstadoTerminado(codigoTerminado);
                    break;
                case 7:
                    String codigoEntregado = vistaJT.RecibirCodigo();
                    base_compras.CambiarEstadoEntregado(codigoEntregado);
                    break;

                case 10:
                    // Salir del programa
                    inProgram = false;
                    break;
                default:
                    break;
            }
        }
    }

    public ArrayList<compra> BuscarListaDeComprasPorFecha(String fecha){
        ArrayList<compra> comprasPorFecha = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaLocalDate = LocalDate.parse(fecha, formatter);

        for(compra c : base_compras.getBd_compras()){
            if(c.getFecha().equals(fechaLocalDate)){
                comprasPorFecha.add(c);
            }
        }
        return comprasPorFecha;
    }
    public ArrayList<compra> BuscarPorNombre(String nombre){
        ArrayList<compra> comprasPorNombre = new ArrayList();
        for(compra c: base_compras.getBd_compras()){
            if (c.getNombreCliente().toLowerCase().equals(nombre)) {
                comprasPorNombre.add(c);              
            }
        }
        return comprasPorNombre;
    }
}
