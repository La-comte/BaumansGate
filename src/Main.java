import City.MyCity;
import Field.Field;
import GameField.GameField;
import Player.MyPlayer;
import Player.Bot;
import Utils.ConsoleColors;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        int botMoveCnt = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to do?\n\t1) Start a new game\n\t2) Load the game\n\t3) Edit and load the game:");
        int dooStart = Integer.parseInt(in.nextLine());
        Field field = Field.getInstance();
        GameField gameField = new GameField();
        switch (dooStart) {
            case 1 -> {
                field.nullCell();
                System.out.print("My move:\n");
                gameField.enterCatalog(true);
                MyPlayer.moneyLimit(MyPlayer.enterPerson());
                gameField.enterField();
                System.out.println("Enemy move:");
                gameField.enterCatalog(false);
                Bot.moneyLimit(Bot.enterPerson());
                gameField.nullKeys();
            }
            case 2 -> {
                if (Objects.requireNonNull(new File("Save/").listFiles()).length > 1) {
                    File file = new File("Save/");
                    int counter = 0;
                    for (File curFile : Objects.requireNonNull(file.listFiles())) {
                        counter++;
                        if (curFile.getName().equals("temp.txt")){
                            counter--;
                            continue;
                        }
                        System.out.println(counter + ". " + curFile.getName());
                    }
                    System.out.println("Enter folder name:");
                    String dirName = in.nextLine();
                    String path = ("Save/" + dirName);
                    LoadGame loadGame = new LoadGame();
                    loadGame.loadAll(path);
                }
            }
            case 3 -> {
                if (Objects.requireNonNull(new File("Save/").listFiles()).length > 1) {
                    File file = new File("Save/");
                    int counter = 0;
                    for (File curFile : Objects.requireNonNull(file.listFiles())) {
                        counter++;
                        if (counter == Objects.requireNonNull(file.listFiles()).length)
                            break;
                        System.out.println(counter + ". " + curFile);
                    }
                    System.out.println("Enter folder name:");
                    String dirName = in.nextLine();
                    String path = ("Save/" + dirName);
                    LoadGame loadGame = new LoadGame();
                    field.edit(path);
                    loadGame.loadAll(path);
                }
            }
        }
        gameField.enterField();
        String friendship = "";
        while (gameField.notNullPerson() && gameField.notNullEnemy() && !friendship.equals("yes")) {
            System.out.print("My move:\n");
            System.out.print("What you want do?:\n\t1.Buy a person\n\t2.Move a person\n\t3.Attack the enemy\n\t4.Finish the move\n");
            int doo = Integer.parseInt(in.nextLine());
            switch (doo) {
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
                    System.out.println("Bot, do you want to give up?(Enter yes or no)");
                    friendship = Bot.isFriendship();
                    System.out.println(friendship);
                    if (friendship.equals("yes"))
                        break;
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
                    if ((botMoveCnt % 3)==0){
                        System.out.println("Player, do you want to give up?(Enter yes or no)");
                        friendship = in.nextLine();
                        if (friendship.equals("yes"))
                            break;
                    }
                    botMoveCnt++;
                }
            }
        }
        if (friendship.equals("yes")) {
            System.out.println(ConsoleColors.BLUE_BOLD + "Friendship won!!!" + ConsoleColors.RESET);
        } else if (gameField.notNullEnemy()) {
            System.out.println(ConsoleColors.PURPLE_BOLD + "The enemy has won" + ConsoleColors.RESET);
            } else {
            System.out.println(ConsoleColors.GREEN_BOLD + "You're the winner" + ConsoleColors.RESET);
        }
    }
}