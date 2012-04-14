package com.CrmsnDragoon.TwentyTwo;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TwentyTwo extends BasicGame {
	public EntityManager entManager; 
	public TwentyTwo(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[])
	{
		try { 
		    AppGameContainer container = new AppGameContainer(new TwentyTwo("Twenty Two Warmup")); 
		    container.setDisplayMode(800,600,false); 
		    container.start(); 
		} catch (SlickException e) { 
		    e.printStackTrace(); 
		}
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		entManager.Render();
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		entManager = new EntityManager();
		entManager.AddSquare();
		entManager.AddSquare();
		entManager.AddSquare();
		entManager.AddSquare();
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
