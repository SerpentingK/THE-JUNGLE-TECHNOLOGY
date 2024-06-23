package modelos.gestionUsuarios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class bd_usuarios {
    private HashMap<String, usuario> usuarios;
    private static final String ruta = "BDUsuarios.json";
    private Gson gson;

    public bd_usuarios(){
        usuarios = new HashMap<>();
        gson = new Gson();
        cargarDesdeJson();
        if(usuarios.isEmpty()){
            System.out.println("bd vacia");
        }
    }

    public void cargarDesdeJson(){
        try(FileReader reader = new FileReader(ruta)) {
            Type tipo = new TypeToken<HashMap<String, usuario>>(){}.getType();
            usuarios = gson.fromJson(reader, tipo);
            if(usuarios == null){
                usuarios = new HashMap<>();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void actualizarJson() {
        // Crear un JSON con la estructura deseada
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, usuario> entry : usuarios.entrySet()) {
            String key = entry.getKey();
            usuario value = entry.getValue();

            JsonObject usuarioObject = new JsonObject();
            JsonObject atributosObject = new JsonObject();
            atributosObject.addProperty("nombre", value.getNombre());
            atributosObject.addProperty("clave", value.getClave());
            atributosObject.addProperty("documento", value.getDocumento());
            usuarioObject.add("atributos", atributosObject);

            jsonObject.add(key, usuarioObject);
        }

        // Escribir el JSON en el archivo
        try (FileWriter writer = new FileWriter(ruta)) {
            Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
            gsonPretty.toJson(jsonObject, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void añadirTecnico(usuario u) {
        usuarios.put(u.getDocumento(), u);
        actualizarJson();
    }
    public void actualizarUsuario(String documento, usuario u) {
        if (usuarios.containsKey(documento)) {
            usuarios.put(documento, u);
            actualizarJson();
        }
    }
    public void eliminarUsuario(String doc) {
        usuarios.remove(doc);
        actualizarJson();
    }

    public usuario getUsuario(String doc){
        return usuarios.get(doc);
    }

    public HashMap<String, usuario> getUsuarios(){
        return usuarios;
    }



}
