package com.CrmsnDragoon.TwentyTwo;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Square extends Entity {
	private double xMod = 0, yMod= 0;
	private Image image;

	public Square(int startingID) {
		super(startingID);
		try {
			image = new Image("");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	public Square(int startingID, double x, double y) {
		super(startingID, x, y);
	}

	@Override
	public void Update() {
		x += xMod;
		y += yMod;
		
		xMod *= 0.1;
		yMod *= 0.1;
		
		if(x < -0.8) x = -0.8;
		if(x <  0.8) x =  0.8;
		if(y < -0.8) y = -0.8;
		if(y <  0.8) y =  0.8;
		
		if (xMod <0.001) xMod = 0;
		if (yMod <0.001) yMod = 0;
		
	}

	@Override
	public void render() {
		image.draw((float)x,(float)y);
	}

}
