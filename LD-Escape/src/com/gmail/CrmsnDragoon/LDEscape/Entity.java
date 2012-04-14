package com.gmail.CrmsnDragoon.LDEscape;

import java.util.Vector;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector3f;

public abstract class Entity {
	Vector<Vector3f> verts;
	Vector<Integer> indices;
	Vector<Vector3f> texCoords;
	Vector<Integer> texIndices;
	Vector3f position, velocity, direction, pitch, roll;
	public Entity()// provide your own verts
	{
		verts = new Vector<Vector3f>();
		indices = new Vector<Integer>();
		texCoords = new Vector<Vector3f>();
		texIndices = new Vector<Integer>();
		
		verts.add(new Vector3f(0.5f, 0.5f, 0));
		verts.add(new Vector3f(-0.5f, 0.5f, 0));
		verts.add(new Vector3f(-0.5f, -0.5f, 0));
		verts.add(new Vector3f(0.5f, -0.5f, 0));
		indices.add(0);
		indices.add(1);
		indices.add(2);
		indices.add(2);
		indices.add(3);
		indices.add(0);
	}

	public Entity(String modelPath) {
		verts = new Vector<Vector3f>();
		indices = new Vector<Integer>();
		texCoords = new Vector<Vector3f>();
		texIndices = new Vector<Integer>();
	}

	public abstract void update();

	public void render() {
		//glMultiplyMatrix(hMatrix);
		glBegin(GL_TRIANGLES);
		for (int i = 0; i < indices.size(); i++) {
			glVertex3f(verts.elementAt(indices.elementAt(i)).x,
					verts.elementAt(indices.elementAt(i)).y,
					verts.elementAt(indices.elementAt(i)).z);
		}
		glEnd();
	}

	public void DeleteVBOs() {
	}
}
