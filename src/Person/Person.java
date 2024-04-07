package Person;
import Field.Field;

public abstract class Person {
    private int health, attack, rangeAttack, defence, price, x, y;
    private double steps, startsteps;
    private String num;
    public Person(int health, int attack, int rangeAttack, int defence, double steps, int price, int x, int y, String num){
        this.health = health;
        this.attack = attack;
        this.rangeAttack = rangeAttack;
        this.defence = defence;
        this.steps = steps;
        startsteps = steps;
        this.price = price;
        this.x = x;
        this.y = y;
        this.num = num;
    }
    public double getStartsteps(){
        return startsteps;
    }
    public double getFine(String symbol){
        return 0;
    }
    public int getHealth(){
        return health;
    }
    public int getAttack(){ return attack; }
    public int getRangeAttack(){
        return rangeAttack;
    }
    public int getDefence(){
        return defence;
    }
    public double getSteps(){
        return steps;
    }
    public int getPrice(){
        return price;
    }
    public int getX(){ return x; }
    public int getY(){ return y; }
    public String getNum() { return num; }
    public void setHealth(int health){
        this.health = health;
    }
    public void setDefence(int defence){
        this.defence = defence;
    }
    public void setSteps(double steps){
        this.steps = steps;
    }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void mechanismMotion(int x1, int y1){
        int j = y;
        int countRose = 0;
        double step = 0;
        System.out.println("Now health: " + getHealth());
        while (j < y1){
            j += 1;
            step += getFine(Field.symbCeil(x, j));
            if (Field.symbCeil(x, j).equals("❀")){
                countRose += 1;
            }
        }
        int i = x;
        while (i < x1){
            i += 1;
            step += getFine(Field.symbCeil(i, j));
            if (Field.symbCeil(i, j).equals("❀")){
                countRose += 1;
            }
        }
        if (step <= getSteps()){
            setSteps(getSteps() - step);
            setHealth(getHealth() - 5*countRose);
            y = j;
            x = i;
        } else { System.out.println("LOL no steps"); }
        System.out.println("Health: " + getHealth());
        System.out.println("Steps: " + getSteps());
    }
    public void mechanismAttack(Person attacker){
        System.out.println("Now health defender: " + health);
        System.out.println("Now defence defender: " + defence);
        int difference = Math.abs((attacker.getX() + attacker.getY()) - (x + y));
        if (difference <= attacker.getRangeAttack()){
            if (attacker.getAttack() > defence){
                int damage = attacker.getAttack() - defence;
                defence = 0;
                if (damage < health){
                    health = health - damage;
                } else {
                    num = "D";
                    System.out.println("You kill enemy");
                    return;
                }
            } else {
                defence = defence - attacker.getAttack();
            }
        } else { System.out.print("You can't attack:\n"); }
        System.out.println("Health defender: " + health);
        System.out.println("Defence defender: " + defence);
    }
}
