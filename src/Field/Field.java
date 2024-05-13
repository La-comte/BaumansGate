package Field;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class Field {
    private static Field instance;

    private Field(){}

    public static Field getInstance(){
        if (instance == null)
            instance = new Field();
        return instance;
    }
    static ArrayList<ArrayList<String>> allCell = new ArrayList<>();
    public void nullCell(){
        for (int i = 0; i < 10; i++) {
            ArrayList<String> list3 = new ArrayList<>();
            for (int j = 0; j < 10; j++)
                list3.add(null);
            allCell.add(list3);
        }
        String s = "";
        int h = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
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
                allCell.get(i).set(j, s);
            }
        }
    }
    public static String symbCell(int x, int y) {
        return allCell.get(x).get(y);
    }
    public void save() throws Exception {
        FileWriter nFile = new FileWriter("field.txt");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                nFile.write(allCell.get(i).get(j));
            nFile.write("\n");
        }
        nFile.close();
    }
}
