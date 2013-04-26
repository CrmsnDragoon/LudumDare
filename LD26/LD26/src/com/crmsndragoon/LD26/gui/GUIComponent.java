package com.crmsndragoon.LD26.gui;

import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class GUIComponent extends Actor{
	protected Vector <GUIComponent> m_components;
	
	public GUIComponent(){
		super();
		m_components = new Vector <GUIComponent>();
	}
	
	public Vector <GUIComponent> getComponents() {
		return m_components;
	}

	public void addComponent(GUIComponent component) {
		m_components.add(component);
	}
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
    	Color color = getColor();
        for (Iterator<GUIComponent> i = m_components.iterator();
        		i.hasNext();){
        	 i.next().draw(batch, color.a *= parentAlpha);
        }
	}

}
