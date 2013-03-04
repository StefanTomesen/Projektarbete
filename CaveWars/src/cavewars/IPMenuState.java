/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cavewars;

import java.io.FileNotFoundException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * Mattias Stenqvist, 3B
 */
class IPMenuState implements GameState {
        public int stateID;
	public Menu ipMeny;
        
	public static String ipAdress = "localhost";
	public static int port = CaveWars.serverPort;

	public IPMenuState(int stateID) throws SlickException
	{
		this.stateID = stateID;
	}

	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
        {
            try
		{
			ipMeny = new IPMenu();
		} catch (FileNotFoundException ex) {}
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
		ipMeny.render(gc, sbg, grphcs);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	@Override
	public void mouseWheelMoved(int steps) {
	}

	@Override
	public void mouseClicked(int button, int x, int y, int i3) {}

	@Override
	public void mousePressed(int button, int x, int y) 
	{
		ipMeny.mouseReleased(button, x, y);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {}

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
	public void keyPressed(int key, char character) {		
		if(key == Input.KEY_ESCAPE)
		{
			CaveWars.caveWars.enterState(CaveWars.MAIN_MENU_STATE);
		}
	}

	@Override
	public void keyReleased(int key, char character) {
            
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
    public void controllerButtonReleased(int i, int i1)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
