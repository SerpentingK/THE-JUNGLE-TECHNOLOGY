import modelos.*;
import controladores.*;
import java.util.ArrayList;
public class Main{
    public static void main(String[] args) {

        /*
        ArrayList<dispositivo> list = new ArrayList<>();
        list.add(new dispositivo("Samnsung", "A03", dispositivo.tipo.celularAndroid,50000, "Arreglo entrada de carga"));
        list.add(new dispositivo("Samnsung", "A03", dispositivo.tipo.celularAndroid,60000, "Arreglo entrada de carga"));
        compra c1 = new compra(new usuario("Gucci"), list, 20000, "Gucci", "3113107485");
        System.out.println(c1);
        
        compra c2 = new compra(new usuario("Mateo"), list, 20000, "Gucci", "3113107482");
        System.out.println(c2);

        compra c3 = new compra(new usuario("Carrillo"), list, 20000, "Gucci", "3113107434");
        System.out.println(c3);
         */
        controladorJT c = new controladorJT();
        c.Ejecutar();

    }
}