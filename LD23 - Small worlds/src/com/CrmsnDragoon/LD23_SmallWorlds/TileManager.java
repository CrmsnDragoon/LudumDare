package com.CrmsnDragoon.LD23_SmallWorlds;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TileManager {
	Tile[][] tiles;
	SpriteManager spriteManager;
	@SuppressWarnings("unused")
	private Vector3f startLoc;
	private Texture wallTex,backWallTex/*,doorTex,buttonTex*/;
	private int levelWidth;
	private int levelHeight;

	public TileManager(int vbo, int ibo, SpriteManager spriteManager) {
		tiles = new Tile[512][512];
		this.spriteManager = spriteManager;
		loadTextures();
	}

	private void loadTextures() {
		try {
			wallTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/tiles/walls/wall.png"));
			backWallTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/tiles/walls/backWall1.png"));
			//doorTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/tiles/doors/door.png"));
			//buttonTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/tiles/buttons/button.png"));	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void Update(int deltaTime) {
		// TODO Auto-generated method stub
		
	}

	public void Render() {
		for (int i = 0; i<levelWidth;i++)
		{
			for (int j=0;j<levelHeight;j++)
			{
				tiles[i][j].Render();
			}
		}
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
		levelWidth = level.getWidth();
		levelHeight = level.getHeight();
		for (int i = 0; i<level.getWidth();i++)
		{
			for (int j=0;j<level.getHeight();j++)
			{
				switch(level.getRGB(i, j))
				{
				case 0xFF000000: //wall
					tiles[i][j] = new Wall(wallTex,i*16,j*16,0);
					break;
				case 0xFFB5B5B5:
					tiles[i][j] = new BackWall(backWallTex,i*16,j*16,1);
					break;
				case 0xFF00FF00://start point
					startLoc = new Vector3f(i,j,0);
					//add back wall
					tiles[i][j] = new BackWall(backWallTex,i*16,j*16,1);
					break;
				case 0xFFFF0000:
					tiles[i][j] = new EscapeTile(backWallTex,i*16,j*16,1);
					break;
				}
			}
		}
		spriteManager.changeLevel(level);
		
	}

	public Vector2f getStartLoc() {
		return new Vector2f();
	}


}
