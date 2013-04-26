package com.crmsndragoon.LD26.gui;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;

public class GUIManager implements Disposable {
	private Stage m_guiStage;
	private BitmapFont m_debugFont;
	private BitmapFont m_menuFont;
	
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
		//assetMgr.load("fonts/debug", BitmapFont.class);
		assetMgr.load("fonts/menu.fnt", BitmapFont.class);
		
	}
	public void finishAssetLoad(AssetManager assetMgr){
		//m_debugFont = assetMgr.get("fonts/debug.fnt",BitmapFont.class);
		m_menuFont = assetMgr.get("fonts/menu.fnt",BitmapFont.class);
	}
	public enum GUITEXTTYPE{
		DEBUG,
		MENU
		}
	
	public TextGUIComponent addTextActor( Vector2 pos,String str){
		//if there is debug text where there shouldn't be, this is something to look into.
		return addTextActor(pos, str, GUITEXTTYPE.DEBUG);
	}
	public TextGUIComponent addTextActor( Vector2 pos, String str, GUITEXTTYPE type){
		TextGUIComponent newComponent =  null;
		switch(type){
		case DEBUG:
			newComponent = new TextGUIComponent(pos, str,m_debugFont);
			break;
		case MENU:
			newComponent = new TextGUIComponent(pos, str,m_menuFont);
			break;
		}
		m_guiStage.addActor(newComponent);
		
		return newComponent;
	}
}
