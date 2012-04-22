package com.CrmsnDragoon.LD23_SmallWorlds;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;



public class SmallWorlds {

	private static boolean paused = true, pausedLastFrame = false;
	private static long lastFrame = getTime();
	private static int frames = 0;
	private static long lastFPS;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(34*16,34*16));
			Display.setFullscreen(false);
			Display.create();
			Mouse.create();
			Keyboard.create();
		} catch (LWJGLException e) {
			System.out.print("ALL THE THINGS WENT WRONG. SORRY");
			e.printStackTrace();
		}
		
		initGL();
		
		int vbo = createVBO();
		int ibo = createIndexBuffer();

		SpriteManager spriteManager = new SpriteManager(vbo, ibo);
		TileManager tileManager = new TileManager(vbo, ibo, spriteManager);
		spriteManager.setTileManager(tileManager);
		tileManager.setLevel("assets/lvl/lvl1.png");

		
		PauseMessage pausedMessage = new PauseMessage();
		
		Display.update();
		lastFPS = getTime();
		lastFrame = getTime();
		while (!Display.isCloseRequested()) {
			pollInput();
			if (!paused) {
				spriteManager.Update(getDeltaTime());
				tileManager.Update(getDeltaTime());
			}
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT |GL11.GL_DEPTH_BUFFER_BIT); 
			
			//GL11.glClearColor(1, 1, 1, 1);
			tileManager.Render();
			spriteManager.Render();
			
			if (paused)
			{
				pausedMessage.render();
			}
			calcFPS();
			
			Display.update();
			Display.sync(120);
			
			if (GL11.glGetError() != GL11.GL_NO_ERROR && GL11.glGetError() != 0)
			{
				System.out.print("GL ERROR: " +GLU.gluGetString( GL11.glGetError())+"\n");
			}
		}
		Keyboard.destroy();
		Mouse.destroy();
		Display.destroy();
	}


	private static void initGL() {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);	
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 64+(8*2), 0, 64+(8*2), 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
	}

	private static void calcFPS() {
		
		if ((getTime() - lastFPS) > 1000)
		{
		Display.setTitle("Small, Square Worlds FPS: " +frames);
		frames = 0;
		lastFPS +=1000;
		}
		frames++;
		
	}

	private static long getTime() {
		return ((Sys.getTime()*1000)/Sys.getTimerResolution());
	}

	private static int getDeltaTime() {
		long time = (getTime());
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	private static int createIndexBuffer() {
		IntBuffer ibo = BufferUtils.createIntBuffer(1);
		ARBVertexBufferObject.glGenBuffersARB(ibo);

		IntBuffer ints = BufferUtils.createIntBuffer(6);
		int array[] = { 0, 1, 2, 2, 3, 0 };
		for (int i = 0; i < array.length; i++) {
			ints.put(array[i]);
		}

		ARBVertexBufferObject.glBindBufferARB(
				ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB, ibo.get(0));
		ARBVertexBufferObject.glBufferDataARB(
				ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB, ints,
				ARBVertexBufferObject.GL_STATIC_DRAW_ARB);
		return ibo.get(0);
	}

	private static int createVBO() {
		IntBuffer vbo = BufferUtils.createIntBuffer(1);
		ARBVertexBufferObject.glGenBuffersARB(vbo);

		FloatBuffer verts = BufferUtils.createFloatBuffer((3+2) * 4);
		float array[] = { 0,0,0, 0,1, 0,100,0, 0,0, 100,100,0, 1,0, 100,0,0, 1,1 }; // x,y,z, u,v, x,y,z, u,v, x,y,z, u,v, x,y,z, u,v
		for (int i = 0; i < array.length; i++) {
			verts.put(array[i]);
		}

		ARBVertexBufferObject.glBindBufferARB(
				ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vbo.get(0));
		ARBVertexBufferObject.glBufferDataARB(
				ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, verts,
				ARBVertexBufferObject.GL_STATIC_DRAW_ARB);
		return vbo.get(0);
	}

	private static void pollInput() {
		Mouse.poll();
		Keyboard.poll();
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			if (!pausedLastFrame) {
				if (paused) {
					paused = false;
					lastFrame = getTime();
				} else {
					paused = true;
				}
			}
			pausedLastFrame = true;
		} else {
			pausedLastFrame = false;
		}
	}

}
