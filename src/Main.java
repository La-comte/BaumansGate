import City.MyCity;
import Field.Field;
import GameField.GameField;
import Player.MyPlayer;
import Player.Bot;
import Utils.ConsoleColors;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Field field = Field.getInstance();
        field.nullCell();
        GameField gameField = new GameField();
        System.out.print("My move:\n");
        gameField.enterCatalog(true);
        MyPlayer.moneyLimit(MyPlayer.enterPerson());
        gameField.enterField();
        System.out.println("Enemy move:");
        gameField.enterCatalog(false);
        Bot.moneyLimit(Bot.enterPerson());
        gameField.enterField();
        gameField.nullKeys();
        while (gameField.notNullPerson() && gameField.notNullEnemy()){
            System.out.print("My move:\n");
            System.out.print("What you want do?:\n\t1.Buy a person\n\t2.Move a person\n\t3.Attack the enemy\n\t4.Finish the move\n");
            Scanner in = new Scanner(System.in);
            int doo = Integer.parseInt(in.nextLine());
            switch (doo){
                case 1 -> {
                    gameField.enterCatalog(true);
                    MyPlayer.moneyLimit(MyPlayer.enterPerson());
                    gameField.enterField();
                }
                case 2 -> {
                    MyPlayer.motionPlayer();
                    gameField.enterField();
                }
                case 3 -> {
                    gameField.buyMyBuilding();
                    MyPlayer.attackPlayer();
                    gameField.enterField();
                }
                case 4 -> {
                    SaveGame file = new SaveGame();
                    file.saveall();
                    gameField.nullingSteps(true);
                    System.out.println("Enemy move:");
                    System.out.print("What you want do?:\n\t1.Buy a person\n\t2.Move a person\n\t3.Attack the enemy\n\t4.Finish the move\n");
                    System.out.println("Answer: 2");
                    Bot.motionPlayer();
                    gameField.enterField();
                    System.out.println("Enemy move:");
                    System.out.print("What you want do?:\n\t1.Buy a person\n\t2.Move a person\n\t3.Attack the enemy\n\t4.Finish the move\n");
                    Random r = new Random();
                    int doEnemy = r.nextInt(4) + 1;
                    //int doEnemy = 3;
                    System.out.println("Answer: " + doEnemy);
                    switch (doEnemy) {
                        case 1 -> {
                            gameField.enterCatalog(false);
                            Bot.moneyLimit(Bot.enterPerson());
                            gameField.enterField();
                        }
                        case 2 -> {
                            Bot.motionPlayer();
                            gameField.enterField();
                        }
                        case 3 -> {
                            gameField.buyBotBuilding();
                            Bot.attackPlayer();
                            gameField.enterField();
                        }
                    }
                    gameField.nullingSteps(false);
                }
            }
        }
        if (gameField.notNullPerson()){
            System.out.println(ConsoleColors.GREEN_BOLD + "You're the winner" + ConsoleColors.RESET);
        } else { System.out.println(ConsoleColors.PURPLE_BOLD + "The enemy has won" + ConsoleColors.RESET); }
    }
}