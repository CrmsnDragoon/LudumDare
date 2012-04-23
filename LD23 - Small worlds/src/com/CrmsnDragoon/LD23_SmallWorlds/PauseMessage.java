package com.CrmsnDragoon.LD23_SmallWorlds;

import java.io.IOException;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class PauseMessage {
	private Texture message, esc, toStart;

	public PauseMessage() {
		loadCharacters();
	}

	private void loadCharacters() {
		try {
			message = TextureLoader.getTexture("PNG", ResourceLoader
					.getResourceAsStream("assets/messages/paused_64x8.png"));
			esc = TextureLoader.getTexture("PNG", ResourceLoader
					.getResourceAsStream("assets/messages/esc_16x8.png"));
			toStart = TextureLoader.getTexture("PNG", ResourceLoader
					.getResourceAsStream("assets/messages/toStart.png"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public void render() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glPushMatrix();
		{
			message.bind();
			GL11.glTranslated(32+8-18, 38+8, 0);
			GL11.glBegin(GL11.GL_QUADS);
			{
				GL11.glVertex3f(0, 0, 1);
						GL11.glTexCoord2d(0, 0);
				GL11.glVertex3f(0, 8, 1);
						GL11.glTexCoord2d(1, 0);
				GL11.glVertex3f(64, 8, 1);
						GL11.glTexCoord2d(1, 1);
				GL11.glVertex3f(64, 0, 1);
						GL11.glTexCoord2d(0, 1);
			}
			GL11.glEnd();
		}
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glPushMatrix();
		{
			esc.bind();
			GL11.glTranslated(32+8-6, 28+8, 0);
			GL11.glBegin(GL11.GL_QUADS);
			{
				GL11.glVertex3f(0, 0, 1);
						GL11.glTexCoord2d(0, 0);
				GL11.glVertex3f(0, 8, 1);
						GL11.glTexCoord2d(1, 0);
				GL11.glVertex3f(16, 8, 1);
						GL11.glTexCoord2d(1, 1);
				GL11.glVertex3f(16, 0, 1);
						GL11.glTexCoord2d(0, 1);
			}
			GL11.glEnd();
		}
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glPushMatrix();
		{
			toStart.bind();
			GL11.glTranslated(32+8-23, 18+8, 0);
			GL11.glBegin(GL11.GL_QUADS);
			{
				GL11.glVertex3f(0, 0, 1);
						GL11.glTexCoord2d(0, 0);
				GL11.glVertex3f(0, 8, 1);
						GL11.glTexCoord2d(1, 0);
				GL11.glVertex3f(64, 8, 1);
						GL11.glTexCoord2d(1, 1);
				GL11.glVertex3f(64, 0, 1);
						GL11.glTexCoord2d(0, 1);
			}
			GL11.glEnd();
		}
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

}
