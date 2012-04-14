package com.CrmsnDragoon.TwentyTwo;

public abstract class Entity {
	int ID;
	double x;
	double y;
	
	public Entity(int startingID)
	{
		this.ID = startingID;
	}
	
	public Entity(int startingID,double startingX, double startingY)
	{
		this.ID = startingID;
		x = startingX;
		y = startingY;
	}

	public abstract void Update();
	public abstract void render();
}
