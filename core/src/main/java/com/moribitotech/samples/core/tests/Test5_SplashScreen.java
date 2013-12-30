package com.moribitotech.samples.core.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.samples.core.testassets.Assets;
import com.moribitotech.samples.core.testmodels.Loading;

public class Test5_SplashScreen extends AbstractScreen {
	private Label lblFps;
	private Label lblScreenTime;
	//
	private Loading loading;
	
	public Test5_SplashScreen(AbstractGame game, String screenName) {
		super(game, screenName);
        setUpScreenElements();
		setUpSplash();
	}
	
	private void setUpSplash() {
		// #5.1 Test
		// Create a loading anim (NOT REAL), center it
		// "Loading" is a model, it just extends abstractactor
		// #########################################################
		loading = new Loading(getStage().getWidth() / 2 - 50, getStage().getHeight() / 2 - 50, 100, 100);
		loading.setAnimation(Assets.animLoadingSkull, true, true);
		getStage().addActor(loading);
	}

	public void setUpScreenElements() {
		// #1.1 TEST
		// Set background texture
		// #########################################################
		setBackgroundTexture(Assets.imgMtxBg);
			
		// #1.2 TEST
		// Set back button
		// (Override keyBackPressed to do some action see very below)
		// #########################################################
		setBackButtonActive(true);
		
		// #1.3 TEST
		// Screen time / Fps
		// Update by overriding render
		// #########################################################
		lblScreenTime = new Label("", getGame().getAssets().getSkin());
		lblFps = new Label("", getGame().getAssets().getSkin());
		lblScreenTime.setPosition(getStage().getWidth() - 80, getStage().getHeight() - 40);
		lblFps.setPosition(getStage().getWidth() - 80, getStage().getHeight() - 60);
		getStage().addActor(lblScreenTime);
		getStage().addActor(lblFps);
	}
	
	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		getGame().setScreen(new Test0_AllTestsScreen(getGame(), ""));
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		lblScreenTime.setText(getScreenTime());
		lblFps.setText("Fps: " + Gdx.graphics.getFramesPerSecond());
		
		//
		if(getSecondsTime() > 6){
			loading.addAction(Actions.sequence(
					Actions.fadeOut(0.8f),
					new Action() {
						@Override
						public boolean act(float delta) {
							getGame().setScreen(new Test0_AllTestsScreen(getGame(), "All tests"));
							return false;
						}
					}
			));
		}
		
	}
}
