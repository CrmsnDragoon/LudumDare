package com.CrmsnDragoon.LD23_SmallWorlds;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.CrmsnDragoon.LD23_SmallWorlds.Sprites.Player;
import com.CrmsnDragoon.LD23_SmallWorlds.Sprites.Shot;
import com.CrmsnDragoon.LD23_SmallWorlds.Sprites.Sprite;

public class SpriteManager {
	Player player;
	Vector<Shot> shots;
	Vector<Sprite> enemys;
	TileManager tileManager;
	private Texture playerTex1, playerTex2, playerTex3;
	//private Texture turretTex1;
	
	public SpriteManager(int vbo, int ibo)
	{
		loadtextures();
		shots = new Vector<Shot>();
		enemys = new Vector<Sprite>();
		player = new Player(playerTex1, 400, 400, 0, playerTex2, playerTex3);
	}
	
	private void loadtextures() {
		try {
			playerTex1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/sprites/player/player_static1.png"));
			playerTex2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/sprites/player/player_static2.png"));
			playerTex3 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/sprites/player/player_static3.png"));
			//turretTex1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/sprites/enemy/turret_static1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changeLevel(BufferedImage level)
	{
		//spawn items and enemies
		for(int i=0;i<level.getWidth();i++)
		{
			for(int j=0;j<level.getHeight();j++)
			{
				
			}
		}
		player.teleportTo(tileManager.getStartLoc());
	}
	
	public void setTileManager(TileManager tileManager) {
		this.tileManager = tileManager;
	}

	public void Update(int deltaTime)
	{
		player.Update(tileManager,enemys,deltaTime);
		for (int i = 0; i<shots.size();i++)
		{
			shots.elementAt(i).Update(tileManager, enemys, deltaTime);
		}
	}

	public void Render() {
		player.Render();
		for (int i = 0; i<shots.size();i++)
		{
			shots.elementAt(i).Render();
		}
	}

}
