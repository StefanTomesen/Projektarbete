package cavewars.client;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Menu
{
	public ArrayList<MenuButton> buttonList = new ArrayList<MenuButton>();
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics) throws SlickException {
		for(MenuButton button : buttonList)
		{
			graphics.setColor(new Color(0x004D00));
			graphics.drawRect(0, 0, gc.getWidth(), gc.getHeight());
			
			button.render(gc, sbg, graphics);
		}
	}
}