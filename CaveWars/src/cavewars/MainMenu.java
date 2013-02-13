package cavewars;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * @author Mattias Stenqvist, 2B Portalens Gymnasium
 */
class MainMenu extends Menu {

    public MainMenu() 
    {
        MenuButton start = new MenuButton("start", "Start", 200, 200);
        MenuButton options = new MenuButton("options","Inställningar", 200, 300);
        MenuButton exit = new MenuButton("exit","Avsluta", 200, 400);
        
        buttonList.add(start);
        buttonList.add(options);
        buttonList.add(exit);
    }

    @Override
    public void buttonPressed(String id) {
        switch (id) {
            case "exit":
                System.exit(0);
                break;
            case "options":
                Settings meny = new Settings();
                meny.Menu();
                meny.setSize(170, 100);
                meny.setVisible(true);
                break;
            case "start":
                CaveWars.caveWars.enterState(CaveWars.GAME_PLAY_STATE);
				break;
        }
    }
}