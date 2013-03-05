package cavewars;

public class EntityTile extends Entity
{
	public int tileID;
	
	public boolean hasLanded = false;
	
	public EntityTile(int entityID, float xPosition, float yPosition, int tileID)
	{
		super(entityID, xPosition, yPosition, 1.0F, ImageLoader.tiles, Tile.numberOfTileColumns, Tile.numberOfTileRows);
		this.tileID = tileID;
	}

	@Override
	public float getRotation()
	{
		return 0;
	}

	@Override
	public float getVerticalAcceleration()
	{
		if(hasLanded)
		{
			return 0;
		}
		return gravity;
	}

	@Override
	public void hasCollided(int id, boolean onGround)
	{
		hasLanded = true;
	}

	@Override
	public void resetCollisionStatus()
	{
		return;
	}

	@Override
	public int getSpriteXIndex()
	{
		return Tile.getColumn(tileID);
	}

	@Override
	public int getSpriteYIndex()
	{
		return Tile.getRow(tileID);
	}

	@Override
	public void updateAnimation(long delta)
	{
		return;
	}
}
