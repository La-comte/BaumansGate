package Field;
import Ceil.Ceil;
import java.util.ArrayList;
import java.util.Random;

public class Field {
    private static Field instance;

    private Field(){}

    public static Field getInstance(){
        if (instance == null)
            instance = new Field();
        return instance;
    }
    static ArrayList<ArrayList<Ceil>> allCeil = new ArrayList<>();
    public void nullCeil(){
        for (int i = 0; i < 15; i++) {
            ArrayList<Ceil> list3 = new ArrayList<>();
            for (int j = 0; j < 15; j++)
                list3.add(null);
            allCeil.add(list3);
        }
        String s = "";
        int h = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Random r = new Random();
                int t = r.nextInt(100) + 1;
                if (t > 40){
                    s = "*";
                } else if (t > 20) {
                    s = "#";
                } else if (t > 7) {
                    s = "❀";
                } else if (t > 5) {
                    s = "!";
                } else { s = "@"; }
                allCeil.get(i).set(j, new Ceil(s));
            }
        }
    }
    public static String symbCeil(int x, int y) {
        return allCeil.get(x).get(y).getSymbol();
    }
}
