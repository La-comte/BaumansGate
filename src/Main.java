import Field.Field;
import GameField.GameField;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Field field = Field.getInstance();
        field.nullCeil();
        GameField gameField = new GameField();
        gameField.moneyLimit(gameField.meEnterPerson());
        System.out.println("Enemy move:");
        gameField.moneyLimitEnemy(gameField.enemyEnterPerson());
        while (gameField.notNull()){
            System.out.print("What you want do?:\n\t1.Buy a person\n\t2.Move a person\n\t3.Attack the enemy\n\t4.Finish the move\n");
            Scanner in = new Scanner(System.in);
            int doo = Integer.parseInt(in.nextLine());
            switch (doo){
                case 1 -> {
                    gameField.moneyLimit(gameField.meEnterPerson());
                }
                case 2 -> {
                    gameField.motion();
                }
                case 3 -> {
                    gameField.attack();
                }
                case 4 -> {
                    gameField.nullingSteps(true);
                    System.out.println("Enemy move:");
                    System.out.print("What you want do?:\n\t1.Buy a person\n\t2.Move a person\n\t3.Attack the enemy\n\t4.Finish the move\n");
                    System.out.println("Answer: 2");
                    gameField.motionEnemy();
                    System.out.print("What you want do?:\n\t1.Buy a person\n\t2.Move a person\n\t3.Attack the enemy\n\t4.Finish the move\n");
                    Random r = new Random();
                    int doEnemy = r.nextInt(4) + 1;
                    System.out.println("Answer: " + doEnemy);
                    switch (doEnemy) {
                        case 1 -> {
                            gameField.moneyLimitEnemy(gameField.enemyEnterPerson());
                        }
                        case 2 -> {
                            gameField.motionEnemy();
                        }
                        case 3 -> {
                            gameField.attackEnemy();
                        }
                        case 4 -> {
                            gameField.nullingSteps(false);
                            System.out.print("My move:\n");
                        }
                    }
                }
            }
        }
    }
}