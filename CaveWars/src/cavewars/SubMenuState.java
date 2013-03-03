package cavewars;

import java.io.FileNotFoundException;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class SubMenuState implements GameState
{
	public int stateID;
	
	public Menu meny;
        
	public SubMenuState(int stateID)
	{
		this.stateID = stateID;
	}

	@Override
	public int getID() 
	{
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		try
		{
			meny = new SubMenu();
		} catch (FileNotFoundException ex) {}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException 
	{
		meny.render(gc, sbg, grphcs);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {}
	
	@Override
	public void mouseWheelMoved(int i) {}

	@Override
	public void mouseClicked(int button, int x, int y, int i3) {}

	@Override
	public void mousePressed(int button, int x, int y) 
	{
		meny.mouseReleased(button, x, y);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {}

	@Override
	public void mouseMoved(int i, int i1, int i2, int i3) {}

	@Override
	public void mouseDragged(int i, int i1, int i2, int i3) {}

	@Override
	public void setInput(Input input) {}

	@Override
	public boolean isAcceptingInput() 
	{
		return true;
	}

	@Override
	public void inputEnded() {}

	@Override
	public void inputStarted() {}

	@Override
	public void keyPressed(int key, char c) 
	{
		if(key == Input.KEY_ESCAPE)
		{
			CaveWars.caveWars.enterState(CaveWars.MAIN_MENU_STATE);
		}
	}

	@Override
	public void keyReleased(int i, char c) {}

	@Override
	public void controllerLeftPressed(int i) {}

	@Override
	public void controllerLeftReleased(int i) {}

	@Override
	public void controllerRightPressed(int i) {}

	@Override
	public void controllerRightReleased(int i) {}

	@Override
	public void controllerUpPressed(int i) {}

	@Override
	public void controllerUpReleased(int i) {}

	@Override
	public void controllerDownPressed(int i) {}

	@Override
	public void controllerDownReleased(int i) {}

	@Override
	public void controllerButtonPressed(int i, int i1) {}

	@Override
	public void controllerButtonReleased(int i, int i1) {}
}
