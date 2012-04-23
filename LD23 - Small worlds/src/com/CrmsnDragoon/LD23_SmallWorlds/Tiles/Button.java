package com.CrmsnDragoon.LD23_SmallWorlds.Tiles;

import java.util.Vector;

import org.newdawn.slick.opengl.Texture;



public class Button extends Tile {
private Vector<Door> triggers;
private boolean pressed = false;
private Texture stateUpTex, stateDownTex;
	public Button(Texture stateUpTex,Texture stateDownTex, float x, float y, float z) {
		super(stateUpTex, x, y, z);
		this.stateUpTex = stateUpTex;
		this.stateDownTex = stateDownTex;
	}

	public void Update(int DeltaTime)
	{
		if (pressed)
		{
			this.setTexture(stateDownTex);
		}
		else
		{
			this.setTexture(stateUpTex);
		}
	}
	
	public void setTrigger(Vector<Door> redDoorCache) {
		triggers = redDoorCache;
	}
	
	public boolean collisionEffect()
	{
		if (!pressed) {
			for (int i = 0; i < triggers.size(); i++) {
				triggers.get(i).ToggleState();
			}
		}
		return true;
	}

}
