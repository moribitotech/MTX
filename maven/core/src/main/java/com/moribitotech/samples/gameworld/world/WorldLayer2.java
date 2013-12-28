package com.moribitotech.samples.gameworld.world;

import com.moribitotech.mtx.scene2d.AbstractWorldScene2d;
import com.moribitotech.samples.gameworld.assets.Assets;
import com.moribitotech.samples.gameworld.managers.GameManager;
import com.moribitotech.samples.gameworld.models.FlyingEnemy;

import java.util.ArrayList;
import java.util.Random;


public class WorldLayer2 extends AbstractWorldScene2d {
	GameManager gameManager;
	//
	ArrayList<FlyingEnemy> flyingEnemies;

	public WorldLayer2(GameManager gameManager, float posX, float posY,
			float worldWidth, float worldHeight) {
		super(posX, posY, worldWidth, worldHeight);
		//
		this.gameManager = gameManager;
		//
		setUpFlyingEnemies();
	}

	private void setUpFlyingEnemies() {
		//
		flyingEnemies = new ArrayList<FlyingEnemy>();
		Random rnd = new Random();
		//
		for(int i = 0; i < 20; i++){
			FlyingEnemy currentFlyingEnemy = new FlyingEnemy(65, 60, true);
			//
			float posY = gameManager.getStage().getHeight() / 2;
			posY = rnd.nextInt((int)posY) +  gameManager.getStage().getHeight() / 3;
			currentFlyingEnemy.setY(posY);
			currentFlyingEnemy.setX(gameManager.getStage().getWidth() + 200);
			//
			if(i % 2 == 0){
				currentFlyingEnemy.setAnimation(Assets.anim_black_ghost, true, true);
			} else{
				currentFlyingEnemy.setAnimation(Assets.anim_green_santa, true, true);
			}
			//
			float rndSpeed = rnd.nextInt(100) + 20;
			currentFlyingEnemy.startMoving(gameManager.getStage().getWidth(), rndSpeed, true);
			//
			flyingEnemies.add(currentFlyingEnemy);
			addActor(currentFlyingEnemy);
		}
	}
}
