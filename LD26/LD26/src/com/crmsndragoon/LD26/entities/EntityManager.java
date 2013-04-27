package com.crmsndragoon.LD26.entities;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.crmsndragoon.LD26.PhysicsManager;

public class EntityManager implements Disposable{
	private Vector<Tile> m_tiles;
	private Vector<Entity> m_entities;
	private Player m_player;
	
	public EntityManager() {
		m_tiles = new Vector<Tile>();
		m_entities = new Vector<Entity>();
		m_player = null;
	}
	
	public Entity createPlayer(float x, float y, TextureRegion textureRegion) {
		m_player = new Player(x * PhysicsManager.BOX_TO_WORLD, y* PhysicsManager.BOX_TO_WORLD, new Sprite(textureRegion));
		return m_player;
	}
	
	public Tile createTile(float x, float y, TextureRegion textureRegion) {
		Tile newTile = new Tile(x * PhysicsManager.BOX_TO_WORLD, y* PhysicsManager.BOX_TO_WORLD, new Sprite(textureRegion));
		m_tiles.add(newTile);
		return newTile;
	}
	
	public void update(float dt) {
		for(int i = 0; i< m_entities.size();++i) {
			m_entities.get(i).update(dt);
		}
		for(int i = 0; i< m_tiles.size();++i) {
			m_tiles.get(i).update(dt);
		}
		m_player.update(dt);
	}
	
	public void registerEntities(Stage stage) {
				if (m_player != null) {
			Gdx.app.log("ENTITY REGISTERED", "PLAYER");
			stage.addActor(m_player);
			stage.setKeyboardFocus(m_player);
		}
		for(int i = 0; i< m_entities.size();++i) {
			stage.addActor(m_entities.get(i));
		}		

	}
	public void registerTiles(Stage stage) {
		for(int i = 0; i< m_tiles.size();++i) {
			stage.addActor(m_tiles.get(i));
		}		
	}

	@Override
	public void dispose() {
		m_tiles.clear();
		m_entities.clear();
	}
}
