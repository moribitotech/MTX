package com.moribitotech.samples.core.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.scene2d.ui.ButtonGame;
import com.moribitotech.mtx.scene2d.ui.ButtonToggle;
import com.moribitotech.mtx.scene2d.ui.MenuCreator;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.samples.core.testassets.Assets;
import com.moribitotech.samples.core.testmodels.MenuSideBoxOverLayer;

public class Test8_GameMenu extends AbstractScreen {
	private Label lblFps;
	private Label lblScreenTime;
	//
	MenuSideBoxOverLayer sideBoxLeft;
	MenuSideBoxOverLayer sideBoxRight;
	
	public Test8_GameMenu(AbstractGame game, String screenName) {
		super(game, screenName);
        setUpScreenElements();
		setUpGameMenu();
	}

	private void setUpGameMenu() {
		
		// #8.1 TEST
		// Create two side boxes in different sizes
		// Make them out of the screen
		// Left box 120px - Right box 80px
		// ###################################################################
	    sideBoxLeft = new MenuSideBoxOverLayer(Assets.imgTransparentBlack, -120, 0, 120, getStage().getHeight());
		sideBoxRight = new MenuSideBoxOverLayer(Assets.imgTransparentBlack, getStage().getWidth(), 0, 80, getStage().getHeight());
		getStage().addActor(sideBoxLeft);
		getStage().addActor(sideBoxRight);
		
		//8.2 TEST
		// Create a menu button anyhere you want, I use a toggle button here
		// Toggle Button
		// ####################################################
		final ButtonToggle btnTest = MenuCreator.createCustomToggleButton(Assets.font2, Assets.btnAllMenuPressed, Assets.btnAllMenu, false);
		btnTest.setPosition(getStage().getWidth() / 2 - 100, 20);
		btnTest.setText("Menu", true);
		btnTest.setTextPosXY(20, 50);
		btnTest.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				// Toggle the button, and change animation of abstract actor
				btnTest.setToggleSwitch();
				if(btnTest.isToggleActive()){
					// Send boxes into screen
					sideBoxLeft.addAction(Actions.moveTo(0, 0, 0.4f));
					sideBoxRight.addAction(Actions.moveTo(getStage().getWidth() - 80, 0, 0.4f));

				} else{
					// Send boxes out of screen
					sideBoxLeft.addAction(Actions.moveTo(- 120, 0, 0.4f));
					sideBoxRight.addAction(Actions.moveTo(getStage().getWidth(), 0, 0.4f));
				}

			}});
		getStage().addActor(btnTest);
		
		//#8.3 TEST
		// Dummy buttons to put on boxes, just for fun
		// #########################################################
		ButtonGame btnTest1 = MenuCreator.createCustomGameButton(null, Assets.btnBatCircle, Assets.btnBatCirclePressed);
		sideBoxLeft.add(btnTest1);
		sideBoxLeft.row();
		
		ButtonGame btnTest2 = MenuCreator.createCustomGameButton(null, Assets.btnBatCircle, Assets.btnBatCirclePressed);
		sideBoxLeft.add(btnTest2);
		sideBoxLeft.row();
		
		ButtonGame btnTest3 = MenuCreator.createCustomGameButton(null, Assets.btnBatCircle, Assets.btnBatCirclePressed);
		sideBoxLeft.add(btnTest3);
		sideBoxLeft.row();
		
		ButtonGame btnTest4 = MenuCreator.createCustomGameButton(null, Assets.btnBatCircle, Assets.btnBatCirclePressed);
		sideBoxLeft.add(btnTest4);
		sideBoxLeft.row();
		
		ButtonGame btnTest5 = MenuCreator.createCustomGameButton(null, Assets.btnLevel, Assets.btnLevelPressed);
		sideBoxRight.add(btnTest5);
		sideBoxRight.row();
		
		ButtonGame btnTest6 = MenuCreator.createCustomGameButton(null, Assets.btnLevel, Assets.btnLevelPressed);
		sideBoxRight.add(btnTest6);
		sideBoxRight.row();
		
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
