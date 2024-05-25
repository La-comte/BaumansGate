import City.BotCity;
import City.MyCity;
import Field.Field;
import Player.Bot;
import Player.MyPlayer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LoadGame {
    public void loadAll(String path) throws FileNotFoundException {

        BotCity botCity = BotCity.getInstance();
        MyCity myCity = MyCity.getInstance();
        Field field = Field.getInstance();

        FileReader fieldReader = new FileReader(path + "/field.txt");
        Scanner fieldScan = new Scanner(fieldReader);
        field.load(fieldScan);

        FileReader botCityReader = new FileReader(path + "/botCity.txt");
        Scanner botCityScan = new Scanner(botCityReader);
        botCity.load(botCityScan);

        FileReader myCityReader = new FileReader(path + "/myCity.txt");
        Scanner myCityScan = new Scanner(myCityReader);
        myCity.load(myCityScan);

        FileReader botReader = new FileReader(path + "/bot.txt");
        Scanner botScan = new Scanner(botReader);
        Bot.load(botScan);

        FileReader myPlayerReader = new FileReader(path + "/MyPerson.txt");
        Scanner myPlayerScan = new Scanner(myPlayerReader);
        MyPlayer.load(myPlayerScan);
    }
}
