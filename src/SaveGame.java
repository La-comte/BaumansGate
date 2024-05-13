import City.BotCity;
import City.MyCity;
import Field.Field;
import Player.Bot;
import Player.MyPlayer;

import java.util.Scanner;

public class SaveGame {
    public void saveall(){
        System.out.println("Do you want to save it? (0 or 1)");
        Scanner in = new Scanner(System.in);
        int save = Integer.parseInt(in.nextLine());
        Field field = Field.getInstance();
        BotCity botCity = BotCity.getInstance();
        MyCity myCity = MyCity.getInstance();
        if (save == 1){
            try {
                field.save();
                MyPlayer.save();
                Bot.save();
                botCity.save("botCity");
                myCity.save("myCity");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
