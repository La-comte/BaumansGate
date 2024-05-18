package Building;

import Person.Person;
import Player.Bot;
import Player.MyPlayer;
import Utils.ConsoleColors;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Market extends Building{
    public Market(int stone, int wood){
        super(stone, wood);
    }
    @Override
    public void welcome(boolean who) {
        System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWelcome to the Market! Exchange wood -> stone or stone -> wood (enter 1 or 2)" + ConsoleColors.RESET);
        int doo = 0;
        if (who){
            Scanner in = new Scanner(System.in);
            doo = Integer.parseInt(in.nextLine());
        } else {
            Random r = new Random();
            doo = r.nextInt(2) + 1;
        }
        switch (doo) {
            case 1 -> plusStone(who);
            case 2 -> plusWood(who);
        }
    }
    private void plusStone(boolean who){
        System.out.println("\t\tEnter the number of wood (for sale)");
        int saleWood;
        if (who){
            Scanner in = new Scanner(System.in);
            saleWood = Integer.parseInt(in.nextLine());
            MyPlayer.setStone(MyPlayer.getStone() + saleWood);
            MyPlayer.setWood(MyPlayer.getWood() - saleWood);
            System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWood: " + MyPlayer.getWood() + "\tStone: " + MyPlayer.getStone() + ConsoleColors.RESET);
        } else {
            Random r = new Random();
            saleWood = r.nextInt(Bot.getStone()/10);
            System.out.println("\t\t" + saleWood);
            Bot.setStone(Bot.getStone() + saleWood);
            Bot.setWood(Bot.getWood() - saleWood);
            System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWood: " + Bot.getWood() + "\tStone: " + Bot.getStone() + ConsoleColors.RESET);
        }
    }
    private void plusWood(boolean who){
        System.out.println("\t\tEnter the number of stone (for sale)");
        int saleStone;
        if (who){
            Scanner in = new Scanner(System.in);
            saleStone = Integer.parseInt(in.nextLine());
            MyPlayer.setStone(MyPlayer.getStone() - saleStone);
            MyPlayer.setWood(MyPlayer.getWood() + saleStone);
            System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWood: " + MyPlayer.getWood() + "\tStone: " + MyPlayer.getStone() + ConsoleColors.RESET);
        } else {
            Random r = new Random();
            saleStone = r.nextInt(Bot.getStone()/10);
            Bot.setStone(Bot.getStone() - saleStone);
            Bot.setWood(Bot.getWood() + saleStone);
            System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWood: " + Bot.getWood() + "\tStone: " + Bot.getStone() + ConsoleColors.RESET);
        }
    }
    public void setLevel(){
    }
    @Override
    public void save(FileWriter nFile) throws IOException {
        nFile.write("Market" + "\t" + getLevel());
    }
    @Override
    public ArrayList<Person> getPersAcademy() {
        return null;
    }
}
