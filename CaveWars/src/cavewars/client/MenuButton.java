package cavewars.client;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MenuButton
{
	public int x;
	public int y;
	public int width;
	public int height;
	public String label;
	
	public MenuButton(String label, int xPos, int yPos, int width, int height)
	{
		this.label = label;
		this.x = xPos;
		this.y = yPos;
		this.width = width;
		this.height = height;
	}
	
	public MenuButton(String label, int xPos, int yPos)
	{
		this(label, xPos, yPos, 100, 30);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics) throws SlickException {
		// Draw button background
		graphics.setColor(new Color(0x4DA64D));
		graphics.drawRect(x - width/2, y - height/2, width, height);
		
		// Draw button text
		int lineWidth = graphics.getFont().getWidth(label);
		graphics.setColor(Color.black);
		graphics.drawString(label, x - lineWidth, y);
	}
}
