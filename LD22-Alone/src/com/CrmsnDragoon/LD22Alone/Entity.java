package com.CrmsnDragoon.LD22Alone;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Entity {
private float x,y;
private Image texture;
private int ENTITYID;
public Entity(float newX, float newY, int id, String imagePath)
{
	x=newX;
	y=newY;
	ENTITYID = id;
	try {
		texture = new Image(imagePath);
	} catch (SlickException e) {
		System.out.print("Error Loading texture for Entity number: "+ ENTITYID);
	}
}

public void Render()
{
	texture.draw(x, y, 1);
}

public void Update(Me me) {
	// TODO Auto-generated method stub
	
}
}
