package modelos.gestionMateriaPrima;

import java.util.ArrayList;
import java.util.HashMap;

public class m_prima {
    private HashMap<String, Object> atributos;

    public m_prima(String tipo){
        atributos = new HashMap<>();
        atributos.put("tipo", tipo);
        atributos.put("lista", new ArrayList<repuesto>());
    }

    public String getTipo(){
        return (String) atributos.get("tipo");
    }

    public ArrayList<repuesto> getLista(){
        return (ArrayList<repuesto>) atributos.get("lista");
    }

    public void añadirRepuesto(repuesto r){
        ArrayList<repuesto> lista = new ArrayList<>();
        lista = (ArrayList<repuesto>) atributos.get("lista");
        lista.add(r);
        atributos.put("lista", lista);
    }


}
