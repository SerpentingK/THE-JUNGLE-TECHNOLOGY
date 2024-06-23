import java.util.ArrayList;

public class principal {
    public static void main(String[] args) {
        ArrayList<dispositivo> list = new ArrayList<>();
        dispositivo disp = new dispositivo("Samsung", "A03", dispositivo.tipo.celularAndroid, 50000, "Puerto de carga dañado");

        list.add(disp);
        
        disp = new dispositivo("Samsung", "A04", dispositivo.tipo.celularAndroid, 60000, "Cambio Visor");
        list.add(disp);

        compra buy = new compra("Gucci", list, 20000);
        buy.String();
    }
}
