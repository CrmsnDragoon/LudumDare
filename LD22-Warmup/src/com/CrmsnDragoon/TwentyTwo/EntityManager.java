package com.CrmsnDragoon.TwentyTwo;

import java.util.Random;
import java.util.Vector;

public class EntityManager {
	public Vector<Entity> entitys;
	public Random rand;
	
	public EntityManager() {
		entitys = new Vector<Entity>();
		rand = new Random();
	}
	
	public void AddSquare()
	{
		entitys.add(new Square(entitys.size()+1,rand.nextDouble(),rand.nextDouble()));
	}
	
	public void Update()
	{
		while (entitys.iterator().hasNext())
		{
			entitys.iterator().next().Update();
		}
	}
	
	public void Render(){
		for (int i = 0; i< entitys.size();i++)
		{
			entitys.elementAt(i).render();
		}
	}
}
