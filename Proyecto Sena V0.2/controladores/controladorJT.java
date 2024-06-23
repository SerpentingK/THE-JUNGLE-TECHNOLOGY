package controladores;

import vistas.vistaJT;
import modelos.*;

import java.util.ArrayList;

// Definición de la clase controladorJT
public class controladorJT {
    // Listas para almacenar usuarios y compras
    private ArrayList<usuario> baseUsuarios = new ArrayList<>();
    private ArrayList<compra> baseCompras = new ArrayList<>();
    private usuario usuarioActual = new usuario("Gucci");

    // Método principal para ejecutar el programa
    public void Ejecutar(){
        boolean inProgram = true;
        while (inProgram) {
            // Mostrar el menú y obtener la opción seleccionada por el usuario
            int r = vistaJT.Menu();
            // Realizar acciones dependiendo de la opción seleccionada
            switch (r) {
                case 1:
                    // Solicitar y crear una nueva compra
                    ArrayList<dispositivo> dispositivos = vistaJT.RecibirDispositivos();
                    float abono = vistaJT.recibirAbono();
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
                case 10:
                    // Salir del programa
                    inProgram = false;
                    break;
                default:
                    break;
            }
        }
    }
}
