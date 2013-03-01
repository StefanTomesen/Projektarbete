package cavewars;

public class CollisionBox
{
	float x;
	float y;
	
	float width;
	float height;
	
	public CollisionBox(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public CollisionBox(Renderable object)
	{
		this(object.xPosition, object.yPosition, object.width, object.height);
	}
	
	public boolean collidesWith(CollisionBox cb, Vector movementVector)
	{
		float x = this.x + movementVector.x;
		float y = this.y + movementVector.y;
		
		boolean horizontalCollision = (x < cb.x + cb.width) && (cb.x < x + width);
		boolean verticalCollision = (y < cb.y + cb.height) && (cb.y < y + height);
		
		return horizontalCollision && verticalCollision;
	}
	
	public Vector getMotionVectorAfterCollision(CollisionBox otherObject, Vector motionVector)
	{
		float x = motionVector.x;
		float y = motionVector.y;
		
		if(this.collidesWith(otherObject, new Vector(motionVector.x, 0)))
		{
			x = 0;
		}
		if(this.collidesWith(otherObject, new Vector(0, motionVector.y)))
		{
			y = 0;
		}
		
		return new Vector(x, y);
	}
}