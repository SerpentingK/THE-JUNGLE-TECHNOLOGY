package modelos.gestionMateriaPrima;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class bd_materia_prima {
    private Gson gson = new Gson();
    private HashMap<String, m_prima> inventario;

    public bd_materia_prima(){
        inventario = new HashMap<>();
        cargarDesdeJson();
    }

    public HashMap<String, m_prima> getInventario() {
        return inventario;
    }

    public void cargarDesdeJson(){
        String rutaArchivo = "BDMateriaPrima.json";
        try{
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));

            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Map<String, Object>>>(){}.getType();
            Map<String, Map<String, Object>> mpMap = gson.fromJson(contenido, type);
            for(Map.Entry<String, Map<String, Object>> entry : mpMap.entrySet()){
                String categoria = entry.getKey();
                Map<String, Object> atributos = (Map<String, Object>) entry.getValue().get("atributos");

                String tipo = (String) atributos.get("tipo");
                ArrayList<Map<String, Object>> listaRepuestos = (ArrayList<Map<String, Object>>) atributos.get("lista");

                ArrayList<repuesto> lista = new ArrayList<>();

                for(Map<String, Object> i: listaRepuestos){
                    Map<String, Object> repuestoMap = (Map<String, Object>) i.get("atributos");
                    String dispositivo = (String) repuestoMap.get("dispositivo");
                    float unidades = ((Double) repuestoMap.get("unidades")).floatValue();

                    lista.add(new repuesto(dispositivo,(int) unidades));
                }

                añadirCategoria(tipo);
                for (repuesto r: lista){
                    añadirRepuesto(categoria, r);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Metodo Actualizar JSON

    public void actualizarJSON(){
        String json = gson.toJson(inventario);
        Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fileWriter = new FileWriter("BDMateriaPrima.json")) {
            gsonPretty.toJson(gsonPretty.fromJson(json, Object.class), fileWriter);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    // método AñadirCategoria

    public void añadirCategoria(String tipo) {
        if (!inventario.containsKey(tipo)) {
            inventario.put(tipo, new m_prima(tipo));
            actualizarJSON();
        }
    }

    // método AñadirRepuesto

    public void añadirRepuesto(String tipo, repuesto r) {
        if (inventario.containsKey(tipo)) {
            m_prima mp = inventario.get(tipo);
            mp.añadirRepuesto(r);
            actualizarJSON();
        }
    }


}
