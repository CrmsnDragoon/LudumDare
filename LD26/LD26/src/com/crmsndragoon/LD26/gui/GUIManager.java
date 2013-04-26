package com.crmsndragoon.LD26.gui;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.*;

public class GUIManager implements Disposable {
	private Stage m_guiStage;
	
	public GUIManager(float width, float height){
		m_guiStage = new Stage();
		m_guiStage.setViewport(width, height, true);
	}
	
	public void draw (){
		getGuiStage().draw();
	}
	
	@Override
	public void dispose() {
		getGuiStage().dispose();
		
	}

	public Stage getGuiStage() {
		return m_guiStage;
	}
	
	public void resize(int width, int height){
		m_guiStage.setViewport(width, height, true);
	}
	
	public Actor addActor(Actor actor){
		m_guiStage.addActor(actor);
		return m_guiStage.getActors().get(m_guiStage.getActors().size-1);
	}

	public void loadAssets(AssetManager assetMgr) {
		assetMgr.load("fonts/debug", BitmapFont.class);
		assetMgr.load("fonts/debug", BitmapFont.class);
	}
}
