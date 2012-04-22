package com.CrmsnDragoon.LD23_SmallWorlds;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.CrmsnDragoon.LD23_SmallWorlds.Tiles.AnimBlock;
import com.CrmsnDragoon.LD23_SmallWorlds.Tiles.BackWall;
import com.CrmsnDragoon.LD23_SmallWorlds.Tiles.KillTile;
import com.CrmsnDragoon.LD23_SmallWorlds.Tiles.SkyTile;
import com.CrmsnDragoon.LD23_SmallWorlds.Tiles.Tile;
import com.CrmsnDragoon.LD23_SmallWorlds.Tiles.Wall;

public class TileManager {
	Tile[][] tiles;
	SpriteManager spriteManager;
	private Vector3f startLoc;
	private Texture wallTex,backWallTex,firepitTex;
	private Texture doorTex,buttonTex, hintUpTex;
	private Vector<Texture> skyTex;
	private int levelWidth;
	private int levelHeight;
	private Random RNG;
	private Texture goalTex1, goalTex2;
	
	public TileManager(int vbo, int ibo, SpriteManager spriteManager) {
		tiles = new Tile[512][512];
		this.spriteManager = spriteManager;
		startLoc = new Vector3f(0,0,0);
		skyTex = new Vector<Texture>();
		RNG = new Random();
		loadTextures();
	}

	private void loadTextures() {
		try {
			wallTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/tiles/walls/wall.png"));
			backWallTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/tiles/walls/backWall1.png"));
			hintUpTex = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("assets/tiles/walls/hintUp.png"));
			
			doorTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/tiles/doors/redDoor.png"));
			buttonTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/tiles/buttons/buttonUp.png"));
			firepitTex = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("assets/tiles/hazards/blueFirePit.png"));
			goalTex1 = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("assets/tiles/goal/goal1.png"));
			goalTex2 = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("assets/tiles/goal/goal2.png"));
			
			skyTex.add(TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("assets/tiles/sky/blueSky1.png")));
			skyTex.add(TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("assets/tiles/sky/blueSky2.png")));
			skyTex.add(TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("assets/tiles/sky/blueSky3.png")));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void Update(int deltaTime) {
		for (int i = 0; i<levelWidth;i++)
		{
			for (int j=0;j<levelHeight;j++)
			{
				tiles[i][j].Update(deltaTime);
			}
		}
		
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
		Vector2f newStartLoc = new Vector2f();
		for (int i = 0; i<level.getWidth();i++)
		{
			for (int j=0;j<level.getHeight();j++)
			{
				switch(level.getRGB(i,level.getHeight()- j-1))
				{
				case 0xFF000000: //wall
					tiles[i][j] = new Wall(wallTex,i*8,j*8,0);
					break;
				case 0xFFFFFFFF: //clear sky
					tiles[i][j] = new SkyTile(skyTex.get(RNG.nextInt(3)),i*8,j*8,1);
				case 0xFFB5B5B5:
					tiles[i][j] = new BackWall(backWallTex,i*8,j*8,1);
					break;
				case 0xFF00FF00://start point
					newStartLoc .x = i*8;
					newStartLoc.y = j*8;
					System.out.print("Changed StartLoc to: "+newStartLoc.x+", "+ newStartLoc.y+"\n");
					//add back wall
					tiles[i][j] = new BackWall(backWallTex,i*8,j*8,1);
					break;
				case 0xFF00FFF0://up arrow tile
					tiles[i][j] = new BackWall(hintUpTex,i*8,j*8,1);
					break;
				case 0xFFFF0000:
					tiles[i][j] = new AnimBlock(goalTex1,goalTex2,i*8,j*8,1);
					break;
				case 0xFF0000FF:
					tiles[i][j] = new KillTile(firepitTex,i*8,j*8,0);
				default:
					tiles[i][j] = new BackWall(backWallTex,i*8,j*8,1);
				}
			}
		}
		
		startLoc = new Vector3f(newStartLoc.x,newStartLoc.y,0);
		spriteManager.changeLevel(level);
		
	}

	public Vector3f getStartLoc() {
		return startLoc;
	}

	public Tile[][] getTiles() {
		return tiles;
	}


}
