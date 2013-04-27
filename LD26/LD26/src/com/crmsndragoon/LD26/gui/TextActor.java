package com.crmsndragoon.LD26.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class TextActor extends Actor {
	private String m_string;
	private BitmapFont m_font;
	public TextActor(float x, float y, String str, BitmapFont fnt) {
		super();
		setX(x);
		setY(y);
		m_string = str;
		m_font = fnt;
		setTouchable(Touchable.disabled);
	}
	@Override
	public void draw(SpriteBatch batch, float parentColour){
		batch.setColor(1, 1, 1, parentColour);
		m_font.drawMultiLine(batch,m_string, getX(),getY());
	}
	public String getString() {
		return m_string;
	}
	public void setString(String m_string) {
		this.m_string = m_string;
	}
	public BitmapFont getFont() {
		return m_font;
	}
	public void setFont(BitmapFont m_font) {
		this.m_font = m_font;
	}
}
