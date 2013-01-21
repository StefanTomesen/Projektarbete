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
	public CaveWars()
	{
		super("CaveWars");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new MainMenuState());
	}
	
	public static void main(String[] args) throws SlickException
    {
		VersionTracker.generateMD5();
		Dialogs.showMessage(VersionTracker.getMD5Sum());

		AppGameContainer app = new AppGameContainer(new ScalableGame(new CaveWars(), 800, 600));

		app.setDisplayMode(800, 600, false);
		app.start();
    }
}