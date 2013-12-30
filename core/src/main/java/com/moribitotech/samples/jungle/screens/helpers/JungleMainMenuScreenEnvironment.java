package com.moribitotech.samples.jungle.screens.helpers;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.moribitotech.mtx.scene2d.models.EmptyActorLight;
import com.moribitotech.mtx.scene2d.models.SmartActor;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.samples.jungle.assets.Assets;
import com.moribitotech.samples.jungle.screens.JungleMainMenuScreen;

import java.util.ArrayList;
import java.util.Random;

public class JungleMainMenuScreenEnvironment {

	public void setUpGameName(final JungleMainMenuScreen jungleMainMenuScreen) {
		jungleMainMenuScreen.gameName = new EmptyActorLight(420, 280,
				true);
		jungleMainMenuScreen.gameName.setTextureRegion(
				Assets.img_obj_text_junglegamemenu, true);
		jungleMainMenuScreen.gameName.setOrigin(
				jungleMainMenuScreen.gameName.getWidth() / 2,
				jungleMainMenuScreen.gameName.getHeight() / 2);
		jungleMainMenuScreen.gameName.setPosition(
				AppSettings.SCREEN_W / 2
						- jungleMainMenuScreen.gameName.getWidth() / 2,
				AppSettings.SCREEN_H
						+ jungleMainMenuScreen.gameName.getHeight());

		//
		jungleMainMenuScreen.getStage().addActor(jungleMainMenuScreen.gameName);
	}

	public void setUpMounatins(final JungleMainMenuScreen jungleMainMenuScreen) {
		jungleMainMenuScreen.mountains = new EmptyActorLight(
				AppSettings.SCREEN_W, 250 * AppSettings.getWorldSizeRatio(),
				false);
		jungleMainMenuScreen.mountains.setTextureRegion(
				Assets.img_obj_mountains, true);
		jungleMainMenuScreen.mountains.setPosition(0, 0);

		//
		jungleMainMenuScreen.getStage()
				.addActor(jungleMainMenuScreen.mountains);
	}

	public void sendInGameName(final JungleMainMenuScreen jungleMainMenuScreen) {
		jungleMainMenuScreen.gameName.addAction(Actions.moveTo(
				AppSettings.SCREEN_W / 2
						- jungleMainMenuScreen.gameName.getWidth() / 2,
				AppSettings.SCREEN_H
						- jungleMainMenuScreen.gameName.getHeight(), 0.5f));

	}

	public void sendAwayGameName(final JungleMainMenuScreen jungleMainMenuScreen) {
		jungleMainMenuScreen.gameName.addAction(Actions.moveTo(
				AppSettings.SCREEN_W / 2
						- jungleMainMenuScreen.gameName.getWidth() / 2,
				AppSettings.SCREEN_H
						+ jungleMainMenuScreen.gameName.getHeight(), 0.5f));
	}

	public void setUpBackgroundBalloons(
			final JungleMainMenuScreen jungleMainMenuScreen) {
		jungleMainMenuScreen.backgroundBalloons = new ArrayList<SmartActor>();
		//
		Random rnd = new Random();
		//
		for (int i = 0; i < 30; i++) {
			float sizeWH = rnd.nextInt(60) + 40;
            SmartActor currentBalloon = new SmartActor(sizeWH, sizeWH, rnd,
					true);
			//
			currentBalloon.setTextureRegion(Assets.img_obj_circle, true);
			currentBalloon.setOrigin(currentBalloon.getWidth() / 2,
					currentBalloon.getHeight() / 2);
			currentBalloon.startActionMoveFreely(15,
					(int) AppSettings.SCREEN_W, (int) AppSettings.SCREEN_H, 6);
			currentBalloon.startActionScale(10, 2, 2, 3, true);
			currentBalloon.startActionFadeInOut(10, 3, true);
			//
			jungleMainMenuScreen.backgroundBalloons.add(currentBalloon);
			jungleMainMenuScreen.getStage().addActor(currentBalloon);
		}
	}

	public void setUpFlowers(final JungleMainMenuScreen jungleMainMenuScreen) {
		jungleMainMenuScreen.flowers = new ArrayList<SmartActor>();
		//
		Random rnd = new Random();
		//
		int size = 15;
		for (int i = 0; i < size; i++) {
			float sizeRatio = rnd.nextInt(90) + 10;
			float wid = 140 * sizeRatio / 100;
			float hei = 180 * sizeRatio / 100;
			// Position X nicely along the screen (Natural looking)
			float posX = i * (AppSettings.SCREEN_W / size);
			//
            SmartActor currentFlower = new SmartActor(wid, hei, rnd, true);
			// Increase varitaion with 2 different flowers as textures
			if (i % 2 == 0) {
				currentFlower.setTextureRegion(Assets.img_obj_flower_1_, true);
			} else {
				currentFlower.setTextureRegion(Assets.img_obj_flower_2_, true);
			}
			currentFlower.setOrigin(currentFlower.getWidth() / 2.0f,
					currentFlower.getOriginY());
			currentFlower.startActionScale(10, 1.3f, 1.3f, 3, true);
			//
			currentFlower.setPosition(posX, 0);
			//
			jungleMainMenuScreen.flowers.add(currentFlower);
			jungleMainMenuScreen.getStage().addActor(currentFlower);
		}
	}
}
