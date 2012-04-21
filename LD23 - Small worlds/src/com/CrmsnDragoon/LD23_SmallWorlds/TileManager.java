package com.CrmsnDragoon.LD23_SmallWorlds;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class TileManager {
	Tile[][] tiles;
	SpriteManager spriteManager;
	@SuppressWarnings("unused")
	private Vector3f startLoc;

	public TileManager(int vbo, int ibo, SpriteManager spriteManager) {
		tiles = new Tile[512][512];
		this.spriteManager = spriteManager;
	}

	public void Update(int deltaTime) {
		// TODO Auto-generated method stub
		
	}

	public void Render() {
		// TODO Auto-generated method stub
		
	}

	public void setLevel(String levelPath) {
		BufferedImage level;
		try {
			level =  ImageIO.read(new File(levelPath));
		} catch (IOException e) {
			System.out.print("Level load failed for path: "+ levelPath);
			e.printStackTrace();
			return;
			
		}
		
		for (int i = 0; i<level.getWidth();i++)
		{
			for (int j=0;j<level.getHeight();j++)
			{
				switch(level.getRGB(i, j))
				{
				case 0xFFFFFFFF: //wall
					
					break;
				case 0xFF00FF00://start point
					setStartLoc(new Vector3f(i,j,0));
					//add back wall
					break;
				}
			}
		}
		spriteManager.changeLevel(level);
		
	}

	public Vector2f getStartLoc() {
		return new Vector2f();
	}

	public void setStartLoc(Vector3f startLoc) {
		this.startLoc = startLoc;
	}

}
