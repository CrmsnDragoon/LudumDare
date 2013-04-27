package com.crmsndragoon.LD26.gui;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.*;

public class GuiManager {
	private Stage m_stage;
	private BitmapFont m_font;
	private Vector<TextActor> m_textActors;
	public GuiManager() {
		m_stage = new Stage();
		m_textActors = new Vector<TextActor>();
	}
	public TextActor addTextActor(float x, float y, String str){
		TextActor newTextActor = new TextActor(x, y, str, m_font);
		m_stage.addActor(newTextActor);
		m_textActors.add(newTextActor);
		return newTextActor;
	}
	
	public BitmapFont getFont() {
		return m_font;
	}
	public void setFont(BitmapFont m_font) {
		this.m_font = m_font;
	}
	public Stage getStage() {
		return m_stage;
	}

}
