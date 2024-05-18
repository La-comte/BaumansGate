package Player;

import Person.Person;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MyPlayer extends Player {
    static ArrayList<Person> myPersons = new ArrayList<>();
    private static MyPlayer instance;

    private MyPlayer(int wallet, int stone, int wood) {
        super(wallet, stone, wood);
    }

    public static MyPlayer getInstance(int wallet, int stone, int wood) {
        if (instance == null)
            instance = new MyPlayer(wallet, stone, wood);
        return instance;
    }

    public static Person getMy(int num) {
        return myPersons.get(num);
    }

    public static boolean isEmptyMy() {
        return myPersons.isEmpty();
    }

    public static void removeMy(Person pers) {
        myPersons.remove(pers);
    }

    public static int sizePlayer() {
        return myPersons.size();
    }

    public static void moneyLimit(Person pers) {
        if (pers.getPrice() <= wallet) {
            setWallet(wallet - pers.getPrice());
            System.out.println("Wallet = " + wallet);
            myPersons.add(pers);
        } else {
            System.out.println("LOL You don't have any money.");
        }
    }

    public static Person enterPerson() {
        System.out.print("Input coordinates:\n");
        Scanner in = new Scanner(System.in);
        int x = Integer.parseInt(in.nextLine());
        int y = Integer.parseInt(in.nextLine());
        System.out.print("Input Type: ");
        int t = Integer.parseInt(in.nextLine());
        x -= 1;
        y -= 1;
        return createPerson(x, y, t, true);
    }

    public static void motionPlayer() {
        System.out.print("Input number of the player:\n");
        for (int count = 0; count < myPersons.size(); count++) {
            System.out.println((count + 1) + ".  <<" + myPersons.get(count).getNum() + ">>");
        }
        Scanner in = new Scanner(System.in);
        int count = Integer.parseInt(in.nextLine()) - 1;
        System.out.print("Input coordinates where you want to go:\n");
        int x = Integer.parseInt(in.nextLine()) - 1;
        int y = Integer.parseInt(in.nextLine()) - 1;
        myPersons.get(count).mechanismMotion(x, y);
    }

    public static void attackPlayer() {
        System.out.print("Input number of the player:\n");
        for (int count = 0; count < myPersons.size(); count++) {
            System.out.println((count + 1) + ".  <<" + myPersons.get(count).getNum() + ">>");
        }
        Scanner in = new Scanner(System.in);
        int countPerson = Integer.parseInt(in.nextLine()) - 1;
        System.out.print("Input number of the enemy:\n");
        for (int count = 0; count < Bot.sizePlayer(); count++) {
            System.out.println((count + 1) + ".  <<" + Bot.getBot(count).getNum() + ">>");
        }
        int countEnemy = Integer.parseInt(in.nextLine()) - 1;
        Bot.getBot(countEnemy).mechanismAttack(myPersons.get(countPerson));
        if (Bot.getBot(countEnemy).getNum().equals("D")) {
            Bot.removeBot(Bot.getBot(countEnemy));
        }
    }

    public static void save(String path) throws Exception {
        FileWriter nFile = new FileWriter(path + "/MyPerson.txt");
        int cnt = 0;
        for (int i = 0; i < sizePlayer(); i++) {
            cnt++;
            nFile.write(myPersons.get(i).getNum() + "\t");
            nFile.write(myPersons.get(i).getX() + "\t");
            nFile.write(myPersons.get(i).getY() + "\t");
            nFile.write(myPersons.get(i).getHealth() + "\t");
            nFile.write(myPersons.get(i).getAttack() + "\t");
            nFile.write(myPersons.get(i).getRangeAttack() + "\t");
            nFile.write(String.valueOf(myPersons.get(i).getDefence()));
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
            myPersons.add(pers);
        }
    }
}
