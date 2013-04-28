package com.crmsndragoon.LD26.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.crmsndragoon.LD26.PhysicsManager;

public abstract class Entity extends Actor {
	private Sprite m_sprite;
	protected Body m_physBody;
	
	public Entity(float x, float y, Sprite m_sprite) {
		super();
		setX(x);
		setY(y);
		setWidth(1);
		setHeight(1);
		setScale(PhysicsManager.BOX_TO_WORLD);
		m_sprite.setBounds(x, y, 1, 1);
		this.m_sprite = m_sprite;
	}
	
	public void update(float dt) {
		if (m_physBody != null) {
			setRotation((float)Math.toDegrees(m_physBody.getAngle()));
			setX(m_physBody.getPosition().x * PhysicsManager.BOX_TO_WORLD);
			setY(m_physBody.getPosition().y * PhysicsManager.BOX_TO_WORLD);
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentColour){
		Color colour = getColor();
		batch.setColor(colour.r,colour.g,colour.b,colour.a*parentColour);
		batch.draw(m_sprite, getX(), getY(), getOriginX()-.5f, getOriginY()-.5f,
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

	}

	public void setPhysBody(Body body) {
		Gdx.app.log("SETTING", "PHYSICS BODY");
		m_physBody = body;
	}
	
	public Body getPhysBody() {
		return m_physBody;
	}

	public Sprite getSprite() {
		return m_sprite;
	}
}
