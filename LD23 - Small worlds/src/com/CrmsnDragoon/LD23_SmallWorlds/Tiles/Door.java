package com.CrmsnDragoon.LD23_SmallWorlds.Tiles;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;


public class Door extends Tile {
	private boolean open;

	private float timeToOpen;
	private Vector3f target;
	public Door(Texture texture, float x, float y, float z) {
		super(texture, x, y, z);
		open = false;
		target = new Vector3f();
		timeToOpen = 8;
	}
	public void Update(int deltaTime)
	{
		if (open)//doors only move to the right, up, or out.
		{
			if (this.getLoc().x<target.x)
			{
				this.setLoc(this.getLoc().x+((timeToOpen/128.0f)*deltaTime), this.getLoc().y, this.getLoc().z);
			}else
				if (this.getLoc().y < target.y)
				{
					this.setLoc(this.getLoc().x, this.getLoc().y+((timeToOpen/64.0f)*deltaTime), this.getLoc().z);
				}else
					if (this.getLoc().z < target.z)
					{
						this.setLoc(this.getLoc().x, this.getLoc().y, this.getLoc().z+((timeToOpen/32.0f)*deltaTime));
					}
		}
	}
	public void ToggleState() {
		if (open){open = false;}else{open = true;}
	}

}
