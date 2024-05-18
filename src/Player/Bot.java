package Player;

import City.BotCity;
import Person.Person;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bot extends Player{
    static ArrayList<Person> enemyPersons = new ArrayList<>();
    private static Bot instance;
    private Bot(int wallet, int stone, int wood){
        super(wallet, stone, wood);
    }
    public static Bot getInstance(int wallet, int stone, int wood){
        if (instance == null)
            instance = new Bot(wallet, stone, wood);
        return instance;
    }
    public static boolean isEmptyBot(){ return enemyPersons.isEmpty();}
    public static int sizePlayer(){
        return enemyPersons.size();
    }
    public static Person getBot(int num) { return enemyPersons.get(num); }
    public static void removeBot(Person pers){ enemyPersons.remove(pers); }
    public static boolean containsEnemy(Person pers){ return enemyPersons.contains(pers); }

    public static void moneyLimit(Person pers) {
        if (pers.getPrice() <= getWallet()) {
            setWallet(getWallet() - pers.getPrice());
            System.out.println("Wallet = " + getWallet());
            enemyPersons.add(pers);
        } else {
            System.out.println("LOL You don't have any money.");
        }
    }

    //@Override
    public static Person enterPerson() {
        System.out.println("Input coordinates:");
        Random r = new Random();
        int x = r.nextInt(10);
        int y = r.nextInt(10);
        System.out.print((x + 1) + "\n" + (y + 1) + "\n");
        System.out.println("Input Type: ");
        //int t = r.nextInt(10 + botCity.getAllBuilding().get("Academy").size()) + 1;
        int t = 10;
        System.out.println(t);
        return createPerson(x, y, t, false);
    }
    public static void motionPlayer() {
        System.out.print("Input number of the player:\n");
        int count = 0;
        for (count = 0; count < enemyPersons.size(); count++) {
            System.out.println((count + 1) + ".  <<" + enemyPersons.get(count).getNum() + ">>");
        }
        Random r = new Random();
        int countPerson = r.nextInt(count);
        System.out.println("Answer: " + (countPerson + 1));
        System.out.println("Input coordinates where you want to go:");
        int x = r.nextInt(9);
        int y = r.nextInt(9);
        System.out.print((x + 1) + "\n" + (y + 1) + "\n");
        enemyPersons.get(countPerson).mechanismMotion(x, y);
    }
    //@Override
    public static void attackPlayer() {
        System.out.print("Input number of the player:\n");
        int count;
        for (count = 0; count < enemyPersons.size(); count++) {
            System.out.println((count + 1) + ".  <<" + enemyPersons.get(count).getNum() + ">>");
        }
        Random r = new Random();
        int countPerson = r.nextInt(count);
        System.out.println("Answer: " + (countPerson + 1));
        System.out.println("Input number of the enemy:");
        int mi = 10000;
        int countEnemy = 0;
        for (count = 0; count < MyPlayer.sizePlayer(); count++) {
            System.out.println((count + 1) + ".  <<" + MyPlayer.getMy(count).getNum() + ">>");
            if (mi > MyPlayer.getMy(count).getDefence()) {
                mi = MyPlayer.getMy(count).getDefence();
                countEnemy = count;
            }
        }
        System.out.println("Answer: " + (countEnemy + 1));
        MyPlayer.getMy(countEnemy).mechanismAttack(enemyPersons.get(countPerson));
        if (MyPlayer.getMy(countEnemy).getNum().equals("D"))
            MyPlayer.removeMy(MyPlayer.getMy(countEnemy));

    }
    public static void save(String path) throws Exception {
        FileWriter nFile = new FileWriter(path + "/Bot.txt");
        int cnt = 0;
        for (int i = 0; i < sizePlayer(); i++) {
            cnt++;
            nFile.write(enemyPersons.get(i).getNum() + "\t");
            nFile.write(enemyPersons.get(i).getX() + "\t");
            nFile.write(enemyPersons.get(i).getY() + "\t");
            nFile.write(enemyPersons.get(i).getHealth() + "\t");
            nFile.write(enemyPersons.get(i).getAttack() + "\t");
            nFile.write(enemyPersons.get(i).getRangeAttack() + "\t");
            nFile.write(String.valueOf(enemyPersons.get(i).getDefence()));
            if (cnt != sizePlayer())
                nFile.write("\n");
        }
        nFile.close();
    }
    public static void load(Scanner botScan) {
        while (botScan.hasNextLine()) {
            String tt = botScan.next();
            int t = 0;
            if (tt.equals("❤"))
                t = 10;
            else
                t = Integer.parseInt(tt);
            int x = Integer.parseInt(botScan.next());
            int y = Integer.parseInt(botScan.next());
            Person pers = createPerson(x, y, t, false);
            int health = Integer.parseInt(botScan.next());
            if (health != pers.getHealth())
                pers.setHealth(health);

            int attack = Integer.parseInt(botScan.next());
            if (attack != pers.getAttack())
                pers.setAttack(attack);

            int rangeAttack = Integer.parseInt(botScan.next());
            if (rangeAttack != pers.getRangeAttack())
                pers.setRangeAttack(rangeAttack);

            int defence = Integer.parseInt(botScan.next());
            if (defence != pers.getDefence())
                pers.setDefence(defence);

            enemyPersons.add(pers);
        }
    }
}
