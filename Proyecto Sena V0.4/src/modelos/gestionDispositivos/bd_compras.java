package modelos.gestionDispositivos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import modelos.usuario;
import vistas.vistaDisp;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class bd_compras {
    private Gson gson = new Gson();
    private HashMap<String, compra> compras = new HashMap<>();

    public bd_compras() {
        // Inicializar el HashMap de compras
        compras = new HashMap<>();
        // Cargar los datos desde el archivo BDCompras.json si existe
        cargarDesdeJson();
    }
    private void cargarDesdeJson(){
        String rutaArchivo = "BDCompras.json";
        try{
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));

            Gson gson = new Gson();
            int numRef = -1;
            char letraRef = 'A';
            Type type  = new TypeToken<Map<String, Map<String, Object>>>(){}.getType();
            Map<String, Map<String, Object>> comprasMap = gson.fromJson(contenido, type);
            for(Map.Entry<String, Map<String, Object>> entry : comprasMap.entrySet()){
                String codigoFactura = entry.getKey();
                String[] partes = codigoFactura.split("-");
                int num = Integer.parseInt(partes[0]);
                char letra = (Character) partes[1].charAt(0);
                if (num > numRef){
                    compra.contadorFactura = num + 1;
                    numRef = num;
                }
                if(letra > letraRef){
                    compra.letraReferencia = letra;
                    letraRef = letra;
                }
                Map<String, Object> atributos = (Map<String, Object>) entry.getValue().get("atributos");

                String fecha = (String) atributos.get("fecha");
                float deuda = ((Double) atributos.get("deuda")).floatValue();
                String nombreCliente = (String) atributos.get("nombreCliente");
                float totalPago = ((Double) atributos.get("totalPago")).floatValue();
                float abono = ((Double) atributos.get("abono")).floatValue();
                String numeroTelefono = (String) atributos.get("numeroTelefono");


                Map<String, Object> tecnicoAsignadoMap = (Map<String, Object>) atributos.get("tecnicoAsignado");
                Map<String, Object> atributosTecnico = (Map<String, Object>) tecnicoAsignadoMap.get("atributos");
                String claveTecnico =(String) atributosTecnico.get("clave");
                String documentoTecnico = (String) atributosTecnico.get("documento");
                String nombreTecnico = (String) atributosTecnico.get("nombre");

                ArrayList<Map<String, Object>> listaDispositivos = (ArrayList<Map<String, Object>>) atributos.get("listaDispositivos");

                ArrayList<dispositivo> lista = new ArrayList<>();

                for (Map<String, Object> i: listaDispositivos){
                    Map<String, Object> dispositivoMap = (Map<String, Object>)  i.get("atributos");
                    String descripcion = (String) dispositivoMap.get("descripcion");
                    String marca = (String) dispositivoMap.get("marca");
                    dispositivo.tipo tipo = dispositivo.tipo.valueOf((String) dispositivoMap.get("tipoDispositivo")) ;
                    boolean terminado = (boolean) dispositivoMap.get("terminado");
                    float precio = ((Double) dispositivoMap.get("precio")).floatValue();
                    boolean entregado = (boolean) dispositivoMap.get("entregado");
                    String modelo = (String) dispositivoMap.get("modelo");

                    lista.add(new dispositivo(marca, modelo, tipo, precio, descripcion, terminado, entregado));
                }

                añadirCompra(new compra(new usuario(nombreTecnico, claveTecnico, documentoTecnico), lista, abono, nombreCliente, numeroTelefono, fecha, totalPago, deuda, codigoFactura));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void actualizarJson() {
        String json = gson.toJson(compras);
        Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fileWriter = new FileWriter("BDCompras.json")) {
            gsonPretty.toJson(gsonPretty.fromJson(json, Object.class), fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void añadirCompra(compra c) {
        compras.put(c.getCodigoFactura(), c);
        System.out.println(compras);
        actualizarJson();
    }

    public compra buscarPorCodigo(String codigo) {
        return compras.get(codigo);
    }

    public HashMap<String, compra> getCompras() {
        return compras;
    }

    public void cambiarEstadoTerminado(String codigo) {
        compra c = buscarPorCodigo(codigo);
        if (c != null) {
            int r = vistaDisp.RecibirInt(vistaDisp.ImprimirListaDispositivos(c.getListaDispositivos()));
            if (r > c.getListaDispositivos().size() || r < 0) {
                vistaDisp.ErrorFueraDeRango();
            } else {
                c.getListaDispositivos().get(r - 1).cambiarEstadoTerminado();
            }
            actualizarJson();
        }
    }

    public void cambiarEstadoEntregado(String codigo) {
        compra c = buscarPorCodigo(codigo);
        if (c != null) {
            int r = vistaDisp.RecibirInt(vistaDisp.ImprimirListaDispositivos(c.getListaDispositivos()));
            if (r > c.getListaDispositivos().size() || r < 0) {
                vistaDisp.ErrorFueraDeRango();
            } else {
                c.getListaDispositivos().get(r - 1).cambiarEstadoEntregado();
            }
            actualizarJson();
        }
    }

}
