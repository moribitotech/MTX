package com.moribitotech.samples.gameworld.world;

import java.util.Random;

import com.moribitotech.mtx.scene2d.AbstractWorldScene2d;
import com.moribitotech.mtx.scene2d.models.SmartActor;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.samples.gameworld.assets.Assets;
import com.moribitotech.samples.gameworld.managers.GameManager;

public class WorldLayer3 extends AbstractWorldScene2d {
	GameManager gameManager;

	//

	public WorldLayer3(GameManager gameManager, float posX, float posY,
			float worldWidth, float worldHeight) {
		super(posX, posY, worldWidth, worldHeight);
		//
		this.gameManager = gameManager;
		//
		setUpSnowFlakes();
	}

	private void setUpSnowFlakes() {
		//
		Random rnd = new Random();
		int numberOfSnowFlakes = 60;
		//
		for (int i = 0; i < numberOfSnowFlakes; i++) {
			//
			float rndSize = rnd.nextInt(30) + 10;
			SmartActor currentSnowFlake = new SmartActor(rndSize, rndSize, rnd,
					true);
			//
			currentSnowFlake.setTextureRegion(Assets.img_obj_snowflake, true);
			//
			if (i < numberOfSnowFlakes / 2) {
				int[] topRangeX = { 0, (int) AppSettings.WORLD_WIDTH };
				int[] bottomRangeX = { 0 - (int) AppSettings.WORLD_WIDTH, 0 };
				//
				currentSnowFlake.startActionMoveToDirection(topRangeX,
						bottomRangeX, (int) AppSettings.WORLD_HEIGHT + 200,
						-200, 9, 3, true, true);
			} else {
				int[] topRangeX = { (int) AppSettings.WORLD_WIDTH,
						(int) AppSettings.WORLD_WIDTH * 2 };
				int[] bottomRangeX = { 0, (int) AppSettings.WORLD_WIDTH };
				//
				currentSnowFlake.startActionMoveToDirection(topRangeX,
						bottomRangeX, (int) AppSettings.WORLD_HEIGHT + 200,
						-200, 9, 3, true, true);
			}
			//

			addActor(currentSnowFlake);
		}
	}
}
