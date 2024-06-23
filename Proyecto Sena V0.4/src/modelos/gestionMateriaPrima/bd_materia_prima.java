package modelos.gestionMateriaPrima;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class bd_materia_prima {
    private Gson gson = new Gson();
    private HashMap<String, m_prima> inventario;

    public bd_materia_prima(){
        inventario = new HashMap<>();
        actualizarJSON();
    }

    public HashMap<String, m_prima> getInventario() {
        return inventario;
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
