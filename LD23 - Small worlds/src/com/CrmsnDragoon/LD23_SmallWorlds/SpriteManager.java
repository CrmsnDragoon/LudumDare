package com.CrmsnDragoon.LD23_SmallWorlds;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SpriteManager {
	Player player;
	Vector<Sprite> shots;
	Vector<Sprite> enemys;
	TileManager tileManager;
	private Texture playerTex;
	
	public SpriteManager(int vbo, int ibo)
	{
		loadtextures();
		shots = new Vector<Sprite>();
		enemys = new Vector<Sprite>();
		player = new Player(playerTex, 400, 400, 0);
	}
	
	private void loadtextures() {
		try {
			playerTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/sprites/player/player_static.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changeLevel(BufferedImage level)
	{
		player.TeleportTo(tileManager.getStartLoc());
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
