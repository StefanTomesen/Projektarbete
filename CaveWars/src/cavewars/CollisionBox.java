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
	
	public Vector getMotionVectorAfterCollision(CollisionBox cb, Vector motionVector)
	{
		float x = motionVector.x;
		float y = motionVector.y;
		System.out.println("Before... X: " + x + ", Y: " + y);
		
		if(this.collidesWith(cb, new Vector(motionVector.x, 0)))
		{
			x = 0;
			System.out.println("x");
		}
		if(this.collidesWith(cb, new Vector(0, motionVector.y)))
		{
			y = 0;
			System.out.println("y");
		}
		
		// If we know that the entity has collided, but neither axis alone is responsible for the
		// collision, it means the we're at a corner and both axes are responsible.
		if(x != 0 && y != 0) 
		{
			//x = 0;
			//y = 0;
			System.out.println("both");
			System.out.println("Really? :" + this.collidesWith(cb, new Vector(0, motionVector.y)) + ", motionY = " + motionVector.y);
		}
		
		System.out.println("After... X: " + x + ", Y: " + y);
		return new Vector(x, y);
	}
	
	public void print()
	{
		System.out.println("Collision Bounds: x, " + x + " -> " + (x + width) + " y, " + y + " -> " + (y + height));
	}
}