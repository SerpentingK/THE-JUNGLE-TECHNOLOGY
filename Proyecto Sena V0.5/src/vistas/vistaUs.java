package vistas;

import javax.swing.*;

public class vistaUs {

    public static int Menu(){
        return Integer.parseInt(JOptionPane.showInputDialog(null,
                "1. Añadir Técnico\n2. Consultar lista de técnicos\n3. Consultar Técnico por ID\n4. Eliminar Técnico\n5. Actualizar Técnico\n6. Salir",
                "Menú", JOptionPane.QUESTION_MESSAGE));
    }

    public static String[] recibirDatosUsuario() {
        String[] datos = new String[3];
        datos[0] = JOptionPane.showInputDialog("Ingresa nombre del técnico:");
        datos[1] = JOptionPane.showInputDialog("Ingrese clave del técnico:");
        datos[2] = JOptionPane.showInputDialog("Ingrese documento del técnico:");
        return datos;
    }
    public static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public static void mostrarTecnico(String tecnicoInfo) {
        JOptionPane.showMessageDialog(null, tecnicoInfo, "Información del Técnico", JOptionPane.INFORMATION_MESSAGE);
    }
    public static String recibirDocTecnico() {
        return JOptionPane.showInputDialog("Ingrese documento del técnico:");
    }
    public static String recibirClave() {
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(null, passwordField, "Ingrese su clave:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            return new String(passwordField.getPassword());
        } else {
            return "";
        }
    }
}
