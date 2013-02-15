package cavewars;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

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
		//throw new UnsupportedOperationException("Not supported yet.");
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
	public void mousePressed(int i, int i1, int i2) {
		//throw new UnsupportedOperationException("Not supported yet.");
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
		if(key == Input.KEY_LEFT)
		{
			world.localPlayer.doWalk(EntityPlayer.LEFT);
		}
		if(key == Input.KEY_RIGHT)
		{
			world.localPlayer.doWalk(EntityPlayer.RIGHT);
		}
		if(key == Input.KEY_A)
		{
			world.camera.zoom(1/0.8F);
		}
		if(key == Input.KEY_Z)
		{
			world.camera.zoom(0.8F);
		}
		
		if(key == Input.KEY_SPACE)
		{
			world.localPlayer.velocityY = -5; // Negative values point up.
		}
	}

	@Override
	public void keyReleased(int key, char character) {
		if(key == Input.KEY_LEFT && world.localPlayer.direction == EntityPlayer.LEFT)
		{
			world.localPlayer.doStop();
		}
		else if(key == Input.KEY_RIGHT && world.localPlayer.direction == EntityPlayer.RIGHT)
		{
			world.localPlayer.doStop();
		}
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
