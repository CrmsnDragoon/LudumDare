package com.CrmsnDragoon.LD23_SmallWorlds;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class Player extends Sprite {
	int health;

	public Player(Texture playerTex, float x, float y, float z) {
		super(playerTex, x, y, z);
		health = 0;
	}

	public void Update(TileManager tileManager, int deltaTime) {
		pollInput();
		updateAnimFrame();
	}

	private void updateAnimFrame() {
		// TODO Auto-generated method stub
		
	}

	private void pollInput() {
		
	}

	public void TeleportTo(Vector2f Loc) {
		this.loc.x = Loc.x;
		this.loc.y = Loc.y;	
	}

}
