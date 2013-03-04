package cavewars;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * @author Mattias Stenqvist, 2B Portalens Gymnasium
 */
class ColMenu extends Menu {

    private int width = CaveWars.windowWidth;
    private int height = CaveWars.windowHeight;
    
    public static int chosenTeam;
    
    public ColMenu() {
        MenuButton yellowTeam = new MenuButton("yellow_team","Join yellow", (width/3)*2, height/2);
        MenuButton redTeam = new MenuButton("red_team","Join Red", width/3, height/2);
        
        buttonList.add(yellowTeam);
        buttonList.add(redTeam);
    }

    @Override
    public void buttonPressed(String id) {
        
        switch (id) {
            case "red_team": chosenTeam = 0; CaveWars.caveWars.enterState(CaveWars.GAME_PLAY_STATE);break;
            case "yellow_team": chosenTeam = 1; CaveWars.caveWars.enterState(CaveWars.GAME_PLAY_STATE);break;
        }
    }

}
