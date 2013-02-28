package cavewars;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Menu
{
	public ArrayList<MenuButton> buttonList = new ArrayList<>();
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics) throws SlickException {
            graphics.setColor(new Color(0x404040));
            graphics.fillRect(0, 0, gc.getWidth(), gc.getHeight());
            for(MenuButton button : buttonList)
		{
        		button.render(gc, sbg, graphics);
		}
	}
        
        public void add(MenuButton button)
        {
        }

    public void mouseReleased(int mouseButton, int x, int y) {
        for(MenuButton button : buttonList){
            if(button.isWithin(x, y)){
                buttonPressed(button.id);
            }
        }
    }
    
    public abstract void buttonPressed(String id);
}