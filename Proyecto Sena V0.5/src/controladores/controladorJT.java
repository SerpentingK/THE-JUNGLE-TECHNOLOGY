package controladores;

import modelos.gestionDispositivos.*;
import modelos.gestionMateriaPrima.*;
import modelos.gestionUsuarios.*;
import vistas.*;

import java.util.ArrayList;
import java.util.HashMap;

// Definición de la clase controladorJT
public class controladorJT {
    private final bd_compras base_compras = new bd_compras();
    private final bd_materia_prima base_materia_prima = new bd_materia_prima();
    private final bd_usuarios base_usuarios = new bd_usuarios();
    private usuario usuarioActual;
    public void ejecutar() {
        while(true){
            inicioSesion();
            if(usuarioActual != null){
                boolean inProgram = true;
                while (inProgram){
                    int r = vistaDisp.MenuPrincipal();
                    switch (r){
                        case 1:
                            gestionDispositivos();
                            break;
                        case 2:
                            gestionMateriaPrima();
                            break;
                        case 3:
                            gestionUsuarios();
                            break;
                        case 4:
                            inProgram = false;
                            usuarioActual = null;
                            break;
                        default:
                            vistaDisp.ErrorFueraDeRango();
                    }
                }
            }else{
                vistaUs.mostrarMensaje("Inicio de sesion fallido.");
            }
        }


    }
    public void inicioSesion() {
        String documento = vistaUs.recibirDocTecnico();
        usuarioActual = base_usuarios.getUsuario(documento);
        if (usuarioActual != null) {
            String claveIngresada = vistaUs.recibirClave();
            if (usuarioActual.getClave().equals(claveIngresada)) {
                vistaUs.mostrarMensaje("Inicio de sesión exitoso.");
            } else {
                vistaUs.mostrarMensaje("Clave incorrecta. Inicio de sesión fallido.");
                usuarioActual = null;
            }
        } else {
            vistaUs.mostrarMensaje("Documento no encontrado. Inicio de sesión fallido.");
        }
    }

    // Método principal para ejecutar el programa
    public void gestionDispositivos() {
        boolean inProgram = true;
        while (inProgram) {
            // Mostrar el menú y obtener la opción seleccionada por el usuario
            int r = vistaDisp.MenuProductos();
            // Realizar acciones dependiendo de la opción seleccionada
            switch (r) {
                case 1:
                    // Solicitar y crear una nueva compra
                    ArrayList<dispositivo> dispositivos = vistaDisp.RecibirDispositivos();
                    float abono = vistaDisp.RecibirAbono();
                    String[] datos = vistaDisp.RecibirDatosCliente();
                    compra nuevaCompra = new compra(usuarioActual, dispositivos, abono, datos[0], datos[1]);
                    base_compras.añadirCompra(nuevaCompra);
                    break;
                case 2:
                    // Mostrar la lista de compras
                    if (base_compras.getCompras().isEmpty()) {
                        vistaDisp.ErrorListaVacia();
                    } else {
                        for (compra c : base_compras.getCompras().values()) {
                            vistaDisp.ImprimirDatosCompra(c);
                        }
                    }
                    break;
                case 3:
                    // Buscar y mostrar compra por código
                    String codigo = vistaDisp.RecibirCodigo().toUpperCase();
                    compra compraPorCodigo = base_compras.buscarPorCodigo(codigo);
                    if (compraPorCodigo != null) {
                        vistaDisp.ImprimirDatosCompra(compraPorCodigo);
                    } else {
                        vistaDisp.ErrorListaVacia();
                    }
                    break;
                case 4:
                    // Buscar y mostrar compras por fecha
                    String fecha = vistaDisp.RecibirFecha();
                    ArrayList<compra> comprasPorFecha = BuscarListaDeComprasPorFecha(fecha);
                    if (comprasPorFecha.isEmpty()) {
                        vistaDisp.ErrorListaVacia();
                    } else {
                        for (compra c : comprasPorFecha) {
                            vistaDisp.ImprimirDatosCompra(c);
                        }
                    }
                    break;
                case 5:
                    // Buscar y mostrar compra por nombre
                    String nombre = vistaDisp.RecibirNombre();
                    ArrayList<compra> comprasPorNombre = BuscarPorNombre(nombre);
                    if (comprasPorNombre.isEmpty()) {
                        vistaDisp.ErrorListaVacia();
                    } else {
                        for (compra c : comprasPorNombre) {
                            vistaDisp.ImprimirDatosCompra(c);
                        }
                    }
                    break;
                case 6:
                    // Cambiar estado a terminado
                    String codigoTerminado = vistaDisp.RecibirCodigo();
                    base_compras.cambiarEstadoTerminado(codigoTerminado);
                    break;
                case 7:
                    // Cambiar estado a entregado
                    String codigoEntregado = vistaDisp.RecibirCodigo();
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

    public void gestionMateriaPrima(){
        boolean inProgram = true;
        while (inProgram) {
            int r = vistaMP.Menu();
            switch (r) {
                case 1:
                    String nuevaCategoria = vistaMP.RecibirString("Ingrese nombre nueva categoria: ");
                    base_materia_prima.añadirCategoria(nuevaCategoria);
                    break;
                case 2:
                    ArrayList<String> listaCategorias = new ArrayList<>();
                    listaCategorias.addAll(base_materia_prima.getInventario().keySet());
                    String categoriaElegida = vistaMP.RecibirCategoria(listaCategorias);
                    System.out.println(categoriaElegida);

                    repuesto nuevoRespuesto = vistaMP.RecibirRepuesto();
                    base_materia_prima.añadirRepuesto(categoriaElegida,nuevoRespuesto);


                    break;
                case 3:
                    if (base_materia_prima.getInventario().isEmpty()) {
                        vistaDisp.ErrorListaVacia();
                    } else {
                        for (m_prima mp : base_materia_prima.getInventario().values()) {
                            vistaMP.ImprimirMateriaPrima(mp);
                        }
                    }
                    break;
                case 4:
                    ArrayList<String> listaCategoriasEliminar = new ArrayList<>();
                    listaCategoriasEliminar.addAll(base_materia_prima.getInventario().keySet());
                    String categoriaEliminar = vistaMP.RecibirCategoria(listaCategoriasEliminar);
                    base_materia_prima.getInventario().remove(categoriaEliminar);
                    base_materia_prima.actualizarJSON();
                    break;
                case 5:
                    ArrayList<String> listaCategoriasActualizar = new ArrayList<>();
                    listaCategoriasActualizar.addAll(base_materia_prima.getInventario().keySet());
                    String categoria = vistaMP.RecibirCategoria(listaCategoriasActualizar);

                    ArrayList<String> listaDispositivos = new ArrayList<>();
                    for (repuesto rep : base_materia_prima.getInventario().get(categoria).getLista()){
                        listaDispositivos.add(rep.getTipo());
                    }
                    String nombreRepuesto = vistaMP.RecibirCategoria(listaDispositivos);
                    int nuevasUnidades = vistaMP.RecibirInt("Ingrese unidades a restar o sumar.\nEjemplo: +1/-1");
                    boolean encontrado = false;
                    for (repuesto rep : base_materia_prima.getInventario().get(categoria).getLista()){
                        if(rep.getTipo().equalsIgnoreCase(nombreRepuesto)){
                            rep.actualizarUnidades(nuevasUnidades);
                            vistaMP.ImprimirMateriaPrima(base_materia_prima.getInventario().get(categoria));
                            encontrado = true;
                        }
                    }
                    if(!encontrado){
                        vistaMP.ErrorNombre();
                    }

                    break;
                case 6:
                    inProgram = false;
                    break;
                default:
                    vistaDisp.ErrorFueraDeRango();
                    break;
            }
        }
    }

    public void gestionUsuarios(){
        boolean inProgram = true;
        while(inProgram){
            int r = vistaUs.Menu();
            switch (r){
                case 1:
                    añadirTecnico();
                    break;
                case 2:
                    mostrarTecnicos();
                    break;
                case 3:
                    consultarTecnicoPorDoc();
                    break;
                case 4:
                    eliminarTecnico();
                    break;
                case 5:
                    actualizarTecnico();
                    break;
                case 6:
                    vistaUs.mostrarMensaje("Saliendo del programa...");
                    inProgram = false;
                    break;
            }
        }
    }
    public void añadirTecnico() {
        String[] datos = vistaUs.recibirDatosUsuario();
        usuario nuevoTecnico = new usuario(datos[0], datos[1], datos[2]);
        base_usuarios.añadirTecnico(nuevoTecnico);
        vistaUs.mostrarMensaje("Técnico añadido correctamente.");
    }
    private void mostrarTecnicos() {
        HashMap<String, usuario> usuarios = base_usuarios.getUsuarios();
        if (usuarios.isEmpty()) {
            vistaUs.mostrarMensaje("No hay técnicos registrados.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (usuario t : usuarios.values()) {
                sb.append("Nombre: ").append(t.getNombre()).append("\n").append("Documento: ").append(t.getDocumento()).append("\n").append("Clave: ").append(t.getClave()).append("\n");
            }
            vistaUs.mostrarTecnico(sb.toString());
        }
    }
    private void consultarTecnicoPorDoc() {
        String doc = vistaUs.recibirDocTecnico();
        usuario u = base_usuarios.getUsuario(doc);
        if (u == null) {
            vistaUs.mostrarMensaje("Técnico no encontrado.");
        } else {
            vistaUs.mostrarTecnico("Nombre: " + u.getNombre() +
                    "\nDocumento: " + u.getDocumento() +
                    "\nClave: " + u.getClave());
        }
    }
    private void eliminarTecnico() {
        String doc = vistaUs.recibirDocTecnico();
        base_usuarios.eliminarUsuario(doc);
        vistaUs.mostrarMensaje("Técnico eliminado correctamente.");
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

    private void actualizarTecnico(){
        String doc = vistaUs.recibirDocTecnico();
        usuario usuarioAct = base_usuarios.getUsuario(doc);
        if (usuarioAct != null) {
            String[] datos = vistaUs.recibirDatosUsuario();
            usuarioAct.setNombre(datos[0]);
            usuarioAct.setClave(datos[2]);
            base_usuarios.actualizarUsuario(doc, usuarioAct);
            vistaUs.mostrarMensaje("Técnico actualizado correctamente.");
        } else {
            vistaUs.mostrarMensaje("Técnico no encontrado.");
        }
    }
}
