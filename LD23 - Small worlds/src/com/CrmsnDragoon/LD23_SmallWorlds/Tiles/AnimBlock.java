package com.CrmsnDragoon.LD23_SmallWorlds.Tiles;

import org.newdawn.slick.opengl.Texture;

import com.CrmsnDragoon.LD23_SmallWorlds.Sprites.Player;


public class AnimBlock extends Tile {

	private Texture frame1, frame2;
	private int frame, time;
	public AnimBlock(Texture frame1,Texture frame2, float x, float y, float z) {
		super(frame1, x, y, z);
		this.frame1 = frame1;
		this.frame2 = frame2;
		this.frame = 1;
		this.time = 0;
	}
	public void Update(int deltaTime)
	{
		if (time >=50) {
			if (frame == 1) {
				this.setTexture(frame1);
				frame++;
			} else {
				this.setTexture(frame2);
				frame--;
			}
			time -= 50;
		}
		else
		{
			time+=deltaTime;
		}
		
	}
	public boolean collisionEffect(Player player) {
		player.WinLevel();
		return true;
	}
}
