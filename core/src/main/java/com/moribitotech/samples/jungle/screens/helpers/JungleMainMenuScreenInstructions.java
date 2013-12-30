package com.moribitotech.samples.jungle.screens.helpers;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.moribitotech.mtx.scene2d.models.EmptyActorLight;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.samples.jungle.assets.Assets;
import com.moribitotech.samples.jungle.screens.JungleMainMenuScreen;

public class JungleMainMenuScreenInstructions {

	public void setUpInstructions(
			final JungleMainMenuScreen jungleMainMenuScreen) {
		jungleMainMenuScreen.instructions = new EmptyActorLight(
				AppSettings.SCREEN_W, AppSettings.SCREEN_H, false);
		jungleMainMenuScreen.instructions.setTextureRegion(
				Assets.img_obj_rectangle, true);
		jungleMainMenuScreen.instructions.setPosition(0 - AppSettings.SCREEN_W,
				0);

		//
		jungleMainMenuScreen.getStage().addActor(
				jungleMainMenuScreen.instructions);
	}

	//
	public void sendInInstructions(
			final JungleMainMenuScreen jungleMainMenuScreen) {
		//
		float widthAsX = jungleMainMenuScreen.btnSwipeForMenu.getWidth();
		jungleMainMenuScreen.instructions.addAction(Actions.moveTo(
				0 - widthAsX, 0, 0.5f));
	}

	public void sendAwayInstructions(
			final JungleMainMenuScreen jungleMainMenuScreen) {
		//
		jungleMainMenuScreen.instructions.addAction(Actions.moveTo(
				0 - AppSettings.SCREEN_W, 0, 0.5f));
	}
}
