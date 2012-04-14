package com.CrmsnDragoon.LD22Alone;

import java.util.Vector;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class LD22Alone extends BasicGame {
	Vector<Entity> pawns;
	Me me;

	public LD22Alone(String title) {
		super(title);
		pawns = new Vector<Entity>();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new LD22Alone(
					"Alone"));
			//container.setMinimumLogicUpdateInterval(20);
			container.setDisplayMode(800, 600, false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		for (int i = 0; i<pawns.size();i++){
			pawns.get(i).Render();
		}

	}

	@Override
	public void init(GameContainer container) throws SlickException {
		for (int i = 0; i < 99; i++) {
			for (int j = 0; j < 37; j++) {
				pawns.add(new Pawn((i ) * 8, (j) * 16, (i+1)*(j+1)));
			}
		}
		System.out.print("Init done");
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		for (int i = 0; i<pawns.size();i++){
			pawns.get(i).Update(me);
		}

	}

}
