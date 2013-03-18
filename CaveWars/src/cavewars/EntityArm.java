package cavewars;

class EntityArm extends Entity
{
	World world2;
	
	public static float offsetX = 0.31F;
	public static float offsetY = -0.18F;
	public static float centerOffsetX = 0.11F;
	public static float centerOffsetY = 0.65F;
	
	public EntityArm(World world, int id, float x, float y, int team)
	{
		super(world, id, null, x + offsetX, y + offsetY, 0.75F, ((team == EntityPlayer.RED_TEAM) ? ImageLoader.redArm : ImageLoader.yellowArm), 2, 1);
		world2 = world;
	}

	@Override
	public void updateRotation()
	{
		if(!world.isServer && world.localPlayer == parent)
		{
			Position cursorWorldPosition = world.getWorldPosition((int)world.cursorPosition.x, (int)world.cursorPosition.y, CaveWars.windowWidth, CaveWars.windowHeight);
			float cursorPosY = cursorWorldPosition.y - centerOffsetY;
			float cursorPosX = cursorWorldPosition.x - centerOffsetX;
			
			rotation = (float)(-90 + Math.atan2(yPosition - cursorPosY, xPosition - cursorPosX) * (180 / Math.PI));
		}
	}

	@Override
	public float getVerticalAcceleration()
	{
		return 0;
	}

	@Override
	public void hasCollided(int id, boolean onGround)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void resetCollisionStatus()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int getSpriteXIndex()
	{
		int playerDirection = ((EntityPlayer)parent).direction;
		return (playerDirection == EntityPlayer.RIGHT) ? 0 : 1;
	}

	@Override
	public int getSpriteYIndex()
	{
		return 0;
	}

	@Override
	public void updateAnimation(long delta)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
