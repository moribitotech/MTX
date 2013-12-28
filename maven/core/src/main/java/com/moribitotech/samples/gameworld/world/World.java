package com.moribitotech.samples.gameworld.world;


import com.moribitotech.mtx.game.GameState;
import com.moribitotech.mtx.scene2d.AbstractWorldScene2d;
import com.moribitotech.samples.gameworld.managers.GameManager;

public class World extends AbstractWorldScene2d {
	GameManager gameManager;

	public World(GameManager gameManager, float posX, float posY,
			float worldWidth, float worldHeight) {
		super(posX, posY, worldWidth, worldHeight);
		//
		this.gameManager = gameManager;
	}

	@Override
	public void act(float delta) {
		//
		// Actors act under the world if only game state is Running
		// ####################################################################
		if (gameManager.getGameState() == GameState.GAME_RUNNING) {
			super.act(delta);
		}
	}
}
