package controladores;

import vistas.vistaJT;
import modelos.bd_compras;
import modelos.compra;
import modelos.dispositivo;
import modelos.usuario;

import java.util.ArrayList;

// Definición de la clase controladorJT
public class controladorJT {
    private final bd_compras base_compras = new bd_compras();
    private final usuario usuarioActual = new usuario("Gucci", "2004", "111111");

    // Método principal para ejecutar el programa
    public void ejecutar() {
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
                    base_compras.añadirCompra(nuevaCompra);
                    break;
                case 2:
                    // Mostrar la lista de compras
                    if (base_compras.getCompras().isEmpty()) {
                        vistaJT.ErrorListaVacia();
                    } else {
                        for (compra c : base_compras.getCompras().values()) {
                            vistaJT.ImprimirDatosCompra(c);
                        }
                    }
                    break;
                case 3:
                    // Buscar y mostrar compra por código
                    String codigo = vistaJT.RecibirCodigo();
                    compra compraPorCodigo = base_compras.buscarPorCodigo(codigo);
                    if (compraPorCodigo != null) {
                        vistaJT.ImprimirDatosCompra(compraPorCodigo);
                    } else {
                        vistaJT.ErrorListaVacia();
                    }
                    break;
                case 4:
                    // Buscar y mostrar compras por fecha
                    String fecha = vistaJT.RecibirFecha();
                    ArrayList<compra> comprasPorFecha = BuscarListaDeComprasPorFecha(fecha);
                    if (comprasPorFecha.isEmpty()) {
                        vistaJT.ErrorListaVacia();
                    } else {
                        for (compra c : comprasPorFecha) {
                            vistaJT.ImprimirDatosCompra(c);
                        }
                    }
                    break;
                case 5:
                    // Buscar y mostrar compra por nombre
                    String nombre = vistaJT.RecibirNombre();
                    ArrayList<compra> comprasPorNombre = BuscarPorNombre(nombre);
                    if (comprasPorNombre.isEmpty()) {
                        vistaJT.ErrorListaVacia();
                    } else {
                        for (compra c : comprasPorNombre) {
                            vistaJT.ImprimirDatosCompra(c);
                        }
                    }
                    break;
                case 6:
                    // Cambiar estado a terminado
                    String codigoTerminado = vistaJT.RecibirCodigo();
                    base_compras.cambiarEstadoTerminado(codigoTerminado);
                    break;
                case 7:
                    // Cambiar estado a entregado
                    String codigoEntregado = vistaJT.RecibirCodigo();
                    base_compras.cambiarEstadoEntregado(codigoEntregado);
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

    public ArrayList<compra> BuscarListaDeComprasPorFecha(String fecha) {
        ArrayList<compra> comprasPorFecha = new ArrayList<>();
        for (compra c : base_compras.getCompras().values()) {
            if (c.getFecha().toString().equals(fecha)) {
                comprasPorFecha.add(c);
            }
        }
        return comprasPorFecha;
    }

    public ArrayList<compra> BuscarPorNombre(String nombre) {
        ArrayList<compra> comprasPorNombre = new ArrayList<>();
        for (compra c : base_compras.getCompras().values()) {
            if (c.getNombreCliente().toLowerCase().equals(nombre.toLowerCase())) {
                comprasPorNombre.add(c);
            }
        }
        return comprasPorNombre;
    }
}
