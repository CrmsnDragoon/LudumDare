
package com.crmsndragoon.LD23Warmup;

import java.util.Vector;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * @author Dylan Sinnott
 * @theme Drawings
 * @version 0.1
 */
public class LD23Warmup
{
	static boolean VSYNC = false;
	static boolean FULLSCREEN = false;
	static Vector<String> lvlList = new Vector<String>();
	private static boolean paused;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.setVSyncEnabled(VSYNC);
			Display.setFullscreen(FULLSCREEN);
			Display.create();
			Mouse.create();
			Keyboard.create();
		} catch (LWJGLException e) {
			System.out.print("Something went wrong. Seriously wrong.");
			e.printStackTrace();
		}
		EntityManager entManager = new EntityManager();
		LevelManager lvlManager = new LevelManager(entManager,lvlList);
		
		while (!Display.isCloseRequested())
		{
			if (!paused) {
				entManager.Update();
				lvlManager.Update();
			}
			entManager.Render();
			lvlManager.Render();
			Display.update();
		}
		Display.destroy();
		
	}
}