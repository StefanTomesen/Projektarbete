package cavewars;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class World
{
	public TileGrid tileGrid;
	
	public EntityFactory entityFactory;
	public ArrayList<Entity> entityList = new ArrayList();
	public Entity player;
	
	public Camera camera;
	
	public Animation animation = new Animation(new SpriteSheet(new Image("./resources/spritesheet_player_red.png"), 280, 500), 1000);
	
	public World() throws SlickException
	{
		entityFactory = new EntityFactory(this);
		entityFactory.createPlayer(0, 0, 0, EntityPlayer.RED_TEAM);
		tileGrid = new TileGrid(100, 60);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException 
	{
		float gameScale = gc.getHeight() / tileGrid.ySize;
		
		//Image currentImage = animation.getCurrentFrame();
		//currentImage.draw(20, 20, 0.2F);
		if(player != null)
		{
			Animation animation = (Animation)player.renderable;
			Image currentImage = animation.getCurrentFrame();
			currentImage.draw(player.xPosition * gameScale, player.yPosition * gameScale, 1000);
		}
		
		/*for(Entity entity : entityList)
		{	
			Renderable renderable = entity.renderable;
			if(renderable instanceof Image)
			{
				Image image = (Image)renderable;
				image.draw(entity.xPosition * gameScale, entity.yPosition * gameScale, 1);
			}
			
		}*/
	}
}
