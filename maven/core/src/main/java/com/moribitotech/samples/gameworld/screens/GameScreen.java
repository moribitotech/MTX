package com.moribitotech.samples.gameworld.screens;

import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.interfaces.IGameScreen;
import com.moribitotech.mtx.interfaces.IScreen;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.samples.gameworld.managers.GameManager;
import com.moribitotech.samples.gameworld.screen.helpers.GameScreenMenu;

public class GameScreen extends AbstractScreen implements IScreen, IGameScreen {
	public GameManager gameManager;
	public GameScreenMenu gameScreenMenu;
	
	public GameScreen(AbstractGame game, String screenName) {
		super(game, screenName);
		//
		setUpGameManager();
		setUpMenu();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		//
		// Update game manager, (Useful for checking game condition)
		// ######################################################
		if(gameManager != null){
			gameManager.update(delta);
		}
	}

	@Override
	public void setUpScreenElements() {
		
	}

	@Override
	public void setUpMenu() {
		gameScreenMenu = new GameScreenMenu();
		gameScreenMenu.setUpGameScreenMenu(GameScreen.this);
	}

	@Override
	public void setUpGameManager() {
		gameManager = new GameManager(getStage(), this);
	}

	@Override
	public void setUpGameMenu() {
		
	}
}
