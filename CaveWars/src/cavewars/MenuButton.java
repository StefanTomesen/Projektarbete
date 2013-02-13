package cavewars;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MenuButton {

	public String id;
	public int x;
	public int y;
	public int width;
	public int height;
	public String label;

	public MenuButton(String id, String label, int xPos, int yPos, int width, int height) {
		this.id = id;
		this.label = label;
		this.x = xPos;
		this.y = yPos;
		this.width = width;
		this.height = height;
	}

	public MenuButton(String id, String label, int xPos, int yPos) {
		this(id, label, xPos, yPos, 150, 30);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics) throws SlickException {
		// Draw button background
		graphics.setColor(new Color(Color.black));
		graphics.fillRect(x - width / 2, y - height / 2, width, height);

		// Draw button text
		int lineWidth = graphics.getFont().getWidth(label);
		int lineHeight = graphics.getFont().getHeight(label);
		graphics.setColor(Color.white);
		graphics.drawString(label, x - lineWidth / 2, y - lineHeight / 2);
	}

	public boolean isWithin(int x, int y) {
		if ((x >= this.x - width / 2) && (x < this.x + width / 2)) {
			if ((y >= this.y - height / 2) && (y < this.y + height / 2)) {
				return true;
			}
		}
		return false;
	}
}
