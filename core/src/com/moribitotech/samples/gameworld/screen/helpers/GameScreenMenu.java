package com.moribitotech.samples.gameworld.screen.helpers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.moribitotech.mtx.game.GameState;
import com.moribitotech.mtx.scene2d.ui.ButtonToggle;
import com.moribitotech.mtx.scene2d.ui.MenuCreator;
import com.moribitotech.samples.gameworld.assets.Assets;
import com.moribitotech.samples.gameworld.screens.GameScreen;

public class GameScreenMenu {
	public ButtonToggle btnPlayStop;

	public void setUpGameScreenMenu(final GameScreen gameScreen) {
		btnPlayStop = MenuCreator.createCustomToggleButton(null,
                Assets.img_btn_pause, Assets.img_btn_play, false, 100, 100,
                true);
		btnPlayStop.setPosition(gameScreen.getStage().getWidth() - btnPlayStop.getWidth(), 
				gameScreen.getStage().getHeight() - btnPlayStop.getHeight());
		btnPlayStop.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				//
				btnPlayStop.setToggleSwitch();
				//
				if(btnPlayStop.isToggleActive()){
					gameScreen.gameManager.setGameState(GameState.GAME_PAUSED);
				} else{
					gameScreen.gameManager.setGameState(GameState.GAME_RUNNING);
				}
			}
		});
		//
		gameScreen.getStage().addActor(btnPlayStop);
	}
}
