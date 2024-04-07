package Person;

import Field.Field;

public class Rider extends Person{
    public Rider(int health, int attack, int rangeAttack, int defence, double steps, int price, int x, int y, String num){
        super(health, attack, rangeAttack, defence, steps, price, x, y, num);
    }
    @Override
    public void mechanismMotion(int x1, int y1){
        int j = getY();
        int countRose = 0;
        double step = 0;
        System.out.println("Now health: " + getHealth());
        while (j < y1){
            j += 1;
            step += getFine(Field.symbCeil(getX(), j));
            if (Field.symbCeil(getX(), j).equals("❀")){
                countRose += 1;
            }
        }
        int i = getX();
        while (i < x1){
            i += 1;
            step += getFine(Field.symbCeil(i, j));
            if (Field.symbCeil(i, j).equals("❀")){
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
    }
    @Override
    public double getFine(String symbol){
        switch (symbol){
            case "*", "❀" -> { return 1; }
            case "#" -> { return 2.2; }
            case "@" -> { return 1.2; }
            case "!" -> { return 1.5; }
        }
        return 0;
    }
}
