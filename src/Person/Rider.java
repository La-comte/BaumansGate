package Person;

import Building.Building;
import Field.Field;

import java.util.HashMap;

public class Rider extends Person{
    public Rider(int health, int attack, int rangeAttack, int defence, double steps, int price, int x, int y, String num){
        super(health, attack, rangeAttack, defence, steps, price, x, y, num);
        nullFine();
    }
    /*@Override
    public void mechanismMotion(int x1, int y1){
        int j = getY();
        int countRose = 0;
        double step = 0;
        System.out.println("Now health: " + getHealth());
        while (j < y1){
            j += 1;
            step += getFine(Field.symbCell(getX(), j));
            if (Field.symbCell(getX(), j).equals("❀")){
                countRose += 1;
            }
        }
        int i = getX();
        while (i < x1){
            i += 1;
            step += getFine(Field.symbCell(i, j));
            if (Field.symbCell(i, j).equals("❀")){
                countRose += 1;
            }
        }
        if (step <= getSteps()){
            setSteps(getSteps() - step);
            setHealth(getHealth() + 5*countRose);
            setY(j);
            setX(i);
        } else { System.out.println("LOL no steps"); }
        System.out.println("Health: " + getHealth());
        System.out.println("Steps: " + getSteps());
    }*/
    @Override
    public void nullFine(){
        getFine().put("*", (double) 1);
        getFine().put("!", 1.5);
        getFine().put("#", 2.2);
        getFine().put("@", 1.2);
        getFine().put("❀", (double) 1);
    }

}
