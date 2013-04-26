package com.crmsndragoon.LD26.gui;

import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TextGUIComponent extends GUIComponent {
	private String m_string;
	private BitmapFont m_font;
	
	public TextGUIComponent(Vector2 pos, String str, BitmapFont bitmapFont) {
		super();
		this.setPosition(pos.x,pos.y);
		m_font = bitmapFont;
		m_string = str;
	}
	
	public String getString() {
		return m_string;
	}
	
	public void setString(String str) {
		this.m_string = str;
	}
	
	public BitmapFont getFont() {
		return m_font;
	}
	
	public void setFont(BitmapFont m_font) {
		this.m_font = m_font;
	}

	@Override
	public void draw(SpriteBatch batch, float parentColour){
		Color thisColour = getColor();
		thisColour.a *= parentColour;
		batch.setColor(thisColour);
		m_font.drawMultiLine(batch, m_string, this.getX(), this.getY());
		
	        for (Iterator<GUIComponent> i = m_components.iterator();
	        		i.hasNext();){
	        	 i.next().draw(batch, thisColour.a);
	        }
	}
}
