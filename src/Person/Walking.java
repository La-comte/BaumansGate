package Person;

import Field.Field;

public class Walking extends Person{

    public Walking(int health, int attack, int rangeAttack, int defence, double steps, int price, int x, int y, String num){
        super(health, attack, rangeAttack, defence, steps, price, x, y, num);
        nullFine();
    }
    @Override
    public void nullFine(){
        getFine().put("*", (double) 1);
        getFine().put("!", 1.2);
        getFine().put("#", 1.5);
        getFine().put("@", (double) 2);
        getFine().put("❀", (double) 1);
    }
}
