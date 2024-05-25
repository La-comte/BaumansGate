package Building;

import Person.Person;
import Player.Bot;
import Player.MyPlayer;
import Utils.ConsoleColors;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Arsenal extends Building{
    public Arsenal(int stone, int wood){
        super(stone, wood);
    }
    @Override
    public void welcome(boolean who) {
        System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWelcome to the Arsenal! Now they will make you a new armor" + ConsoleColors.RESET);
        int up;
        if (who){
            for(int i = 0; i < MyPlayer.sizePlayer(); i++){
                up = getLevel() + MyPlayer.getMy(i).getDefence();
                MyPlayer.getMy(i).setDefence(up);
            }
        } else {
            for(int i = 0; i < Bot.sizePlayer(); i++){
                up = getLevel() + Bot.getBot(i).getDefence();
                Bot.getBot(i).setDefence(up);
            }
        }
        System.out.println("Thank you for visiting the Arsenal. The defence has been upgraded to:\t" + ConsoleColors.CYAN_BOLD + getLevel() + ConsoleColors.RESET);
    }
    @Override
    public void save(FileWriter nFile) throws IOException {
        nFile.write("Arsenal" + "\t" + getLevel());
    }

    @Override
    public ArrayList<Person> getPersAcademy() {
        return null;
    }
}
