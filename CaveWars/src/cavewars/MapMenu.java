package cavewars;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * @author Mattias Stenqvist, 3B Portalens Gymnasium
 */
class MapMenu extends Menu {
    private int width = CaveWars.windowWidth;
    private int height = CaveWars.windowHeight;
    public static int chosenMap = 0;

    public MapMenu() throws FileNotFoundException 
    {   
        MenuButton map_1 = new MenuButton("map_1", "Map no. 1", width/2, height/3);
        MenuButton map_2 = new MenuButton("map_2","Map no. 2", width/2, height/2);
        MenuButton map_3 = new MenuButton("map_3","Map no. 3", width/2, (height/3)*2);
        
        buttonList.add(map_1);
        buttonList.add(map_2);
        buttonList.add(map_3);

    }

    @Override
    public void buttonPressed(String id) {
        switch (id) {
            case "map_1":
                chosenMap = 0;
                CaveWars.caveWars.enterState(CaveWars.MAIN_MENU_STATE);
                break;
            case "map_2":
                chosenMap = 1;
                CaveWars.caveWars.enterState(CaveWars.MAIN_MENU_STATE);
                break;
            case "map_3":
                chosenMap = 2;
                CaveWars.caveWars.enterState(CaveWars.MAIN_MENU_STATE);
                break;
        }
    }
}