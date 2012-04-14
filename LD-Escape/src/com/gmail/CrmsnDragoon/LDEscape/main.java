package com.gmail.CrmsnDragoon.LDEscape;

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
class LDEscape{

    private static int sizeX = 720, sizeY = 480;
    
    private static void setFullscreen(boolean fullscreen)
    {
    	try {
			Display.setFullscreen(fullscreen);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public static void main(String[] args) {
   {
    	Display.setVSyncEnabled(false);
    	try {
    		Display.setDisplayMode(new DisplayMode(sizeX,sizeY));
			Display.create();
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			System.out.print("Start up failed. Something is seriously wrong.");
			e.printStackTrace();
			return;
		}
		setFullscreen(false);
		
		EntityManager manager = new EntityManager("\\Levels\\Level 1.txt");
		
		
		while (!Display.isCloseRequested())
		{
			//update everything
			manager.update();
			//draw to the buffers
			manager.render();
			//swap buffers
			Display.update(true);
		}
		
		//cleanup stuff here
		
		//delete VBOs
		manager.deleteVBOs();
		//delete OGL context
		Display.destroy();
		
        
    }
}}