package cavewars;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * @author Mattias Stenqvist, 2B Portalens Gymnasium
 */
class MainMenu extends Menu {
    private int width = 0;
    private int height = 0;

    public MainMenu() throws FileNotFoundException 
    {
        File setting = new File("Settings.txt");
        Scanner setS = new Scanner(setting);
        while(setS.hasNext()){            
                    if(setS.hasNextInt()){
                        width = setS.nextInt();
                        height = setS.nextInt();
                        break;
                    }
                    setS.next();
                }
        
        MenuButton start = new MenuButton("start", "Start", width/2, height/3);
        MenuButton options = new MenuButton("options","Inställningar", width/2, height/2);
        MenuButton exit = new MenuButton("exit","Avsluta", width/2, (height/3)*2);
        
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