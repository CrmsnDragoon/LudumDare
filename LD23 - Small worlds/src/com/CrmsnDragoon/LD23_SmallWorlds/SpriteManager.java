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
import com.CrmsnDragoon.LD23_SmallWorlds.Sprites.Turret;

public class SpriteManager {
	Player player;
	Vector<Shot> shots;
	Vector<Sprite> enemys;
	TileManager tileManager;
	private Texture playerTex1, playerTex2, playerTex3;
	private Texture turretTex1,turretTex2,turretTex3,turretTex4;
	
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
			turretTex1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/sprites/turret/turret1.png"));
			turretTex2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/sprites/turret/turret1.png"));
			turretTex3 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/sprites/turret/turret1.png"));
			turretTex4 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/sprites/turret/turret1.png"));
			
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
				switch (level.getRGB(i, j))
				{
				case 0xF0F3F3F3:
					enemys.add(new Turret(turretTex1,turretTex2,turretTex3,turretTex4, i*8,j*8,0,100));
					break;
				default:
					break;
				}
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
