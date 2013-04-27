package com.crmsndragoon.LD26.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.crmsndragoon.LD26.PhysicsManager;

public class Player extends Entity {

	public Player(float x, float y, Sprite m_sprite) {
		super(x, y, m_sprite);
		setScale(PhysicsManager.BOX_TO_WORLD*0.6f,PhysicsManager.BOX_TO_WORLD*0.8f);	
	}
	@Override
	public void draw(SpriteBatch batch, float parentColour){
		Color colour = getColor();
		batch.setColor(colour.r,colour.g,colour.b,colour.a*parentColour);
		batch.draw(getSprite(), getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		Gdx.app.log(String.format("Draw Player at %f %f",getX(),getY()),
				String.format("Should be Drawn at: %f, %f",
						PhysicsManager.BOX_TO_WORLD*getPhysBody().getPosition().x,
						PhysicsManager.BOX_TO_WORLD*getPhysBody().getPosition().y));
	}
}
