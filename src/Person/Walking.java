package Person;

import Field.Field;

public class Walking extends Person{

    public Walking(int health, int attack, int rangeAttack, int defence, double steps, int price, int x, int y, String num){
        super(health, attack, rangeAttack, defence, steps, price, x, y, num);
    }
    @Override
    public double getFine(String symbol){
        switch (symbol){
            case "*", "❀" -> { return 1; }
            case "#" -> { return 1.5; }
            case "@" -> { return 2; }
            case "!" -> { return 1.2; }
        }
        return 0;
    }
}
