package GameField;

import Field.Field;
import Person.Person;
import Person.Archer;
import Person.Rider;
import Person.Walking;

import java.util.ArrayList;
import java.util.Random;

import java.util.Scanner;

public class GameField {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private int meWallet = 999;
    public void setMeWallet(int meWallet) {
        this.meWallet = meWallet;
    }
    private int enemyWallet = 999;
    public void setEnemyWallet(int enemyWallet){
        this.enemyWallet = enemyWallet;
    }
    ArrayList<Person> mePersons = new ArrayList<>();
    ArrayList<Person> enemyPersons = new ArrayList<>();
    public boolean notNull(){
        if (mePersons.isEmpty() || enemyPersons.isEmpty())
            return false;
        return true;
    }
    public void enterField(){
        boolean hasPersonAtCeil = true;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                for (int w = 0; w < mePersons.size(); w++) {
                    if (mePersons.get(w).getX() == i && mePersons.get(w).getY() == j) {
                        System.out.print(GREEN_BOLD + mePersons.get(w).getNum() + ANSI_RESET + "\t");
                        hasPersonAtCeil = false;
                        break;
                    }
                }
                for (int w = 0; w < enemyPersons.size(); w++) {
                    if (enemyPersons.get(w).getX() == i && enemyPersons.get(w).getY() == j) {
                        System.out.print(PURPLE_BOLD_BRIGHT + enemyPersons.get(w).getNum() + ANSI_RESET + "\t");
                        hasPersonAtCeil = false;
                        break;
                    }
                }
                if (hasPersonAtCeil){ System.out.print(Field.symbCeil(i, j) + "\t"); }
                hasPersonAtCeil = true;
            }
            System.out.println();
        }
    }
    public Person meEnterPerson(){
        System.out.print("Input coordinates:\n");
        Scanner in = new Scanner(System.in);
        int x = Integer.parseInt(in.nextLine());
        int y = Integer.parseInt(in.nextLine());
        System.out.print("Input Type: ");
        int t = Integer.parseInt(in.nextLine());
        x -= 1;
        y -= 1;
        return enterPerson(x, y, t);
    }
    public Person enemyEnterPerson(){
        System.out.println("Input coordinates:");
        Random r = new Random();
        int x = r.nextInt(15);
        int y = r.nextInt(15);
        System.out.print((x+1) + "\n" + (y+1) + "\n");
        System.out.println("Input Type: ");
        //int t = r.nextInt(10) + 1;
        int t = 10;
        System.out.println(t);
        return enterPerson(x, y, t);
    }
    private Person enterPerson(int x, int y, int t){
        switch (t) {
            case 1 -> {
                return new Walking(50, 5, 1, 8, 3, 10, x, y, "1");
            }
            case 2 -> {
                return new Walking(35, 3, 1, 4, 6, 15, x, y, "2");
            }
            case 3 -> {
                return new Walking(45, 9, 1, 3, 4, 20, x, y, "3");
            }
            case 4 -> {
                return new Archer(30, 6, 5, 8, 2, 15, x, y, "4");
            }
            case 5 -> {
                return new Archer(25, 3, 3, 4, 4, 19, x, y, "5");
            }
            case 6 -> {
                return new Archer(40, 7, 6, 3, 2, 23, x, y, "6");
            }
            case 7 -> {
                return new Rider(30, 5, 1, 3, 6, 20, x, y, "7");
            }
            case 8 -> {
                return new Rider(50, 2, 1, 7, 5, 23, x, y, "8");
            }
            case 9 -> {
                return new Rider(25, 3, 3, 2, 5, 25, x, y, "9");
            }
            case 10 -> {
                return new Archer(999, 999, 999, 999, 999, 99, x, y,"❤");
            }
        }
        return null;
    }
    public void moneyLimit(Person pers){
        if (pers.getPrice() <= meWallet){
            setMeWallet(meWallet - pers.getPrice());
            System.out.println("Wallet = " + meWallet);
            mePersons.add(pers);
            enterField();
        } else { System.out.println("LOL You don't have any money.");}
    }
    public void moneyLimitEnemy(Person pers){
        if (pers.getPrice() <= enemyWallet){
            setEnemyWallet(enemyWallet - pers.getPrice());
            System.out.println("Wallet enemy = " + enemyWallet);
            enemyPersons.add(pers);
            enterField();
        } else { System.out.println("LOL You don't have any money.");}
    }
    public void motionEnemy(){
        System.out.print("Input number of the player:\n");
        int count = 0;
        for (count = 0; count < enemyPersons.size(); count++) {
            System.out.println((count+1) + ".  <<" + enemyPersons.get(count).getNum() + ">>");
        }
        Random r = new Random();
        int countPerson = r.nextInt(count);
        System.out.println("Answer: " + (countPerson + 1));
        System.out.println("Input coordinates where you want to go:");
        int x = r.nextInt(15);
        int y = r.nextInt(15);
        System.out.print((x+1) + "\n" + (y+1) + "\n");
        enemyPersons.get(countPerson).mechanismMotion(x, y);
        enterField();
    }
    public void motion(){
        System.out.print("Input number of the player:\n");
        for (int count = 0; count < mePersons.size(); count++) {
            System.out.println((count+1) + ".  <<" + mePersons.get(count).getNum() + ">>");
        }
        Scanner in = new Scanner(System.in);
        int count = Integer.parseInt(in.nextLine()) - 1;
        System.out.print("Input coordinates where you want to go:\n");
        int x = Integer.parseInt(in.nextLine()) - 1;
        int y = Integer.parseInt(in.nextLine()) - 1;
        mePersons.get(count).mechanismMotion(x, y);
        enterField();
    }
    public void attackEnemy(){
        System.out.print("Input number of the player:\n");
        int count;
        for (count = 0; count < enemyPersons.size(); count++) {
            System.out.println((count+1) + ".  <<" + enemyPersons.get(count).getNum() + ">>");
        }
        Random r = new Random();
        int countPerson = r.nextInt(count);
        System.out.println("Answer: " + (countPerson+1));
        System.out.println("Input number of the enemy:");
        int mi = 10000;
        int countEnemy = 0;
        for (count = 0; count < mePersons.size(); count++) {
            System.out.println((count+1) + ".  <<" + mePersons.get(count).getNum() + ">>");
            if (mi > mePersons.get(count).getDefence()){
                mi = mePersons.get(count).getDefence();
                countEnemy = count;
            }
        }
        System.out.println("Answer: " + (countEnemy+1));
        mePersons.get(countEnemy).mechanismAttack(enemyPersons.get(countPerson));
        if (mePersons.get(countEnemy).getNum().equals("D"))
            mePersons.remove(mePersons.get(countEnemy));
        enterField();

    }
    public void attack(){
        System.out.print("Input number of the player:\n");
        for (int count = 0; count < mePersons.size(); count++) {
            System.out.println((count+1) + ".  <<" + mePersons.get(count).getNum() + ">>");
        }
        Scanner in = new Scanner(System.in);
        int countPerson = Integer.parseInt(in.nextLine()) - 1;
        System.out.print("Input number of the enemy:\n");
        for (int count = 0; count < enemyPersons.size(); count++) {
            System.out.println((count+1) + ".  <<" + enemyPersons.get(count).getNum() + ">>");
        }
        int countEnemy = Integer.parseInt(in.nextLine()) - 1;
        enemyPersons.get(countEnemy).mechanismAttack(mePersons.get(countPerson));
        boolean isPersonDeleted = false;
        if (enemyPersons.get(countEnemy).getNum().equals("D")) {
            enemyPersons.remove(enemyPersons.get(countEnemy));
            isPersonDeleted = true;
        }
        enterField();
        if (!isPersonDeleted && enemyPersons.contains(enemyPersons.get(countEnemy))){
            System.out.println("Health defender: " + enemyPersons.get(countEnemy).getHealth());
            System.out.println("Defence defender: " + enemyPersons.get(countEnemy).getDefence());
        }
    }
    public void nullingSteps(boolean who){
        if (who){
            for (int w = 0; w < mePersons.size(); w++)
                mePersons.get(w).setSteps(mePersons.get(w).getStartsteps());
        } else {
            for (int w = 0; w < enemyPersons.size(); w++)
                mePersons.get(w).setSteps(enemyPersons.get(w).getStartsteps());
        }
    }
}
