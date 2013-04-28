package com.crmsndragoon.LD26.entities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.crmsndragoon.LD26.PhysicsManager;

public class Player extends Entity implements InputProcessor {
	private Vector2 m_acceleration;
	public Player(float x, float y, Sprite m_sprite) {
		super(x, y, m_sprite);
		setScale(PhysicsManager.BOX_TO_WORLD * 0.6f,
				PhysicsManager.BOX_TO_WORLD * 1f);
		m_acceleration = new Vector2();
	}

	@Override
	public void update(float dt) {
		if (m_physBody != null) {
			setRotation((float) Math.toDegrees((m_physBody.getAngle())));
			setX(m_physBody.getPosition().x * PhysicsManager.BOX_TO_WORLD);
			setY(m_physBody.getPosition().y * PhysicsManager.BOX_TO_WORLD);
			
			if (m_physBody.getLinearVelocity().y == 0){
				m_physBody.applyLinearImpulse(m_acceleration,m_physBody.getLocalCenter());
			}
			else {
				m_physBody.applyLinearImpulse(new Vector2(m_acceleration.x,0),m_physBody.getLocalCenter());
			}
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentColour) {
		Color colour = getColor();
		batch.setColor(colour.r, colour.g, colour.b, colour.a * parentColour);
		batch.draw(getSprite(), getX(), getY(), getOriginX()-0.85f, getOriginY()-.5f,
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation()*57.2957795f);
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.SPACE:
				m_acceleration.y = 200f;
			break;
		case Keys.A:
			m_acceleration.x += -25f;
			break;
		case Keys.D:
			m_acceleration.x += +25f;
			break;
		case Keys.RIGHT:
			m_acceleration.x += -25f;
			break;
		case Keys.LEFT:
			m_acceleration.x += +25f;
			break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.SPACE:
			m_acceleration.y = 0;
			break;
		case Keys.A:
			m_acceleration.x = 0;
			break;
		case Keys.D:
			m_acceleration.x = 0;
			break;
		case Keys.RIGHT:
			m_acceleration.x = 0;
			break;
		case Keys.LEFT:
			m_acceleration.x = 0;
			break;
		default:
			return false;
		}
		return true;
	}
	@Override
	public boolean keyTyped(char character) {
		// not needed as of yet
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
