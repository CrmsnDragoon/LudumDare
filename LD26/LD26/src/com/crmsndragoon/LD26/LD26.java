package com.crmsndragoon.LD26;

import com.crmsndragoon.LD26.gui.*;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.*;

public class LD26 implements ApplicationListener {
	private GUIManager m_guiMgr;
	private InputMultiplexer m_multiplex;
	private OrthographicCamera m_camera;
	private SpriteBatch m_batch;
	private Texture m_texture;
	private Sprite m_sprite;
	private Sound m_coinPickup;
	private AssetManager m_assetMgr;
	private BitmapFont m_textFont;
	
	@Override
	public void create() {		
		
		//Start loading assets ASAP to reduce blocking.
		m_assetMgr = new AssetManager();
		Gdx.app.debug("LOADING ", "textures/coin.png");
		m_assetMgr.load("textures/coin.png", Texture.class);
		Gdx.app.debug("LOADING ", "textures/test.dds");
		m_assetMgr.load("textures/coin.png", Texture.class);
		Gdx.app.debug("LOADING ", "sounds/pickupCoin1.mp3");
		m_assetMgr.load("sounds/pickupCoin1.mp3", Sound.class);
		//if another font besides Arial is desired, load it here.
		//m_assetMgr.load("fonts/",BitmapFont.class);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		m_guiMgr = new GUIManager(w,h);
		m_guiMgr.loadAssets(m_assetMgr);
		
		m_batch = new SpriteBatch();
		m_camera = new OrthographicCamera(w,h);
		m_camera.setToOrtho(false,800,480);
		
		m_textFont = new BitmapFont();
		
		m_multiplex = new InputMultiplexer();
		m_multiplex.addProcessor(m_guiMgr.getGuiStage());
		Gdx.input.setInputProcessor(m_multiplex);
		
		//this does mean that you can stack multiplexers
		//one for GUI, one for player control, one for menus, etc.
		
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
		//set sprite position in local space, no matrix stack as of yet
		// so it's also world space.
		m_sprite.setPosition(x, y);
		
		m_coinPickup = m_assetMgr.get("sounds/pickupCoin1.mp3", Sound.class);
		m_coinPickup.play();
	}

	@Override
	public void dispose() {
		m_assetMgr.dispose();
		m_batch.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0.7f, 0.7f, 0.5f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		m_camera.update();
		
		//GUI!
		m_batch.setProjectionMatrix(m_camera.combined);
		m_batch.begin();
		for (int i = 0; i<=30;++i){
			for(int j = 0; j<=1;++j){
				m_sprite.setPosition((16*i)+16, -((32*j)+16)+ Gdx.graphics.getHeight()*0.925f);
				m_sprite.draw(m_batch);
			}
		}
		m_textFont.drawMultiLine(m_batch,"Ludum Dare #26\nTheme: TBA",
				Gdx.graphics.getWidth()*0.8f,Gdx.graphics.getHeight()*0.95f);
		//End of GUI!
		m_batch.end();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.graphics.setDisplayMode(width, height, false);
		m_camera.setToOrtho(false, width, height);
		m_guiMgr.resize(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
