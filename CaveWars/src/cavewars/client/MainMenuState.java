package cavewars.client;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainMenuState implements GameState
{
	public static int id = 1;
	public Menu menu;
        
        
	public MainMenuState()
	{
            
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		menu = new MainMenu();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
		menu.render(gc, sbg, grphcs);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	
	@Override
	public void mouseWheelMoved(int i) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void mouseClicked(int button, int x, int y, int i3) {
		
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
            menu.mouseReleased(button, x, y);
            
        }

	@Override
	public void mouseMoved(int i, int i1, int i2, int i3) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void mouseDragged(int i, int i1, int i2, int i3) {
            //throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void setInput(Input input) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void inputEnded() {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void inputStarted() {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void keyPressed(int i, char c) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void keyReleased(int i, char c) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void controllerLeftPressed(int i) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void controllerLeftReleased(int i) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void controllerRightPressed(int i) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void controllerRightReleased(int i) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void controllerUpPressed(int i) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void controllerUpReleased(int i) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void controllerDownPressed(int i) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void controllerDownReleased(int i) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void controllerButtonPressed(int i, int i1) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void controllerButtonReleased(int i, int i1) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}
}
