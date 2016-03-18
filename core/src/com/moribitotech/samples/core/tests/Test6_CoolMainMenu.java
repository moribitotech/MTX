package com.moribitotech.samples.core.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.scene2d.ui.ButtonGame;
import com.moribitotech.mtx.scene2d.ui.MenuCreator;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.samples.core.testassets.Assets;

public class Test6_CoolMainMenu extends AbstractScreen {
	private Label lblFps;
	private Label lblScreenTime;
	
	public Test6_CoolMainMenu(AbstractGame game, String screenName) {
		super(game, screenName);
        setUpScreenElements();
		setUpMainMenu();
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
	
	private void setUpMainMenu() {
		// #6.1 TEST
		// Create table button 
		// Animate from outside in
		// ########################################################################
		Table table = MenuCreator.createTable(true, getGame().getAssets().getSkin());
		table.setPosition(getStage().getWidth() + 50, table.getY());
		table.addAction(Actions.moveTo(getStage().getWidth() - 550,  table.getY(), 0.9f));
		table.row();
		getStage().addActor(table);
		
		// #6.2 TEST
		// Random buttons
		// ###############################################################################
		ButtonGame btnLevels = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnAllMenu,Assets.btnAllMenuPressed);
		btnLevels.setText("Levels", true);
		btnLevels.setTextPosXY(25, 45);
		table.add(btnLevels).size(230, 62).uniform().pad(2);
		table.row();
		
		ButtonGame btnSettings = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnAllMenu,Assets.btnAllMenuPressed);
		btnSettings.setText("Settings", true);
		btnSettings.setTextPosXY(25, 45);
		table.add(btnSettings).size(230, 62).uniform().pad(2);
		table.row();
		
		ButtonGame btnAbout = MenuCreator.createCustomGameButton(Assets.font2, Assets.btnAllMenu,Assets.btnAllMenuPressed);
		btnAbout.setText("About", true);
		btnAbout.setTextPosXY(25, 45);
		table.add(btnAbout).size(230, 62).uniform().pad(2);
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
