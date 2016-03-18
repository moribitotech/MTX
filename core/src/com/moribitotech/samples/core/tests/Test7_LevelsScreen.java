package com.moribitotech.samples.core.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.scene2d.ui.MenuCreator;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.samples.core.ButtonLevel;
import com.moribitotech.samples.core.testassets.Assets;

import java.util.Random;

public class Test7_LevelsScreen extends AbstractScreen {
	private Label lblFps;
	private Label lblScreenTime;
	//
	Table levelsTable1;
	
	public Test7_LevelsScreen(AbstractGame game, String screenName) {
		super(game, screenName);
        setUpScreenElements();
		setUpLevelsScreen();
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
	
	private void setUpLevelsScreen() {
		// Create levels table
		// ######################################################################
	    levelsTable1 = MenuCreator.createTable(true, getGame().getAssets().getSkin());
		levelsTable1.setPosition(-999, 0);
		levelsTable1.addAction(Actions.moveTo(0, 0, 0.7f));
		levelsTable1.top().left().pad(30, 30, 30, 30);
		
		// Add to stage
		// ######################################################################
		getStage().addActor(levelsTable1);
		
		// Add levels buttons
		// Normally get this number from textfiles or database
		// ######################################################################
		int numberOfLevels = 20;
		
		// Create buttons with a loop
		for (int i = 0; i < numberOfLevels; i++){
			//1. Create level button
            Drawable dUp = new TextureRegionDrawable(Assets.btnLevel);
            Drawable dDown = new TextureRegionDrawable(Assets.btnLevelPressed);
            final ButtonLevel levelButton = new ButtonLevel(dUp, dDown);

			//2. Set level number
			levelButton.setLevelNumber(i + 1, Assets.font2);
			
			//3. Set lock condition (get from database if it is locked or not and lock it)
			// use if/else here to lock or not
			// levelButton.setTextureLocked(Assets.btnBatLocked, true);
			
			//4. Set stars or any other achievements (get from database or text files here)
			// I just made a random number of earned stars 
			Random rnd = new Random();
			levelButton.setLevelStars(Assets.imgStarHolder, Assets.imgStar, 3, rnd.nextInt(3) + 1);
			
			//5. Add  listener
			//Add button listener to go to a level (gamascreen)
			levelButton.addListener(new ActorGestureListener() {
			@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					super.touchUp(event, x, y, pointer, button);
				}
			});

			//6. Add row after each 5 level button to go down or how many do you need
			if(i % 5 == 0){
				levelsTable1.row();
			}
			
			// Add to table
			levelsTable1.add(levelButton).size(100, 100).pad(5, 5, 5, 5).expand();
		}		
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
