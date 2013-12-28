package com.moribitotech.samples.core.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.samples.core.testassets.Assets;

public class Test1_AbstractScreen extends AbstractScreen {
	private Label lblFps;
	private Label lblScreenTime;
	
	public Test1_AbstractScreen(AbstractGame game, String screenName) {
		super(game, screenName);
        setUpScreenElements();
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
	}
}
