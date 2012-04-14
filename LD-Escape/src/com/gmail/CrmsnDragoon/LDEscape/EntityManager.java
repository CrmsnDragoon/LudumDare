package com.gmail.CrmsnDragoon.LDEscape;

import java.util.Random;
import java.util.Vector;

public class EntityManager {
	public static Random randGen;
	private Vector<StaticEntity> immobileEnts;
	private Vector<MovingEntity> mobileEnts;

	public EntityManager(String level) {
		immobileEnts = new Vector<StaticEntity>();
		mobileEnts = new Vector<MovingEntity>();
		randGen = new Random();
		populateWorld(level);
	}

	private void populateWorld(String level) {
		immobileEnts.add(new StaticEntity());
		mobileEnts.add(new MovingEntity());
	}

	public void deleteVBOs() {
		for (int i = 0; i < immobileEnts.size(); i++) {
			immobileEnts.elementAt(i).DeleteVBOs();
		}
		for (int i = 0; i < mobileEnts.size(); i++) {
			mobileEnts.elementAt(i).DeleteVBOs();
		}
	}

	public void update() {
		for (int i = 0; i < immobileEnts.size(); i++) {
			immobileEnts.elementAt(i).update();
		}
		for (int i = 0; i < mobileEnts.size(); i++) {
			mobileEnts.elementAt(i).update();
		}
	}

	public void render() {
		for (int i = 0;i< immobileEnts.size();i++)
		{
			immobileEnts.elementAt(i).render();
		}
		for (int i = 0;i< mobileEnts.size();i++)
		{
			mobileEnts.elementAt(i).render();
		}
	}

}
