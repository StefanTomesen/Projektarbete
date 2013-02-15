package cavewars;

import java.io.File;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * The base class responsible for starting the game.
 * WIP name might change.
 */
public class CaveWars extends StateBasedGame
{
	public static final int MAIN_MENU_STATE = 0;
	public static final int GAME_PLAY_STATE = 1;
	
    public static CaveWars caveWars;
	
	public boolean server;
	
	public CaveWars()
	{
		super("CaveWars");
        caveWars = this;
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new MainMenuState(MAIN_MENU_STATE));
		addState(new GamePlayState(GAME_PLAY_STATE));
	}
	
	public static void main(String[] args) throws SlickException
    {
		ImageLoader.initiateImageList();
		
		int width = 1000;
		int height = 500;
        
		AppGameContainer app = new AppGameContainer(new ScalableGame(new CaveWars(), width, height));

		app.setDisplayMode(width, height, false);
		app.start();
    }
}