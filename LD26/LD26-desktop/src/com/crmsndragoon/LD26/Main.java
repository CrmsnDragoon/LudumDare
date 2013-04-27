package com.crmsndragoon.LD26;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "LD26";
		cfg.useGL20 = false;
		cfg.width = 400;
		cfg.height = 300;
		
		new LwjglApplication(new LD26(), cfg);
	}
}
