package com.moribitotech.samples.core.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.moribitotech.mtx.animation.AnimationCreator;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.scene2d.ui.ButtonGame;
import com.moribitotech.mtx.scene2d.ui.MenuCreator;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.samples.core.testassets.Assets;
import com.moribitotech.samples.core.testmodels.Bat;
import com.moribitotech.samples.core.testmodels.Hulk;

public class Test4_Animations extends AbstractScreen {
	private Label lblFps;
	private Label lblScreenTime;

	//
	private Table tableButtons;

	// Models
	private Hulk hulk;
	private Bat bat;
	private Bat batsanta;
	
	// Hulk animations (Normally done in Asset class or via AssetManager)
	// We set animations here only for demonstration purposes
	private Animation animBruceWalk;
	private Animation animBruceToHulk;
	private Animation animHulkWalk;
	private Animation animHulkToBruce;
	private Animation animBatFlyRight;
	private Animation animBatSantaFlyRight;
	
	public Test4_Animations(AbstractGame game, String screenName) {
		super(game, screenName);
        setUpScreenElements();
		setUpAnimations(); // Normally done in Asset class
		setUpGameElements();
		setUpButtons();
	}
	
	private void setUpAnimations() {
		// Bruce - Hulk Animations (Single Sheet multiple rows)
		animBruceWalk = AnimationCreator.getAnimationFromSingleTextureMultiRows(Assets.getAtlas(), "animhulkbruce", 8, 22, 4, 0, 0.08f);
		animHulkWalk = AnimationCreator.getAnimationFromSingleTextureMultiRows(Assets.getAtlas(), "animhulkbruce", 8, 22, 4, 1, 0.08f);
		animBruceToHulk = AnimationCreator.getAnimationFromSingleTextureMultiRows(Assets.getAtlas(), "animhulkbruce", 21, 22, 4, 2, 0.08f);
		animHulkToBruce = AnimationCreator.getAnimationFromSingleTextureMultiRows(Assets.getAtlas(), "animhulkbruce", 18, 22, 4, 3, 0.08f);
	
		// Sinlge row animation
		animBatFlyRight = AnimationCreator.getAnimationFromSingleTexture(Assets.getAtlas(), "animbatflyright", 6, 0.065f);
	
		// Multi frame .png animation
		animBatSantaFlyRight = AnimationCreator.getAnimationFromMultiTextures(Assets.getAtlas(), "batsanta", 6, 0.065f, false, false);
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
		lblScreenTime.setPosition(getStage().getWidth() - 80, getStage()
				.getHeight() - 40);
		lblFps.setPosition(getStage().getWidth() - 80,
				getStage().getHeight() - 60);
		getStage().addActor(lblScreenTime);
		getStage().addActor(lblFps);
	}

	private void setUpGameElements() {
		//
		hulk = new Hulk(getStage().getWidth() / 2 - 48, 120, 96, 96);
		hulk.setSize(hulk.HULK_SIZE	,hulk.HULK_SIZE * 2);
		hulk.setAnimation(animBruceWalk, true, true);
		getStage().addActor(hulk);
		
		//
		batsanta = new Bat(10, 100, 80, 80);
		batsanta.setAnimation(animBatSantaFlyRight, true, true);
		getStage().addActor(batsanta);
		
		
		// 
		bat = new Bat(20, 20, 80, 80);
		bat.setAnimation(animBatFlyRight, true, true);
		getStage().addActor(bat);
	}

	private void setUpButtons() {
		tableButtons = MenuCreator.createTable(false, getGame().getAssets().getSkin());

		// #4.2 TEST
		// Bruce To Hulk
		// ####################################################
		final ButtonGame btnBruceToHulk = MenuCreator.createCustomGameButton(null,
				Assets.btnBatCircle, Assets.btnBatCirclePressed);
		final ButtonGame btnHulkToBruce = MenuCreator.createCustomGameButton(null,
				Assets.btnBatCircle, Assets.btnBatCirclePressed);
		
		
		// Initials
		btnHulkToBruce.setTextureLocked(Assets.btnBatLocked, true);
		
		// Listeners
		// ####################################################
		btnBruceToHulk.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				hulk.setSize(hulk.HULK_SIZE	,hulk.HULK_SIZE * 2);
				hulk.setAnimationMomentary(animBruceToHulk, true, animHulkWalk, true, false);
				
				//
				btnBruceToHulk.setTextureLocked(Assets.btnBatLocked, true);
				btnHulkToBruce.setLockActive(false);
				
				// 
			}			
		});
		tableButtons.add(btnBruceToHulk).pad(5);
		
		
		btnHulkToBruce.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				hulk.setSize(hulk.HULK_SIZE	,hulk.HULK_SIZE * 2);
				hulk.setAnimationMomentary(animHulkToBruce, true, animBruceWalk, true, false);
				
				btnHulkToBruce.setTextureLocked(Assets.btnBatLocked, true);
				btnBruceToHulk.setLockActive(false);
				
				//
			}
		});
		tableButtons.add(btnHulkToBruce).pad(5);

		// Center the table and add to stage
		tableButtons.setPosition(getStage().getWidth() / 2 - tableButtons.getWidth() / 2, 50);
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
