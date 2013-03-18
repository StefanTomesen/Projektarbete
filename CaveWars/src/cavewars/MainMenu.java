package cavewars;

import java.io.*;
import java.util.Scanner;
import java.util.logging.*;
import javax.swing.JOptionPane;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Mattias Stenqvist, 3B Portalens Gymnasium
 */
class MainMenu extends Menu {
    private int width = CaveWars.windowWidth;
    private int height = CaveWars.windowHeight;

    public MainMenu() throws FileNotFoundException 
    {   
		int firstButtonYPos = height / 4;
		int buttonSpacing = height / 10;
		
        MenuButton start = new MenuButton("start", "Start", width/2, firstButtonYPos + buttonSpacing * 0);
        MenuButton runServer = new MenuButton("runServer", "Run server", width/2, firstButtonYPos + buttonSpacing * 1);
        MenuButton stopServer = new MenuButton("stopServer", "Close server", width/2, firstButtonYPos + buttonSpacing * 2);
        MenuButton level = new MenuButton("setLevel", "Select map", width/2, firstButtonYPos + buttonSpacing * 3);
        MenuButton options = new MenuButton("options","Settings", width/2, firstButtonYPos + buttonSpacing * 4);
        MenuButton exit = new MenuButton("exit","Exit game", width/2, firstButtonYPos + buttonSpacing * 5);
        
        buttonList.add(start);
        buttonList.add(runServer);
        buttonList.add(stopServer);
        buttonList.add(level);
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
                                    try
                                    {
                        CaveWars.server = new Server(CaveWars.serverPort);
                        CaveWars.server.start();
                }
                                    catch (SlickException ex)
                                    {
                                            System.out.println("Failed to start server!");
                                            ex.printStackTrace();
                                    }
                            }
                break;
            case "stopServer":
                if(CaveWars.server != null)
                {
                        CaveWars.server.stop();
                }
                break;
            case "setLevel":
                CaveWars.caveWars.enterState(CaveWars.MAP_MENU_STATE);
                break;
            case "options":
                CaveWars.caveWars.enterState(CaveWars.SET_MENU_STATE);
                break;
            case "start":
                CaveWars.caveWars.enterState(CaveWars.COL_MENU_STATE);
				break;
        }
    }
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics) throws SlickException
	{
		super.render(gc, sbg, graphics);
		String serverStatus;
		
		if(CaveWars.server == null) serverStatus = "Offline";
		else if(CaveWars.server.running) serverStatus = "Online";
		else serverStatus = "Shutting down";
		graphics.drawString("Server: " + serverStatus, 5, 25);
	}
}
