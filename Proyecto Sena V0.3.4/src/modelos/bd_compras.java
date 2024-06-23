package modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vistas.vistaJT;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class bd_compras {
    private Gson gson = new Gson();
    private HashMap<String, compra> compras = new HashMap<>();
    private static int contador = 0;

    public bd_compras() {
        ArrayList<dispositivo> lista = new ArrayList<>();
        lista.add(new dispositivo("Samsung", "A14", dispositivo.tipo.celularAndroid, 20000, "a"));
        compra x = new compra(new usuario("PRUEBA", "clave", "documento"), lista, 0, "juan", "3203169321");
        compras.put(x.getCodigoFactura(), x);
        lista = new ArrayList<>();
        lista.add(new dispositivo("Samsung", "A14", dispositivo.tipo.celularAndroid, 20000, "a"));
        lista.add(new dispositivo("Samsung", "A12", dispositivo.tipo.celularAndroid, 50000, "b"));
        x = new compra(new usuario("PRUEBA", "clave", "documento"), lista, 0, "sofia", "3203169321");
        compras.put(x.getCodigoFactura(), x);
        actualizarJson();
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
            int r = vistaJT.RecibirInt(vistaJT.ImprimirListaDispositivos(c.getListaDispositivos()));
            if (r > c.getListaDispositivos().size() || r < 0) {
                vistaJT.ErrorFueraDeRango();
            } else {
                c.getListaDispositivos().get(r - 1).cambiarEstadoTerminado();
            }
            actualizarJson();
        }
    }

    public void cambiarEstadoEntregado(String codigo) {
        compra c = buscarPorCodigo(codigo);
        if (c != null) {
            int r = vistaJT.RecibirInt(vistaJT.ImprimirListaDispositivos(c.getListaDispositivos()));
            if (r > c.getListaDispositivos().size() || r < 0) {
                vistaJT.ErrorFueraDeRango();
            } else {
                c.getListaDispositivos().get(r - 1).cambiarEstadoEntregado();
            }
            actualizarJson();
        }
    }

}
