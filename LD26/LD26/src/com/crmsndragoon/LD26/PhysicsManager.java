package com.crmsndragoon.LD26;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Disposable;
import com.crmsndragoon.LD26.entities.Entity;
public class PhysicsManager implements Disposable{
	public static final float WORLD_TO_BOX = 0.025f;
	public static final float BOX_TO_WORLD = 40.f;
	private World m_physWorld;

	public PhysicsManager(float gravity) {
		m_physWorld = new World(new Vector2(0,gravity), false);
		
	}

	public void update (float dt) {
		m_physWorld.step(dt, 8, 3);
	}
	
	@Override
	public void dispose() {
		m_physWorld.dispose();
	}
	
	public void registerEntity(Entity ent) {
		BodyDef bodDef = new BodyDef();
		bodDef.position.x = ent.getX() * WORLD_TO_BOX;
		bodDef.position.y = ent.getY() * WORLD_TO_BOX;
		bodDef.type = BodyType.DynamicBody;
		bodDef.active = true;
		bodDef.allowSleep = false;
		bodDef.awake = true;
		bodDef.gravityScale = 1.0f;
		Body bod = m_physWorld.createBody(bodDef);
		bod.applyForceToCenter(new Vector2(50,0));
		FixtureDef fixDef = new FixtureDef();
		PolygonShape entShape = new PolygonShape();
		entShape.setAsBox(0.3f, 0.4f);
		fixDef.shape = entShape;
		fixDef.density = 35.f;
		fixDef.friction = 0.3f;
		bod.createFixture(fixDef);
		entShape.dispose();
		bod.setUserData(ent);
		ent.setPhysBody(bod);
	}
	
	public void registerStaticEntity(Entity ent) {
		BodyDef bodDef = new BodyDef();
		bodDef.position.x = ent.getX() * WORLD_TO_BOX;
		bodDef.position.y = ent.getY() * WORLD_TO_BOX;
		bodDef.type = BodyType.StaticBody;
		Body bod = m_physWorld.createBody(bodDef);
		FixtureDef fixDef = new FixtureDef();
		PolygonShape entShape = new PolygonShape();
		entShape.setAsBox(0.5f, 0.5f);
		fixDef.shape = entShape;
		fixDef.density = 1.0f;
		fixDef.friction = 1.0f;
		bod.createFixture(fixDef);
		entShape.dispose();
		bod.setUserData(ent);
		ent.setPhysBody(bod);
	}
	
	public void registerKinematicEntity(Entity ent) {
		BodyDef bodDef = new BodyDef();
		bodDef.position.x = ent.getX() * WORLD_TO_BOX;
		bodDef.position.y = ent.getY() * WORLD_TO_BOX;
		bodDef.type = BodyType.KinematicBody;
		Body bod = m_physWorld.createBody(bodDef);
		FixtureDef fixDef = new FixtureDef();
		PolygonShape entShape = new PolygonShape();
		entShape.setAsBox(1.f, 1.f);
		fixDef.shape = entShape;
		fixDef.density = 1.0f;
		fixDef.friction = 0.4f;
		bod.createFixture(fixDef);
		entShape.dispose();
		bod.setUserData(ent);
		ent.setPhysBody(bod);
	}
}
