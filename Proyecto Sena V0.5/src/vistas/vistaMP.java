package vistas;

import modelos.gestionMateriaPrima.*;

import javax.swing.*;
import java.util.ArrayList;

public class vistaMP {


    public static int Menu() {
        return Integer.parseInt(JOptionPane.showInputDialog(null,
                "1. Nueva Categoría\n2. Añadir Repuesto\n3. Consultar Inventario\n4. Borrar Categoría\n5. Actualizar repuesto\n6. Salir", "Menú", JOptionPane.QUESTION_MESSAGE));
    }
    public static void ErrorNombre(){
        JOptionPane.showMessageDialog(null, "Nombre no encontrado.");
    }
    public static String RecibirString(String mensaje) {
        return JOptionPane.showInputDialog(null, mensaje, "RECIBIR STRING", JOptionPane.QUESTION_MESSAGE);
    }
    public static int RecibirInt(String mensaje){
        return Integer.parseInt(JOptionPane.showInputDialog(null, mensaje, "RECIBIR NUMERO", JOptionPane.QUESTION_MESSAGE));
    }

    public static repuesto RecibirRepuesto(){
        String nombre = RecibirString("Ingrese dispositivo del repuesto: ");
        int unidades = RecibirInt("Ingrese unidades: ");
        return new repuesto(nombre, unidades);
    }
    public static String RecibirCategoria(ArrayList<String> lista){
        Object[] categorias = lista.toArray();

        String categoria = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona Categoria: ",
                "Categorias",
                JOptionPane.QUESTION_MESSAGE,
                null,
                categorias,
                categorias[0]
        );
        return categoria;
    }

    public static void ImprimirMateriaPrima(m_prima m){
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Lista de Inventario\n");
        mensaje.append(m.getTipo()).append(":\n");
        for (repuesto r : m.getLista()) {
            mensaje.append(" - ").append(r.getTipo()).append(": ").append(r.getUnidades()).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Detalles Inventario", JOptionPane.INFORMATION_MESSAGE);
    }
}
