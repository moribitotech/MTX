package com.moribitotech.samples.gameworld.managers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.moribitotech.mtx.game.AbstractGameManager;
import com.moribitotech.mtx.game.GameState;
import com.moribitotech.mtx.interfaces.IGameManager;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.samples.gameworld.world.World;
import com.moribitotech.samples.gameworld.world.WorldLayer1;
import com.moribitotech.samples.gameworld.world.WorldLayer2;
import com.moribitotech.samples.gameworld.world.WorldLayer3;

public class GameManager extends AbstractGameManager implements IGameManager {
	public World world;
	public WorldLayer1 worldLayer1;
	public WorldLayer2 worldLayer2;
	public WorldLayer3 worldLayer3;

	public GameManager(Stage stage, AbstractScreen screen) {
		super(stage, screen);
		//
		setUpWorld();
		//
		setGameState(GameState.GAME_RUNNING);
	}

	@Override
	public void setUpWorld() {
		//
		// Create the main world and its world layers
		// #############################################################
		world = new World(this, 0, 0, AppSettings.SCREEN_W,
				AppSettings.SCREEN_H);
		
		//
		// World layers
		// #############################################################
		//
		// Layer1 - Background image, bottom soil, clouds
		// Layer2 - Flying enemy objects
		// Layer3 - Snow effect
		//
		worldLayer1 = new WorldLayer1(this, 0, 0, AppSettings.SCREEN_W,
				AppSettings.SCREEN_H);
		worldLayer2 = new WorldLayer2(this, 0, 0, AppSettings.SCREEN_W,
				AppSettings.SCREEN_H);
		worldLayer3 = new WorldLayer3(this, 0, 0, AppSettings.SCREEN_W,
				AppSettings.SCREEN_H);

		//
		// Add all layers to world
		// #############################################################
		world.addActor(worldLayer1);
		world.addActor(worldLayer2);
		world.addActor(worldLayer3);

		//
		// Add the main world to stage
		// #############################################################
		getStage().addActor(world);
	}

	@Override
	public void startLevel(int levelNumber) {

	}

	@Override
	public void checkGameCondition() {

	}

	@Override
	public void update(float delta) {
		checkGameCondition();
	}

	@Override
	public void saveGame() {

	}

    @Override
    public void setUpData() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setUpPreManagers() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setUpPostManagers() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
