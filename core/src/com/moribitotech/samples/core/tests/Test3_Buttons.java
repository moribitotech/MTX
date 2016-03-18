package com.moribitotech.samples.core.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.scene2d.ui.ButtonGame;
import com.moribitotech.mtx.scene2d.ui.ButtonToggle;
import com.moribitotech.mtx.scene2d.ui.MenuCreator;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.samples.core.ButtonLevel;
import com.moribitotech.samples.core.testassets.Assets;
import com.moribitotech.samples.core.testmodels.Bat;

public class Test3_Buttons extends AbstractScreen {
	private Label lblFps;
	private Label lblScreenTime;
	
	//
	private Table tableButtons;
	
	//
	private int batCircleCounter = 9;
	//private Bat bat;
	private Bat bat2;
	
	public Test3_Buttons(AbstractGame game, String screenName) {
		super(game, screenName);
        setUpScreenElements();
		setUpGameElements();
		setUpButtons();
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
		
		// #2.1 TEST (NO NEED)
		// Create an abstract actor
		// #####################################################
		//bat = new Bat(Assets.imgBat, true, 50, 20	, 96, 96);
		//getStage().addActor(bat);
		
		// #2.2 TEST
		// Create an abstract actor with animation
		// #####################################################
		bat2 = new Bat(getStage().getWidth() / 2 - 48, 120 , 96, 96);
		bat2.setAnimation(Assets.animBatFlyRight, true, true);
		getStage().addActor(bat2);
	}
	
	private void setUpButtons() {
		tableButtons = MenuCreator.createTable(false, getGame().getAssets().getSkin());
		
		// #3.1 TEST
		// Button locked
		// ####################################################
		ButtonGame btnTest1 = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnBatCircle, Assets.btnBatCirclePressed);
		btnTest1.setTextureLocked(Assets.btnBatLocked, true);
		tableButtons.add(btnTest1).pad(5);
		
		// #3.2 TEST
		// General  Game Button (Button + Text)
		// ####################################################
		final ButtonGame btnTest2 = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnBatCircle, Assets.btnBatCirclePressed);
		btnTest2.setText("" + batCircleCounter, true);
		btnTest2.setTextPosXY(50, 35);
		btnTest2.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				// Set the momentary animation of abstractactor
				// It is like a blink eye animation then goes back to regular animation
				bat2.setAnimationMomentary(Assets.animBatCircleRight, true, null, true, false);
				
				// Decrease counter
				if(batCircleCounter > 1){
					btnTest2.setTextChange("" + --batCircleCounter);
				} else{
					btnTest2.setTextureLocked(Assets.btnBatLocked, true);
				}
			}});
		tableButtons.add(btnTest2).pad(5);
		
		
		
		// #3.3 TEST
		// Toggle Button
		// ####################################################
		final ButtonToggle btnTest3 = MenuCreator.createCustomToggleButton(Assets.font2, Assets.btnBatCirclePressed, Assets.btnBatCircle, false);
		btnTest3.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				// Toggle the button, and change animation of abstract actor
				btnTest3.setToggleSwitch();
				if(btnTest3.isToggleActive()){
					bat2.setAnimation(Assets.animBatCircleRight, true, true);

				} else{
					bat2.setAnimation(Assets.animBatFlyRight, true, true);
				}

			}});
		tableButtons.add(btnTest3).pad(5);
		
		// #3.4 TEST
		// Level Button
		// ####################################################
        Drawable dUp = new TextureRegionDrawable(Assets.btnLevel);
        Drawable dDown = new TextureRegionDrawable(Assets.btnLevelPressed);
        final ButtonLevel btnTest4 = new ButtonLevel(dUp, dDown);
		btnTest4.setLevelStars(Assets.imgStarHolder, Assets.imgStar, 3, 1);
		btnTest4.setLevelStarSizeRatio(3);
		btnTest4.setLevelStarPosYStart(2);
		
		btnTest4.setLevelNumber(1, Assets.font2);
		btnTest4.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				// Reset circle counter, update button 2
				batCircleCounter = 9;
				btnTest2.setTextChange("" + batCircleCounter);
				btnTest2.setLockActive(false);
				
				// if full reset (JUST FOR TEST)
				if(btnTest4.getNumberOfEarnedStars() == btnTest4.getNumberOfTotalStars()){
					btnTest4.setNumberOfEarnedStars(0);
				}
				
				// Increase the earned stars
				btnTest4.setNumberOfEarnedStars(btnTest4.getNumberOfEarnedStars() + 1);
			}});
		tableButtons.add(btnTest4).pad(5);
		
		// #3.5 TEST
		// General Game Button (Button + Text + ExternalImage)
		// ####################################################
		final ButtonGame btnTest5 = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnAllMenu, Assets.btnAllMenuPressed);
		btnTest5.setText("Top Apps", true);
		btnTest5.setTextPosXY(20, 50);
		btnTest5.setTextureExternal(Assets.imgTopApps, true);
		btnTest5.setTextureExternalSize(120, 120);
		btnTest5.setTextureExternalPosXY(144, -16);
		btnTest5.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				// Set the momentary animation of abstractactor
				// It is like a blink eye animation then goes back to regular animation
			}});
		tableButtons.add(btnTest5).pad(5);
		
		// Center the table and add to stage
		tableButtons.setPosition(getStage().getWidth() / 2 - tableButtons.getWidth() /2, 50);
		getStage().addActor(tableButtons);
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
