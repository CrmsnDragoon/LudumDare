package com.crmsndragoon.LD26.level;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.crmsndragoon.LD26.PhysicsManager;
import com.crmsndragoon.LD26.entities.Entity;
import com.crmsndragoon.LD26.entities.EntityManager;
import com.crmsndragoon.LD26.entities.Player;
import com.crmsndragoon.LD26.entities.Tile;

public class Level implements Disposable {
	private Stage m_stage;
	private Stage m_background;
	private Stage m_foreground;
	private InputMultiplexer m_inputMP;
	private Array<TextureRegion> m_tileTypes;
	private EntityManager m_entMgr;
	private PhysicsManager m_physMgr;
	private TextureRegion m_playerTex;

	public Level(EntityManager entityManager, PhysicsManager physicsManager) {
		m_stage = new Stage();
		m_background = new Stage();
		m_foreground = new Stage();
		m_inputMP = new InputMultiplexer();
		//m_inputMP.addProcessor(m_stage);
		//m_inputMP.addProcessor(m_background);
		//m_inputMP.addProcessor(m_foreground);
		m_tileTypes = new Array<TextureRegion>();
		m_entMgr = entityManager;
		m_physMgr = physicsManager;
	}

	public void addTileType(Texture tex, int x, int y) {
		if (m_tileTypes.size != 16) {
			TextureRegion region = new TextureRegion(tex, x, y, 4, 4);
			m_tileTypes.add(region);
		}
	}

	public void setPlayerTexture(TextureRegion playerTexture) {
		m_playerTex = playerTexture;
	}
	
	public InputMultiplexer getInputMultiplexer() {
		return m_inputMP;
	}

	public void draw() {
		
		m_background.draw();
		m_stage.draw();
		m_physMgr.render();
		m_foreground.draw();
	}

	@Override
	public void dispose() {
		m_tileTypes.clear();
		m_inputMP.clear();
		m_stage.dispose();
		m_background.dispose();
		m_foreground.dispose();

	}

	public enum TILE_TYPES {
		AIR, WALL, START, GOAL
	}

	public void loadLvlImage(FileHandle file) throws RuntimeException {
		try {
			BufferedImage imgBuffer = ImageIO.read(file.read());
			int[][] lvlImage = imageToIntArray(imgBuffer);

			for (int i = 0; i < imgBuffer.getWidth(); ++i) {
				for (int j = 0; j < imgBuffer.getHeight(); ++j) {
					// strip alpha, use it for reading in dynamic objects like
					// buttons
					//Gdx.app.log("IMAGE LOAD AT:"+i+","+j,String.format("%x",lvlImage[i][j]));
					switch (lvlImage[i][j] & 0xFFFFFF) {
					case 0x000000: {
						//Wall
						//spawn wall tile
						Tile newTile = m_entMgr.createTile(i, j, m_tileTypes.get(TILE_TYPES.WALL.ordinal()));
						m_physMgr.registerStaticEntity(newTile);
						Gdx.app.log("ENTITY CREATED AT: "+i+", "+j, "Wall");
						}
						break;
					case 0xFF0000: {
						//player start
						//record and spawn player
						Entity player = m_entMgr.createPlayer(i+.1f, j, m_playerTex);
						//this is done in EntityManager.registerEntities
						player.setColor(1, 1, 0, 1);
						m_physMgr.registerEntity(player);
						Gdx.app.log("ENTITY CREATED AT: "+i+", "+j, "Player");
						
						m_entMgr.createTile(i, j, m_tileTypes.get(TILE_TYPES.START.ordinal()));
						//m_physMgr.registerStaticEntity(newTile);
						Gdx.app.log("ENTITY CREATED AT: "+i+", "+j, "Start");
						}
						break;
					case 0xFFFF00:
						//player finish
						//record and spawn goal tile
						m_entMgr.createTile(i, j, m_tileTypes.get(TILE_TYPES.GOAL.ordinal()));
						//m_physMgr.registerStaticEntity(newTile);
						Gdx.app.log("ENTITY CREATED AT: "+i+", "+j, "Goal");
						break;
					case 0xFF00FF:
						//Hazard
						//if player touches this, spawn player at current spawnLoc.
						break;
					case 0x00FF00:
						//switch
						//spawn phys object that triggers a callback marked with the pixel's
						//alpha.
						break;
					case 0x00F000:
						//switch target
						//spawn kinematic phys object behind a wall and add a listener for
						//the alpha of the pixel
						//if switch
						break;
					case 0xFFFFFF:
						//air
						// Do nothing! It's air!
						break;
					case 0x0000FF:
						//enemy
						break;
					default:
						break;
					}
				}
			}
			m_entMgr.registerTiles(m_stage);
			m_entMgr.registerEntities(m_stage);
			Player player = m_entMgr.getPlayer();
			if (player != null) {
				m_inputMP.addProcessor(player);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}

	private static int[][] imageToIntArray(BufferedImage image) {
		//Remeber: LibGdx has the origin at bottom left, unlike EVERY GRAPHICS PACKAGE EVER!
		final byte[] pixels = ((DataBufferByte) image.getRaster()
				.getDataBuffer()).getData();
		final int width = image.getWidth();
		final int height = image.getHeight();
		final boolean hasAlphaChannel = image.getAlphaRaster() != null;

		int[][] result = new int[width][height];
		if (hasAlphaChannel) {
			final int pixelLength = 4;
			for (int pixel = 0, row = height -1, col = 0; pixel < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
				argb += ((int) pixels[pixel + 1] & 0xff); // blue
				argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
				argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
				result[col][row] = argb;
				col++;
				if (col == width) {
					col = 0;
					row--;
				}
			}
		} else {
			final int pixelLength = 3;
			for (int pixel = 0, row = height -1, col = 0; pixel < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += -16777216; // 255 alpha
				argb += ((int) pixels[pixel] & 0xff); // blue
				argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
				argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
				result[col][row] = argb;
				col++;
				if (col == width) {
					col = 0;
					row--;
				}
			}
		}

		return result;
	}
}
