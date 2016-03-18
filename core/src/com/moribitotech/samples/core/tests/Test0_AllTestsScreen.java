package com.moribitotech.samples.core.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.scene2d.ui.ButtonGame;
import com.moribitotech.mtx.scene2d.ui.MenuCreator;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.samples.core.testassets.Assets;

public class Test0_AllTestsScreen extends AbstractScreen {
	
	//
	private Table tableButtons;
	
	public Test0_AllTestsScreen(AbstractGame game, String screenName) {
		super(game, screenName);
        setUpScreenElements();
		setUpGameElements();
		setUpButtons();
	}

	public void setUpScreenElements() {
		setBackgroundTexture(Assets.imgMtxBg);
		setBackButtonActive(true);
	}
	
	private void setUpGameElements() {
	}
	
	private void setUpButtons() {
		tableButtons = MenuCreator.createTable(true, getGame().getAssets().getSkin());
		
		// ####################################################
		final ButtonGame btnTest1 = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnAllMenu, Assets.btnAllMenuPressed);
		btnTest1.setText("Test 1", true);
		btnTest1.setTextPosXY(20, 50);
		btnTest1.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				getGame().setScreen(new Test1_AbstractScreen(getGame(), "Test 1"));
			}});
		tableButtons.add(btnTest1).pad(5);
		
		// ####################################################
		final ButtonGame btnTest2 = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnAllMenu, Assets.btnAllMenuPressed);
		btnTest2.setText("Test 2", true);
		btnTest2.setTextPosXY(20, 50);
		btnTest2.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				getGame().setScreen(new Test2_AbstractActor(getGame(), "Test 2"));
			}});
		tableButtons.add(btnTest2).pad(5);
		tableButtons.row();

		
		// ####################################################
		final ButtonGame btnTest3 = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnAllMenu, Assets.btnAllMenuPressed);
		btnTest3.setText("Test 3", true);
		btnTest3.setTextPosXY(20, 50);
		btnTest3.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				getGame().setScreen(new Test3_Buttons(getGame(), "Test 3"));
			}});
		tableButtons.add(btnTest3).pad(5);

		
		// ####################################################
		final ButtonGame btnTest4 = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnAllMenu, Assets.btnAllMenuPressed);
		btnTest4.setText("Test 4", true);
		btnTest4.setTextPosXY(20, 50);
		btnTest4.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				getGame().setScreen(new Test4_Animations(getGame(), "Test 4"));
			}});
		tableButtons.add(btnTest4).pad(5);
		tableButtons.row();
		
		
		// ####################################################
		final ButtonGame btnTest5 = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnAllMenu, Assets.btnAllMenuPressed);
		btnTest5.setText("Test 5", true);
		btnTest5.setTextPosXY(20, 50);
		btnTest5.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				getGame().setScreen(new Test5_SplashScreen(getGame(), "Test 5"));
			}});
		tableButtons.add(btnTest5).pad(5);

		
		// ####################################################
		final ButtonGame btnTest6 = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnAllMenu, Assets.btnAllMenuPressed);
		btnTest6.setText("Test 6", true);
		btnTest6.setTextPosXY(20, 50);
		btnTest6.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				getGame().setScreen(new Test6_CoolMainMenu(getGame(), "Test 6"));
			}});
		tableButtons.add(btnTest6).pad(5);
		tableButtons.row();
		
		
		// ####################################################
		final ButtonGame btnTest7 = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnAllMenu, Assets.btnAllMenuPressed);
		btnTest7.setText("Test 7", true);
		btnTest7.setTextPosXY(20, 50);
		btnTest7.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				getGame().setScreen(new Test7_LevelsScreen(getGame(), "Test 7"));
			}});
		tableButtons.add(btnTest7).pad(5);
		
		
		// ####################################################
		final ButtonGame btnTest8 = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnAllMenu, Assets.btnAllMenuPressed);
		btnTest8.setText("Test 8", true);
		btnTest8.setTextPosXY(20, 50);
		btnTest8.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				getGame().setScreen(new Test8_GameMenu(getGame(), "Test 8"));
			}});
		tableButtons.add(btnTest8).pad(5);
		tableButtons.row();
		
		
		// Center the table and add to stage
		//tableButtons.setPosition(getStage().getWidth() / 2 - tableButtons.getWidth() /2, getStage().getHeight() - 50);
		getStage().addActor(tableButtons);
	}
	
	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		
		if(getSecondsTime() > 2){
			Gdx.app.exit();
		}
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
	}
}
