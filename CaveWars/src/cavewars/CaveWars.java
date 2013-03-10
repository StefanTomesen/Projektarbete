package cavewars;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * The base class responsible for starting the game.
 * WIP name might change.
 * 
 * @author Mattias Stenqvist, 3B Portalens Gymnasium
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class CaveWars extends StateBasedGame
{
	public static AppGameContainer game;
	public static CaveWars caveWars;
	public static Server server = null;
	
	public static final int MAIN_MENU_STATE = 0;
	public static final int GAME_PLAY_STATE = 1;
	public static final int MAP_MENU_STATE = 2;
	public static final int COL_MENU_STATE = 3;
	public static final int IP_MENU_STATE = 4;
        public static final int SET_MENU_STATE = 5;
	
	public static int windowWidth = 500; // Default settings.
	public static int windowHeight = 500; 
	
	public static int serverPort = 36745;
	
	public CaveWars()
	{
            super("CaveWars");
            caveWars = this;
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new MainMenuState(MAIN_MENU_STATE));
		addState(new GamePlayState(GAME_PLAY_STATE));
		addState(new MainMenuState(MAP_MENU_STATE));
		addState(new MainMenuState(COL_MENU_STATE));
		addState(new MainMenuState(IP_MENU_STATE));
                addState(new MainMenuState(SET_MENU_STATE));
	}
        
	public static void main(String[] args) throws SlickException, IOException
    {
        PrintWriter settings = new PrintWriter(new BufferedWriter(new FileWriter("Settings.txt", true))); //Skapar filen el. öppnar filen
        File setting = new File("Settings.txt");
        Scanner setS = new Scanner(setting);
        Settings fill = new Settings();
        if (setS.hasNext() == false) {  //Kollar om filen har innehåll
            fill.Fill(3, "W", "S", "A", "D", "SPACE");
        }
        settings.close();
		while(setS.hasNext()){
			if(setS.hasNextInt()){
				windowWidth = setS.nextInt();
				windowHeight = setS.nextInt();
				break;
			}
			setS.next();
		}
                
		game = new AppGameContainer(new CaveWars());
		CaveWars.setScreenSize(windowWidth, windowHeight);
		
		game.setVSync(true);
		//game.setTargetFrameRate(60);
		
		try
		{
			game.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
    }
	
	public static void setScreenSize(int width, int height)
	{
		windowWidth = width;
		windowHeight = height;
		
		try
		{
			CaveWars.game.setDisplayMode(windowWidth, windowHeight, false);
		}
		catch (SlickException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void setFullscreenMode()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screensize = toolkit.getScreenSize();
		
		windowWidth = screensize.width;
		windowHeight = screensize.height;
		
		try
		{
			CaveWars.game.setDisplayMode(windowWidth, windowHeight, true);
		}
		catch (SlickException ex)
		{
			ex.printStackTrace();
		}
	}
}
