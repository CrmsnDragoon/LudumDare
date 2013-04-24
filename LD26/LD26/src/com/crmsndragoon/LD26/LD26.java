package com.crmsndragoon.LD26;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.*;

public class LD26 implements ApplicationListener {
	private OrthographicCamera m_camera;
	private SpriteBatch m_batch;
	private Texture m_texture;
	private TextureAtlas m_textureAtlas;
	private Sprite m_sprite;
	private Sound m_coinPickup;
	private AssetManager m_assetMgr;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		m_assetMgr = new AssetManager();
		m_batch = new SpriteBatch();
		m_camera = new OrthographicCamera(w,h);
		
		m_camera.setToOrtho(false,800,400);
		
		Gdx.app.debug("LOADING ", "textures/coin.png");
		m_assetMgr.load("textures/coin.png", Texture.class);
		Gdx.app.debug("LOADING ", "sounds/pickupCoin1.mp3");
		m_assetMgr.load("sounds/pickupCoin1.mp3", Sound.class);
		
		//TODO: Loading screen
		m_assetMgr.finishLoading();
		
		m_texture = m_assetMgr.get("textures/coin.png", Texture.class);
		
		
		int width = 16;
		int height = 32;
		int x = 800 / 2 - width / 2;
		int y = 20;
		
		//create texture region, set which coin by changing x value
		TextureRegion textureRegion = new TextureRegion(m_texture,16,0, width, height);
		
		m_sprite = new Sprite(textureRegion);
		//set sprite position in local space, no matrix stack as of yet, so it's also world space.
		m_sprite.setPosition(x, y);
		
		m_coinPickup = m_assetMgr.get("sounds/pickupCoin1.mp3", Sound.class);
		m_coinPickup.play();
	}

	@Override
	public void dispose() {
		m_assetMgr.clear();
		m_batch.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0.7f, 0.7f, 0.5f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		m_camera.update();
		
		m_batch.setProjectionMatrix(m_camera.combined);
		m_batch.begin();
		m_sprite.draw(m_batch);
		m_batch.end();
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
