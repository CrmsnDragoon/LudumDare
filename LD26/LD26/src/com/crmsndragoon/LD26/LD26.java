package com.crmsndragoon.LD26;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.*;
import com.crmsndragoon.LD26.gui.GuiManager;
import com.crmsndragoon.LD26.level.Level;

public class LD26 implements ApplicationListener {
	private OrthographicCamera m_camera;
	private SpriteBatch m_batch;
	private Texture m_texture;
	private Sprite m_sprite;
	private AssetManager m_assetMgr;
	private InputMultiplexer m_globalInputMP;
	private GuiManager m_guiMgr;
	private Level m_level;
	
	@Override
	public void create() {	
		m_assetMgr = new AssetManager();
		m_assetMgr.load("fonts/font.fnt", BitmapFont.class);
		m_assetMgr.load("textures/2Tone.png",Texture.class);
		//m_assetMgr.load("textures/4Tone.png",Texture.class);
		
		//busy work
		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
		m_camera = new OrthographicCamera();
		m_camera.setToOrtho(false, width, height);
		m_batch = new SpriteBatch();
		m_guiMgr = new GuiManager();
		m_level = new Level();
		m_globalInputMP = new InputMultiplexer();
		m_globalInputMP.addProcessor(m_guiMgr.getStage());
		m_globalInputMP.addProcessor(m_level.getInputMultiplexer());
		m_assetMgr.finishLoading();
		//rather have getters and setters than have a public field
		m_guiMgr.setFont((BitmapFont) m_assetMgr.get("fonts/font.fnt"));
		m_texture = m_assetMgr.get("textures/2Tone.png");
	}

	@Override
	public void dispose() {
		m_batch.dispose();
		m_texture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		m_batch.setProjectionMatrix(m_camera.combined);
		m_batch.begin();
		m_batch.end();
	}

	@Override
	public void resize(int width, int height) {
		m_camera.viewportWidth = width;
		m_camera.viewportWidth = height;
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
