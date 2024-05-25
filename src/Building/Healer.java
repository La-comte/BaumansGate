package Building;
import Person.Person;
import Player.Bot;
import Player.MyPlayer;
import Utils.ConsoleColors;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Healer extends Building{
    public Healer(int stone, int wood){
        super(stone, wood);
    }
    @Override
    public void welcome(boolean who) {
        System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWelcome to the Healer! We will cure you now" + ConsoleColors.RESET);
        int up;
        if (who) {
            for (int i = 0; i < MyPlayer.sizePlayer(); i++) {
                up = getLevel() + MyPlayer.getMy(i).getHealth();
                MyPlayer.getMy(i).setHealth(up);
            }
        } else {
            for(int i = 0; i < Bot.sizePlayer(); i++){
                up = getLevel() + Bot.getBot(i).getHealth();
                Bot.getBot(i).setHealth(up);
            }
        }
        System.out.println("Thank you for visiting the Healer. The health has been upgraded to:\t" + ConsoleColors.CYAN_BOLD + getLevel() + ConsoleColors.RESET);
    }
    @Override
    public void save(FileWriter nFile) throws IOException {
        nFile.write("Healer" + "\t" + getLevel());
    }
    @Override
    public ArrayList<Person> getPersAcademy() {
        return null;
    }
}
