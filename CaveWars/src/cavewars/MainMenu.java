package cavewars;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Mattias Stenqvist, 3B Portalens Gymnasium
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
        
		int firstButtonYPos = height / 4;
		int buttonSpacing = height / 10;
		
        MenuButton start = new MenuButton("start", "Start", width/2, firstButtonYPos + buttonSpacing * 0);
		MenuButton runServer = new MenuButton("runServer", "Starta Server", width/2, firstButtonYPos + buttonSpacing * 1);
		MenuButton restartServer = new MenuButton("stopServer", "Stäng Av Server", width/2, firstButtonYPos + buttonSpacing * 2);
        MenuButton options = new MenuButton("options","Inställningar", width/2, firstButtonYPos + buttonSpacing * 3);
        MenuButton exit = new MenuButton("exit","Avsluta", width/2, firstButtonYPos + buttonSpacing * 4);
        
        buttonList.add(start);
		buttonList.add(runServer);
		buttonList.add(restartServer);
        buttonList.add(options);
        buttonList.add(exit);
    }

    @Override
    public void buttonPressed(String id) {
        switch (id) {
            case "exit":
                System.exit(0);
                break;
			case "runServer":
				if(CaveWars.server == null)
				{
					CaveWars.server = new Server(CaveWars.serverPort);
					CaveWars.server.start();
				}
				break;
			case "stopServer":
				if(CaveWars.server != null)
				{
					CaveWars.server.stop();
				}
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
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics) throws SlickException
	{
		super.render(gc, sbg, graphics);
		String serverStatus;
		
		if(CaveWars.server == null) serverStatus = "Offline";
		else if(CaveWars.server.running) serverStatus = "Online";
		else serverStatus = "Stängs Av";
		graphics.drawString("Server: " + serverStatus, 5, 25);
	}
}