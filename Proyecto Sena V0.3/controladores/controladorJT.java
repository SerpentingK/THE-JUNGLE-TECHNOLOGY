package controladores;

import vistas.vistaJT;
import modelos.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Definición de la clase controladorJT
public class controladorJT {
    // Listas para almacenar usuarios y compras
    //private ArrayList<usuario> baseUsuarios = new ArrayList<>();
    private ArrayList<compra> baseCompras = new ArrayList<>();
    private usuario usuarioActual = new usuario("Gucci");

    // Método principal para ejecutar el programa
    public void Ejecutar(){
        //Añadir una compra por defecto PARA PRUEBAS
        ArrayList<dispositivo> lista = new ArrayList<>();
        lista.add(new dispositivo("Samsung", "A14", dispositivo.tipo.celularAndroid, 20000, "a"));
        compra x = new compra(usuarioActual, lista, 0, "juan", "3203169321");
        baseCompras.add(x);
        baseCompras.add(x);
        x = new compra(usuarioActual, lista, 0, "sofia", "3203169321");
        baseCompras.add(x);
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
                    baseCompras.add(nuevaCompra);
                    break;
                case 2:
                    // Mostrar la lista de compras
                    for(compra c: baseCompras){
                        vistaJT.ImprimirDatosCompra(c);
                    }
                    break;
                case 3:
                    //Buscar y mostrar compra por codigo
                    String codigo = vistaJT.RecibirCodigo();
                    vistaJT.ImprimirDatosCompra(vistaJT.BuscarPorCodigo(codigo, baseCompras));
                    break;
                case 4:
                    //Buscar y mostrar compras por fecha
                    String fecha = vistaJT.RecibirFecha();
                    ArrayList<compra> comprasPorFecha = BuscarListaDeComprasPorFecha(fecha);
                    for(compra c: comprasPorFecha){
                        vistaJT.ImprimirDatosCompra(c);
                    }
                    break;
                case 5:
                    //Buscar y mostrar compra por nombre
                    String nombre = vistaJT.RecibirNombre();
                    ArrayList<compra> comprasPorNombre = vistaJT.BuscarPorNombre(nombre, baseCompras);
                    if(comprasPorNombre.isEmpty()){
                        vistaJT.ErrorListaVacia();
                    }else{
                        for(compra c: comprasPorNombre){
                            vistaJT.ImprimirDatosCompra(c);
                        }
                    }
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

        for(compra c : baseCompras){
            if(c.getFecha().equals(fechaLocalDate)){
                comprasPorFecha.add(c);
            }
        }
        return comprasPorFecha;
    }
}
