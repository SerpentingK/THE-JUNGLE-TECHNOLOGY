package modelos;

import java.util.ArrayList;
import vistas.vistaJT;

public class bd_compras {
    private ArrayList<compra> bd_compras = new ArrayList<>();

    public bd_compras(){
        ArrayList<dispositivo> lista = new ArrayList<>();
        lista.add(new dispositivo("Samsung", "A14", dispositivo.tipo.celularAndroid, 20000, "a"));
        compra x = new compra(new usuario("PRUEBA"), lista, 0, "juan", "3203169321");
        bd_compras.add(x);
        lista = new ArrayList<>();
        lista.add(new dispositivo("Samsung", "A14", dispositivo.tipo.celularAndroid, 20000, "a"));
        lista.add(new dispositivo("Samsung", "A12", dispositivo.tipo.celularAndroid, 50000, "b"));
        x = new compra(new usuario("PRUEBA"), lista, 0, "sofia", "3203169321");
        bd_compras.add(x);
    }

    public void AñadirCompra(compra c){
        bd_compras.add(c);
    }
    
    public compra BuscarPorCodigo(String codigo){
        for(compra c : bd_compras){
            if(c.getCodigoFactura().toUpperCase().equals(codigo)){
                return c;
            }
        }
        return null;
    }

    public ArrayList<compra> getBd_compras() {
        return bd_compras;
    }
    public void CambiarEstadoTerminado (String codigo){
        for(compra c: bd_compras){
            if(c.getCodigoFactura().equals(codigo)){
                vistaJT.ImprimirListaDispositivos(c.getListaDispositivos());
                int r = vistaJT.RecibirInt();
                if(r > c.getListaDispositivos().size() || r < 0){
                    vistaJT.ErrorFueraDeRango();
                }else{
                    
                    c.getListaDispositivos().get(r - 1).CambiarEstadoTerminado();
                }
            }
        }
    }
    public void CambiarEstadoEntregado (String codigo){
        for(compra c: bd_compras){
            if(c.getCodigoFactura().equals(codigo)){
                vistaJT.ImprimirListaDispositivos(c.getListaDispositivos());
                int r = vistaJT.RecibirInt();
                if(r > c.getListaDispositivos().size() || r < 0){
                    vistaJT.ErrorFueraDeRango();
                }else{
                    c.getListaDispositivos().get(r - 1).CambiarEstadoEntregado();
                }
            }
        }
    }
}
