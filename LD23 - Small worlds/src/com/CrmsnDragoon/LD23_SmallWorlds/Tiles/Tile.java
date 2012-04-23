package com.CrmsnDragoon.LD23_SmallWorlds.Tiles;

import org.newdawn.slick.opengl.Texture;

import com.CrmsnDragoon.LD23_SmallWorlds.Entity;
import com.CrmsnDragoon.LD23_SmallWorlds.Sprites.Player;

public class Tile extends Entity {

	public Tile(Texture texture, float x, float y, float z) {
		super(texture, x, y, z);
	}

	public void Update(int deltaTime) {
		//to be overwritten by classes that need it
		
	}

	public boolean collisionEffect(Player player) {
		//to be overwritten by tiles that need an effect to trigger
		return false;
	}

}
