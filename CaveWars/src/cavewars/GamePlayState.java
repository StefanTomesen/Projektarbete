package cavewars;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class GamePlayState implements GameState
{
	public int stateID;
	
	public World world;

	public GamePlayState(int stateID) throws SlickException
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
		ImageLoader.initiateImageList();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
		world.render(gc, sbg, grphcs);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		world.update(delta);
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		world = new World();
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		world = null;
	}

	
	@Override
	public void mouseWheelMoved(int i) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void mouseClicked(int i, int i1, int i2, int i3) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		world.mousePressed(button, x, y);
	}

	@Override
	public void mouseReleased(int i, int i1, int i2) {
		//throw new UnsupportedOperationException("Not supported yet.");
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
	public void keyPressed(int key, char character) {
		world.keyPressed(key, character);
		
		if(key == Input.KEY_ESCAPE)
		{
			CaveWars.caveWars.enterState(CaveWars.MAIN_MENU_STATE);
		}
	}

	@Override
	public void keyReleased(int key, char character) {
		world.keyReleased(key, character);
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
