package City;

import Player.Player;

import java.util.Scanner;

public class MyCity extends City{
    private static MyCity instance;
    public static MyCity getInstance(){
        if (instance == null)
            instance = new MyCity();
        return instance;
    }
    private MyCity(){}
    private final int x = 0;
    private final int y = 4;
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean hasCityAtCell(int i, int j){
        return i == getX() && j == getY();
    }
    public void buyBuilding(){
        System.out.println("\tWhich one do you want to buy?");
        System.out.println(getFreeKeys());
        Scanner in = new Scanner(System.in);
        String build = in.nextLine();
        addBuilding(build);
        getFreeKeys().remove(build);
        if (Player.resourceLimit(getAllBuilding().get(build))){
            System.out.println("Thank you for buying the building.");
            getAllBuilding().get(build).welcome(true);
        }
    }
    public void upBuilding(){
        System.out.println("\tWhich one do you want to upgrade (visit)?:");
        for (String key : getAllBuilding().keySet())
            System.out.println(key);
        Scanner in = new Scanner(System.in);
        String build = in.nextLine();
        if (Player.resourceLimit(getAllBuilding().get(build))){
            if (getAllBuilding().get(build).getLevel()<3) {
                getAllBuilding().get(build).setLevel(getAllBuilding().get(build).getLevel() + 1);
            }
            System.out.println("\tLevel now:\t" + getAllBuilding().get(build).getLevel());
            getAllBuilding().get(build).welcome(true);
        }
    }
}
