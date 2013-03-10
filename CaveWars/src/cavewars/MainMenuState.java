package cavewars;

import java.io.FileNotFoundException;
import java.util.logging.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainMenuState implements GameState
{
	public int stateID;
	
	public Menu menu;
        
	public MainMenuState(int stateID)
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
            switch(stateID){
                case 0: try {
                            menu = new MainMenu();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MainMenuState.class.getName()).log(Level.SEVERE, null, ex);
                        } break;
                case 2: try {
                            menu = new MapMenu();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MainMenuState.class.getName()).log(Level.SEVERE, null, ex);
                        } break;
                case 3: 
                        menu = new ColMenu(); break;
                case 4: try {
                            menu = new IPMenu();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MainMenuState.class.getName()).log(Level.SEVERE, null, ex);
                        } break;
                case 5: 
                        menu = new SettingsMenu(); break;
            }
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException 
	{
		menu.render(gc, sbg, grphcs);
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
		menu.mouseReleased(button, x, y);
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
	    if(stateID == 0){
                if(key == Input.KEY_ESCAPE)
		{
                    System.exit(0);
		}
            }else{
                if(key == Input.KEY_ESCAPE){
                    CaveWars.caveWars.enterState(CaveWars.MAIN_MENU_STATE);
                }                
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
