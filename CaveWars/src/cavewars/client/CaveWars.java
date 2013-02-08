package cavewars.client;

import cavewars.util.*;
import java.io.File;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * The base class responsible for starting the game.
 * WIP name might change.
 */
public class CaveWars extends StateBasedGame
{
    public static CaveWars caveWars;
	public CaveWars()
	{
		super("CaveWars");
                caveWars = this;
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new MainMenuState());
	}
	
	public static void main(String[] args) throws SlickException
    {
		//VersionTracker.generateMD5();
		//Dialogs.showMessage(VersionTracker.getMD5Sum());
                int width = 1000;
                int height = 500;
        
		AppGameContainer app = new AppGameContainer(new ScalableGame(new CaveWars(), width, height));

		app.setDisplayMode(width, height, false);
		app.start();
    }
}