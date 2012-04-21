package com.CrmsnDragoon.LD23_SmallWorlds;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

public class Entity {
protected Vector3f loc;
private Texture texture;
	public Entity(Texture Tex, float x, float y, float z)
	{
		loc = new Vector3f(x,y,z);
		this.texture = Tex;
		
	}
	
	public void Render()
	{
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glLoadIdentity();
		GL11.glColor3ub((byte)255,(byte) 255,(byte) 255);
		texture.bind();
		GL11.glTranslatef(loc.x,loc.y,loc.z);
		GL11.glScalef(6, 6, 0);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex3f(0, 0, 0);
					GL11.glTexCoord2d(0, 0);
			GL11.glVertex3f(0, 10, 0);
					GL11.glTexCoord2d(1, 0);
			GL11.glVertex3f(10, 10, 0);
					GL11.glTexCoord2d(1, 1);
			GL11.glVertex3f(10, 0, 0);
					GL11.glTexCoord2d(0, 1);
		 GL11.glEnd();
		}

	public void setLoc(float x, float y, float z) {
		this.loc.x = x;
		this.loc.y = y;
		this.loc.z = z;
	}

	public Vector getLoc() {
		return loc;
	}
}
