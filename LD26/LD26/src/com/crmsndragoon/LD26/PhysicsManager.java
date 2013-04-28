package com.crmsndragoon.LD26;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Disposable;
import com.crmsndragoon.LD26.entities.Entity;
public class PhysicsManager implements Disposable{
	public static final float WORLD_TO_BOX = 0.025f;
	public static final float BOX_TO_WORLD = 40.f;
	private World m_physWorld;
	private Box2DDebugRenderer m_debugDraw;
	public PhysicsManager(float gravity) {
		m_physWorld = new World(new Vector2(0,gravity), false);
		
		m_debugDraw = new Box2DDebugRenderer(true, true, true, true, true );
	}

	public void update (float dt) {
		m_physWorld.step(dt, 8, 3);
	}
	
	@Override
	public void dispose() {
		m_physWorld.dispose();
	}
	
	public void render() {
		OrthographicCamera cam = new OrthographicCamera(20,15);
		cam.translate(9.5f, 7f);
		cam.update();
		m_debugDraw.render(m_physWorld, cam.combined);
	}
	
	public void registerEntity(Entity ent) {
		BodyDef bodDef = new BodyDef();
		bodDef.position.x = ent.getX() * WORLD_TO_BOX;
		bodDef.position.y = ent.getY() * WORLD_TO_BOX;
		bodDef.fixedRotation = true;
		bodDef.type = BodyType.DynamicBody;
		bodDef.active = true;
		bodDef.allowSleep = false;
		bodDef.awake = true;
		bodDef.gravityScale = 1.0f;
		Body bod = m_physWorld.createBody(bodDef);
		FixtureDef fixDef = new FixtureDef();
		PolygonShape entShape = new PolygonShape();
		entShape.setAsBox(0.3f, 0.5f);
		fixDef.shape = entShape;
		fixDef.density = 35.f;
		fixDef.friction = 1.f;
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
		fixDef.density = 1.0f;
		fixDef.friction = 1.0f;
		
		PolygonShape entShape = new PolygonShape();
		entShape.setAsBox(0.48f, 0.48f);
		fixDef.shape = entShape;
		bod.createFixture(fixDef);
		entShape.dispose();
		
		EdgeShape shape = new EdgeShape();
		shape.set(new Vector2(-0.51f,0.5f),new Vector2(0.51f,0.5f));
		fixDef.shape = shape;
		bod.createFixture(fixDef);
		shape.dispose();
		
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
		entShape.setAsBox(0.5f, 0.5f);
		fixDef.shape = entShape;
		fixDef.density = 1.0f;
		fixDef.friction = 0.4f;
		bod.createFixture(fixDef);
		entShape.dispose();
		bod.setUserData(ent);
		ent.setPhysBody(bod);
	}
}
