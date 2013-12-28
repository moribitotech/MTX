package com.moribitotech.samples.core.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.samples.core.testassets.Assets;
import com.moribitotech.samples.core.testmodels.Bat;

public class Test2_AbstractActor extends AbstractScreen {
	private Label lblFps;
	private Label lblScreenTime;
	private Bat bat;
	private Bat bat2;
	
	public Test2_AbstractActor(AbstractGame game, String screenName) {
		super(game, screenName);
        setUpScreenElements();
        setUpGameElements();
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
	
	private void setUpGameElements() {
		
		// #2.1 TEST
		// Create an abstract actor
		// #####################################################
		bat = new Bat(Assets.imgBat, true, 50, 20	, 96, 96);
		getStage().addActor(bat);
		
		// #2.2 TEST
		// Create an abstract actor with animation
		// #####################################################
		bat2 = new Bat(50, 100 , 96, 96);
		bat2.setAnimation(Assets.animBatFlyRight, true, true);
		getStage().addActor(bat2);
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
