package cavewars.client;

import cavewars.util.VersionTracker;
/*import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;*/

/**
 * The base class responsible for starting the game.
 * WIP name might change.
 */
public class CaveWars// extends StateBasedGame
{	
	public CaveWars()
	{
		//super("CaveWars");
	}

	/*@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new MainMenuState());
	}*/
	
	public static void main(String[] args)// throws SlickException
    {
		VersionTracker.generateMD5();
		
		/*AppGameContainer app = new AppGameContainer(new ScalableGame(new CaveWars(), 800, 600));

		app.setDisplayMode(800, 600, false);
		app.start();*/
    }
}
