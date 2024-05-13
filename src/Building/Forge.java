package Building;

import Person.Person;
import Player.Bot;
import Player.MyPlayer;
import Utils.ConsoleColors;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Forge extends Building{
    public Forge(int stone, int wood){
        super(stone, wood);
    }
    @Override
    public void welcome(boolean who) {
        System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWelcome to the Forge! Now they will make you a new sword" + ConsoleColors.RESET);
        int up;
        if(who){
            for(int i = 0; i < MyPlayer.sizePlayer(); i++){
                up = getLevel() + MyPlayer.getMy(i).getAttack();
                MyPlayer.getMy(i).setAttack(up);
            }
        } else {
            for(int i = 0; i < Bot.sizePlayer(); i++){
                up = getLevel() + Bot.getBot(i).getAttack();
                Bot.getBot(i).setAttack(up);
            }
        }
        System.out.println("Thank you for visiting the Forge. The attack has been upgraded to:\t" + ConsoleColors.CYAN_BOLD + getLevel() + ConsoleColors.RESET);
    }
    @Override
    public void save(FileWriter nFile) throws IOException {
        nFile.write("Forge" + "\t" + getLevel() + "\t" + getStone() + "\t" + getLevel() + ";");
    }
    @Override
    public ArrayList<Person> getPersAcademy() {
        return null;
    }
}
