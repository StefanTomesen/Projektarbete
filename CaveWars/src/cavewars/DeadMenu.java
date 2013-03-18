/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cavewars;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * Mattias Stenqvist, 3B
 */

class DeadMenu extends Menu {

    public DeadMenu() {
        MenuButton respawn = new MenuButton("respawn", "Respawn", CaveWars.windowWidth / 2, CaveWars.windowHeight / 4);
        MenuButton quit = new MenuButton("quit", "Quit to main", CaveWars.windowWidth / 2, (CaveWars.windowHeight / 4) * 3);
        
        buttonList.add(respawn);
        buttonList.add(quit);
    }

    @Override
    public void buttonPressed(String id) {
        switch(id){
            case("respawn"): CaveWars.caveWars.enterState(CaveWars.GAME_PLAY_STATE);break;
            case("quit"): CaveWars.caveWars.enterState(CaveWars.MAIN_MENU_STATE);break;
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics) throws SlickException {
        Image image = ImageLoader.getImage(ImageLoader.dedScreen);
        int windowHeight = CaveWars.windowHeight;
        int imageHeight = image.getHeight();
        float scale = (float) windowHeight/imageHeight;
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, CaveWars.windowWidth, CaveWars.windowHeight);
        image.draw(CaveWars.windowWidth/2 - scale * image.getWidth()/2, CaveWars.windowHeight/2 - scale * image.getHeight()/2, scale);
        
        for(MenuButton button : buttonList)
		{
        		button.render(gc, sbg, graphics);
		}
    }

}
