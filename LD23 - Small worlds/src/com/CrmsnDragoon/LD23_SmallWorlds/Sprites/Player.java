package com.CrmsnDragoon.LD23_SmallWorlds.Sprites;

import java.util.Vector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import com.CrmsnDragoon.LD23_SmallWorlds.TileManager;
import com.CrmsnDragoon.LD23_SmallWorlds.Tiles.Tile;

public class Player extends Sprite {
	int health;
	Vector3f velocity;
	boolean inAir, jumpedLastFrame;
	Texture health1, health2, health3;
	private boolean nextLevel;

	public Player(Texture health1, float x, float y, float z, Texture health2,
			Texture health3) {
		super(health3, x, y, z);
		velocity = new Vector3f(0, 0, 0);
		inAir = false;
		jumpedLastFrame = false;
		health = 3;
		this.health1 = health1;
		this.health2 = health2;
		this.health3 = health3;
	}

	public void Update(TileManager tileManager, Vector<Sprite> enemys,
			int deltaTime) {
		pollInput();
		updatePosAndColisionDetection(tileManager.getTiles(), deltaTime);
		if (nextLevel) {
			tileManager.incrementLevel();
			nextLevel = false;
		}
	}

	private void updatePosAndColisionDetection(Tile[][] tiles, int deltaTime) {
		Vector3f velocityToBeAdded = new Vector3f(velocity.x,
				velocity.y - 0.35f, velocity.z);

		velocityToBeAdded.scale(deltaTime / 3.0f);
		velocity.scale(0.5f);

		if (this.loc.y + velocityToBeAdded.y < 0) {
			this.loc.y = 0;
			velocityToBeAdded.y = 0;
		}
		if (this.loc.x + velocityToBeAdded.x < -0.5f) {
			this.loc.x = -0.5f;
			velocityToBeAdded.x = 0;
		}
		if (this.loc.y + velocityToBeAdded.y > 15 * 8) {
			this.loc.y = (15 * 8);
			velocityToBeAdded.y = 0.00000001f;
		}
		if (this.loc.x + velocityToBeAdded.x > (15 * 8) + 0.5f) {
			this.loc.x = (15 * 8) + 0.5f;
			velocityToBeAdded.x = 0;
		}
		
		velocityToBeAdded.scale(0.125f);
		for (int k = 0; k < 4; k++) {
			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 16; j++) {
					velocityToBeAdded = collisionDetection(tiles[i][j],
							velocityToBeAdded);
				}
			}
			velocityToBeAdded.scale(2);
		}
			teleportTo(Vector3f.add(this.loc, velocityToBeAdded, null));
		
		if (velocityToBeAdded.y != 0) {
			inAir = true;
		} else {
			inAir = false;
		}
	}

	private Vector3f collisionDetection(Tile tile, Vector3f add) {
		boolean bottomLeft = isPointInTile(tile, new Vector3f(this.getLoc().x+add.x,this.getLoc().y+add.y,this.getLoc().z+add.z));
		boolean bottomRight = isPointInTile(tile, new Vector3f(this.getLoc().x+add.x+7.5f,this.getLoc().y+add.y,this.getLoc().z+add.z));
		boolean topLeft = isPointInTile(tile, new Vector3f(this.getLoc().x+add.x,this.getLoc().y+add.y+7.5f,this.getLoc().z+add.z));
		boolean topRight = isPointInTile(tile, new Vector3f(this.getLoc().x+add.x+7.5f,this.getLoc().y+add.y+7.5f,this.getLoc().z+add.z));
		boolean middleTop = isPointInTile(tile, new Vector3f(this.getLoc().x+add.x+3.25f,this.getLoc().y+add.y+7.5f,this.getLoc().z+add.z));
		if(bottomLeft)
		{
			System.out.print("Collision Detected! it's to the right!\n");
			if(!tile.collisionEffect(this))//if the tile doesn't stop collision resolution
			{
				add.x += add.x*-1;
			}
			System.out.print("Set add to X:"+ add.x+", Y:"+add.y+", Z:"+add.z+"\n");
		}
		if(bottomRight)
		{
			System.out.print("Collision Detected! It's to the left!\n");
			if(!tile.collisionEffect(this))//if the tile doesn't stop collision resolution
			{
				add.x += tile.getLoc().x+.5f-this.getLoc().x;
			}
			System.out.print("Set add to X:"+ add.x+", Y:"+add.y+", Z:"+add.z+"\n");
		}
		if (topLeft)
		{
			System.out.print("Collision Detected! It's from below!\n");
			if(!tile.collisionEffect(this))//if the tile doesn't stop collision resolution
			{
				add.y += add.y*-1;
			}
			System.out.print("Set add to X:"+ add.x+", Y:"+add.y+", Z:"+add.z+"\n");
		}
		if (middleTop)
			//congratulations, you have successfully detected a collision!
			{
				System.out.print("Collision Detected! It's from below, on both sides!\n");
				if(!tile.collisionEffect(this))//if the tile doesn't stop collision resolution
				{
					add.y += add.y*-1f;
				}
				System.out.print("Set add to X:"+ add.x+", Y:"+add.y+", Z:"+add.z+"\n");
			}else
		if (topRight)
		//congratulations, you have successfully detected a collision!
		{
			System.out.print("Collision Detected! It's from below!\n");
			if(!tile.collisionEffect(this))//if the tile doesn't stop collision resolution
			{
				add.y += add.y*-1;
			}
			System.out.print("Set add to X:"+ add.x+", Y:"+add.y+", Z:"+add.z+"\n");
		}
		
		
		return add;
	}

	private boolean isPointInTile(Tile tile, Vector3f point) {
		if ((point.z == tile.getLoc().z)) 
			//if the tile is on the same plane as the point
		{
			if ((point.x >= tile.getLoc().x)
					&& (point.x <= tile.getLoc().x + 8))
			//and if the point is within the tile's x space
			{
				if ((point.y <= tile.getLoc().y + 8)
						&& (point.y >= tile.getLoc().y))
				//and it's within the tile's y space
				{
					//it's in the tile!
					return true;
				}
			}
		}
		return false;
	}
	
	private void pollInput() {

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if (!inAir && !jumpedLastFrame) {
				// JUMP LEETLE MAN! JUMP!
				velocity.y += 3.5f;
			}
			jumpedLastFrame = true;
		} else {
			jumpedLastFrame = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
				velocity.x += -0.1f;
			} else {
				velocity.x += -0.2f;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
				velocity.x += 0.1f;
			} else {
				velocity.x += 0.2f;
			}
		}
	}

	public void teleportTo(Vector3f vector3f) {
		setLoc(vector3f);
	}

	public void WinLevel() {
		nextLevel = true;
		
	}

}
