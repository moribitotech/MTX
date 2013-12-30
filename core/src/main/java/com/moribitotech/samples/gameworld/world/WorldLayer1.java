package com.moribitotech.samples.gameworld.world;

import java.util.Random;

import com.badlogic.gdx.utils.Scaling;
import com.moribitotech.mtx.scene2d.AbstractWorldScene2d;
import com.moribitotech.mtx.scene2d.models.EmptyActorLight;
import com.moribitotech.mtx.scene2d.models.SmartActor;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.samples.gameworld.assets.Assets;
import com.moribitotech.samples.gameworld.managers.GameManager;

public class WorldLayer1 extends AbstractWorldScene2d {
	GameManager gameManager;

	public WorldLayer1(GameManager gameManager, float posX, float posY,
			float worldWidth, float worldHeight) {
		super(posX, posY, worldWidth, worldHeight);
		//
		this.gameManager = gameManager;
		//
		setUpBackround();
		setUpBottomSoils();
		setUpClouds();
	}

	private void setUpBackround() {
		setBackgroundTexture(Assets.img_bg_1_, Scaling.stretch, true, false);
	}

	private void setUpBottomSoils() {
		float worldWidth = gameManager.getStage().getWidth();
		float soilWidth = 300f;
		float soilHeight = 100f;
		//
		int numberOfSoils = (int) ((worldWidth / (soilWidth * AppSettings
				.getWorldSizeRatio())) + 1);
		//
		for (int i = 0; i < numberOfSoils; i++) {
			EmptyActorLight currentSoil = new EmptyActorLight(
					soilWidth, soilHeight, true);
			//
			currentSoil.setTextureRegion(Assets.img_obj_soil_1_, true);
			float posX = (i * currentSoil.getWidth())
					- (4 * AppSettings.getWorldSizeRatio());
			currentSoil.setPosition(posX, 0);
			//
			addActor(currentSoil);
		}
	}

	private void setUpClouds() {
		Random rnd = new Random();
		//
		for (int i = 0; i < 8; i++) {
			//
			float rndSizeRatio = rnd.nextInt(80) + 20;
			//
			SmartActor currentCloud = new SmartActor(231 * rndSizeRatio / 100,
					128 * rndSizeRatio / 100, rnd, true);
			//
			currentCloud.setTextureRegion(Assets.img_obj_cloud, true);
			currentCloud.setPosition(-200,
					gameManager.getStage().getHeight() / 2);
			//
			float posY = rnd.nextInt((int) gameManager.getStage().getHeight())
					+ gameManager.getStage().getHeight() / 3;
			//
			currentCloud.startActionMoveSideToSideFreely(15, -200,
					(int) gameManager.getStage().getWidth() + 200, (int) posY,
					15f);
			//
			addActor(currentCloud);
		}
	}

}
