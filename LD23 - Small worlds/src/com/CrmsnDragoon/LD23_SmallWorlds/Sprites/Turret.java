package com.CrmsnDragoon.LD23_SmallWorlds.Sprites;

import org.newdawn.slick.opengl.Texture;

import com.CrmsnDragoon.LD23_SmallWorlds.TileManager;

public class Turret extends Sprite {

	public Turret(Texture frame1Tex, Texture frame2Tex, Texture Frame3Tex, Texture frame4Tex, float x, float y, float z, int updateSpeed) {
		super(frame1Tex, x, y, z);
	}
	
	
	public void Update(TileManager tileManager,int deltaTime, Player player)
	{
		if (player.getLoc().y >= this.getLoc().y -8 &&player.getLoc().y <= this.getLoc().y+16)
		{
			
		}
	}
}
