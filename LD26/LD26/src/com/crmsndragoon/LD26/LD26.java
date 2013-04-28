package com.crmsndragoon.LD26;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.assets.*;
import com.crmsndragoon.LD26.entities.EntityManager;
import com.crmsndragoon.LD26.gui.GuiManager;
import com.crmsndragoon.LD26.level.Level;

public class LD26 implements ApplicationListener {
	private Texture m_texture;
	private AssetManager m_assetMgr;
	private InputMultiplexer m_globalInputMP;
	private GuiManager m_guiMgr;
	private Level m_level;
	private PhysicsManager m_physMgr;
	private EntityManager m_entMgr;
	private int m_width;
	private int m_height;
	
	@Override
	public void create() {	
		m_assetMgr = new AssetManager();
		m_assetMgr.load("fonts/font.fnt", BitmapFont.class);
		m_assetMgr.load("textures/4Tone.png",Texture.class);
		m_assetMgr.load("textures/player.png",Texture.class);
		
		//busy work
		m_width = 800;
		m_height = 600;
		m_guiMgr = new GuiManager();
		m_physMgr = new PhysicsManager(-9.8f);
		m_entMgr = new EntityManager();
		m_level = new Level(m_entMgr,m_physMgr);
		
		
		m_globalInputMP = new InputMultiplexer();
		//m_globalInputMP.addProcessor(m_guiMgr.getStage());
		//m_globalInputMP.addProcessor(m_level.getInputMultiplexer());
		m_assetMgr.finishLoading();
		
		
		//rather have getters and setters than have a public field
		m_guiMgr.setFont((BitmapFont) m_assetMgr.get("fonts/font.fnt"));
		m_texture = m_assetMgr.get("textures/4Tone.png");
		m_level.setPlayerTexture(new TextureRegion((Texture)m_assetMgr.get("textures/player.png"),0,0,4,4));
		m_level.addTileType(m_texture, 0, 4);
		m_level.addTileType(m_texture, 0, 0);
		m_level.addTileType(m_texture, 4, 0);
		m_level.addTileType(m_texture, 4, 4);
		m_level.loadLvlImage(Gdx.files.internal("levels/level1.png"));
		
		m_guiMgr.addTextActor((m_width * 0.8f) - 50, m_height* 0.99f, "ludum dare 26:\n minimalism");
		m_globalInputMP.addProcessor(m_entMgr.getPlayer());
		Gdx.input.setInputProcessor(m_globalInputMP);
	}

	@Override
	public void dispose() {
		m_texture.dispose();
		m_guiMgr.dispose();
		m_level.dispose();
	}

	@Override
	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		m_physMgr.update(dt);
		m_entMgr.update(dt);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		m_level.draw();
		m_guiMgr.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
