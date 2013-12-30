package com.moribitotech.samples.gameworld.models;


import com.moribitotech.mtx.scene2d.AbstractActor;

public class FlyingEnemy extends AbstractActor {
	float worldWidth;
	float speed;
	boolean isMoving;
	
	
	public FlyingEnemy(float width, float height, boolean DIPActive) {
		super(width, height, DIPActive);
	}
	
	public void startMoving(float worldWidth,  float speed, boolean isMoving){
		this.worldWidth = worldWidth;
		this.speed = speed;
		this.isMoving = isMoving;

	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		//
		checkPosition();
		//
		if(isMoving){
			translateWithoutAcc(-speed, 0, delta);
		}
	}

	private void checkPosition() {
		if(getX() < -200){
			setX(worldWidth + 200);
		}
	}
}
