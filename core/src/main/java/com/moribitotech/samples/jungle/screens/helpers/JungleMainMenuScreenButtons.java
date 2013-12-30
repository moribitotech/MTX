package com.moribitotech.samples.jungle.screens.helpers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.moribitotech.mtx.scene2d.effects.EffectCreator;
import com.moribitotech.mtx.scene2d.ui.TableModel;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.samples.jungle.assets.Assets;
import com.moribitotech.samples.jungle.buttons.JungleGameButton;
import com.moribitotech.samples.jungle.screens.JungleMainMenuScreen;

import java.util.Random;

public class JungleMainMenuScreenButtons {

	public void setUpMainMenuButtons(
			final JungleMainMenuScreen jungleMainMenuScreen) {

		//
		// Menu Table (Play, Scores, Settings)
		// #################################################################
		jungleMainMenuScreen.menuTable = new TableModel(null,
				AppSettings.SCREEN_W, AppSettings.SCREEN_H);
		jungleMainMenuScreen.menuTable.setPosition(0,
				-120 * AppSettings.getWorldPositionYRatio());

		//
		// Btn Values
		// #################################################################
		Random rnd = new Random();
		float btnWidth = 270f;
		float btnHeight = 90f;

		//
		// Btn Play
		// #################################################################
		jungleMainMenuScreen.btnPlay = new JungleGameButton(btnWidth,
				btnHeight, rnd, true);
		jungleMainMenuScreen.btnPlay.setTextureRegion(Assets.img_obj_btn_play,
				true);
		jungleMainMenuScreen.btnPlay.setOrigin(
				jungleMainMenuScreen.btnPlay.getWidth() / 2.0f,
				jungleMainMenuScreen.btnPlay.getHeight() / 2.0f);
		jungleMainMenuScreen.btnPlay.addListener(new ActorGestureListener() {
			@Override
			public void touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchDown(event, x, y, pointer, button);
				//
				jungleMainMenuScreen.btnPlay.clearActions();
				EffectCreator.create_SC_SHK_BTN(jungleMainMenuScreen.btnPlay,
                        1.3f, 1.3f, 5f, 0, 0.05f, null, false);
			}

		});

		//
		// Btn Scores
		// #################################################################
		jungleMainMenuScreen.btnScores = new JungleGameButton(btnWidth,
				btnHeight, rnd, true);
		jungleMainMenuScreen.btnScores.setTextureRegion(
				Assets.img_obj_btn_scores, true);
		jungleMainMenuScreen.btnScores.setOrigin(
				jungleMainMenuScreen.btnScores.getWidth() / 2.0f,
				jungleMainMenuScreen.btnScores.getHeight() / 2.0f);
		jungleMainMenuScreen.btnScores.addListener(new ActorGestureListener() {
			@Override
			public void touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchDown(event, x, y, pointer, button);
				//
				jungleMainMenuScreen.btnScores.clearActions();
				EffectCreator.create_SC_SHK_BTN(jungleMainMenuScreen.btnScores,
						1.3f, 1.3f, 5f, 0, 0.05f, null, false);
				//
			}
		});

		//
		// Btn Settings
		// #################################################################
		jungleMainMenuScreen.btnSettings = new JungleGameButton(btnWidth,
				btnHeight, rnd, true);
		jungleMainMenuScreen.btnSettings.setTextureRegion(
				Assets.img_obj_btn_settings, true);
		jungleMainMenuScreen.btnSettings.setOrigin(
				jungleMainMenuScreen.btnSettings.getWidth() / 2.0f,
				jungleMainMenuScreen.btnSettings.getHeight() / 2.0f);
		jungleMainMenuScreen.btnSettings
				.addListener(new ActorGestureListener() {
					@Override
					public void touchDown(InputEvent event, float x, float y,
							int pointer, int button) {
						super.touchDown(event, x, y, pointer, button);
						//
						jungleMainMenuScreen.btnSettings.clearActions();
						EffectCreator.create_SC_SHK_BTN(
								jungleMainMenuScreen.btnSettings, 1.3f, 1.3f,
								5f, 0, 0.05f, null, false);
						//
					}
				});

		//
		// Scale them to "0", we will send them in after splash completed
		// #################################################################
		jungleMainMenuScreen.btnPlay.setScale(0f);
		jungleMainMenuScreen.btnScores.setScale(0f);
		jungleMainMenuScreen.btnSettings.setScale(0f);

		//
		// Add
		// #################################################################
		jungleMainMenuScreen.menuTable.add(jungleMainMenuScreen.btnPlay).pad(0);
		jungleMainMenuScreen.menuTable.row();
		jungleMainMenuScreen.menuTable.add(jungleMainMenuScreen.btnScores).pad(
				0);
		jungleMainMenuScreen.menuTable.row();
		jungleMainMenuScreen.menuTable.add(jungleMainMenuScreen.btnSettings)
				.pad(0);
		//
		jungleMainMenuScreen.getStage()
				.addActor(jungleMainMenuScreen.menuTable);

	}

	public void setUpSocialButtons(
			final JungleMainMenuScreen jungleMainMenuScreen) {
		//
		// Btn Values
		// #################################################################
		Random rnd = new Random();
		float btnWidth = 80f;
		float btnHeight = 130f;
		float initialPosX = 30 * AppSettings.getWorldPositionXRatio();
		float initialposY = AppSettings.SCREEN_H;
		final float touchAnimationDuration = 0.05f;

		//
		// Btn Facebook
		// #################################################################
		jungleMainMenuScreen.btnSocialFacebook = new JungleGameButton(btnWidth,
				btnHeight, rnd, true);
		jungleMainMenuScreen.btnSocialFacebook.setTextureRegion(
				Assets.img_obj_social_facebook, true);
		jungleMainMenuScreen.btnSocialFacebook.setOrigin(
				jungleMainMenuScreen.btnSocialFacebook.getWidth() / 2.0f,
				jungleMainMenuScreen.btnSocialFacebook.getHeight() / 2.0f);
		jungleMainMenuScreen.btnSocialFacebook
				.addListener(new ActorGestureListener() {

					@Override
					public void touchDown(InputEvent event, float x, float y,
							int pointer, int button) {
						super.touchDown(event, x, y, pointer, button);
						//
						jungleMainMenuScreen.btnSocialFacebook.addAction(Actions.moveTo(
								jungleMainMenuScreen.btnSocialFacebook.getX(),
								AppSettings.SCREEN_H
										- jungleMainMenuScreen.btnSocialFacebook
												.getHeight(),
								touchAnimationDuration));
					}

					@Override
					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {
						super.touchDown(event, x, y, pointer, button);
						//
						jungleMainMenuScreen.btnSocialFacebook.addAction(Actions.moveTo(
								jungleMainMenuScreen.btnSocialFacebook.getX(),
								AppSettings.SCREEN_H
										- jungleMainMenuScreen.btnSocialFacebook
												.getHeight() / 1.25f,
								touchAnimationDuration));
					}
				});

		//
		// Btn Twitter
		// #################################################################
		jungleMainMenuScreen.btnSocialTwitter = new JungleGameButton(btnWidth,
				btnHeight, rnd, true);
		jungleMainMenuScreen.btnSocialTwitter.setTextureRegion(
				Assets.img_obj_social_twitter, true);
		jungleMainMenuScreen.btnSocialTwitter.setOrigin(
				jungleMainMenuScreen.btnSocialTwitter.getWidth() / 2.0f,
				jungleMainMenuScreen.btnSocialTwitter.getHeight() / 2.0f);
		jungleMainMenuScreen.btnSocialTwitter
				.addListener(new ActorGestureListener() {
					@Override
					public void touchDown(InputEvent event, float x, float y,
							int pointer, int button) {
						super.touchDown(event, x, y, pointer, button);
						//
						jungleMainMenuScreen.btnSocialTwitter.addAction(Actions.moveTo(
								jungleMainMenuScreen.btnSocialTwitter.getX(),
								AppSettings.SCREEN_H
										- jungleMainMenuScreen.btnSocialTwitter
												.getHeight(),
								touchAnimationDuration));
					}

					@Override
					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {
						super.touchDown(event, x, y, pointer, button);
						//
						jungleMainMenuScreen.btnSocialTwitter.addAction(Actions.moveTo(
								jungleMainMenuScreen.btnSocialTwitter.getX(),
								AppSettings.SCREEN_H
										- jungleMainMenuScreen.btnSocialTwitter
												.getHeight() / 1.25f,
								touchAnimationDuration));
					}
				});

		//
		// Btn Google+
		// #################################################################
		jungleMainMenuScreen.btnSocialGoogle = new JungleGameButton(btnWidth,
				btnHeight, rnd, true);
		jungleMainMenuScreen.btnSocialGoogle.setTextureRegion(
				Assets.img_obj_social_google, true);
		jungleMainMenuScreen.btnSocialGoogle.setOrigin(
				jungleMainMenuScreen.btnSocialGoogle.getWidth() / 2.0f,
				jungleMainMenuScreen.btnSocialGoogle.getHeight() / 2.0f);
		jungleMainMenuScreen.btnSocialGoogle
				.addListener(new ActorGestureListener() {
					@Override
					public void touchDown(InputEvent event, float x, float y,
							int pointer, int button) {
						super.touchDown(event, x, y, pointer, button);
						//
						jungleMainMenuScreen.btnSocialGoogle.addAction(Actions.moveTo(
								jungleMainMenuScreen.btnSocialGoogle.getX(),
								AppSettings.SCREEN_H
										- jungleMainMenuScreen.btnSocialGoogle
												.getHeight(),
								touchAnimationDuration));
					}

					@Override
					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {
						super.touchDown(event, x, y, pointer, button);
						//
						jungleMainMenuScreen.btnSocialGoogle.addAction(Actions.moveTo(
								jungleMainMenuScreen.btnSocialGoogle.getX(),
								AppSettings.SCREEN_H
										- jungleMainMenuScreen.btnSocialGoogle
												.getHeight() / 1.25f,
								touchAnimationDuration));
					}
				});

		//
		// Construct Position
		// #################################################################
		jungleMainMenuScreen.btnSocialFacebook.setPosition(initialPosX,
				initialposY);
		jungleMainMenuScreen.btnSocialTwitter.setPosition(
				jungleMainMenuScreen.btnSocialFacebook.getX()
						+ jungleMainMenuScreen.btnSocialFacebook.getWidth(),
				initialposY);
		jungleMainMenuScreen.btnSocialGoogle.setPosition(
				jungleMainMenuScreen.btnSocialTwitter.getX()
						+ jungleMainMenuScreen.btnSocialTwitter.getWidth(),
				initialposY);

		//
		// Add
		// #################################################################
		jungleMainMenuScreen.getStage().addActor(
				jungleMainMenuScreen.btnSocialFacebook);
		jungleMainMenuScreen.getStage().addActor(
				jungleMainMenuScreen.btnSocialTwitter);
		jungleMainMenuScreen.getStage().addActor(
				jungleMainMenuScreen.btnSocialGoogle);
	}

	public void sendInMainMenuButtons(
			final JungleMainMenuScreen jungleMainMenuScreen) {
		EffectCreator.create_SC_BTO(jungleMainMenuScreen.btnPlay, 1.3f, 1.3f,
				0.4f, null, false);

		EffectCreator.create_SC_BTO(jungleMainMenuScreen.btnScores, 1.3f, 1.3f,
				0.6f, null, false);

		EffectCreator.create_SC_BTO(jungleMainMenuScreen.btnSettings, 1.3f,
				1.3f, 0.8f, null, false);
		//
		jungleMainMenuScreen.btnPlay.setTouchable(Touchable.enabled);
		jungleMainMenuScreen.btnScores.setTouchable(Touchable.enabled);
		jungleMainMenuScreen.btnSettings.setTouchable(Touchable.enabled);
	}

	public void sendAwayMainMenuButtons(
			final JungleMainMenuScreen jungleMainMenuScreen) {
		EffectCreator.create_SC(jungleMainMenuScreen.btnPlay, 0f, 0f, 0.4f,
				null, false);
		EffectCreator.create_SC(jungleMainMenuScreen.btnScores, 0f, 0f, 0.6f,
				null, false);

		EffectCreator.create_SC(jungleMainMenuScreen.btnSettings, 0f, 0f, 0.8f,
				null, false);
		//
		jungleMainMenuScreen.btnPlay.setTouchable(Touchable.disabled);
		jungleMainMenuScreen.btnScores.setTouchable(Touchable.disabled);
		jungleMainMenuScreen.btnSettings.setTouchable(Touchable.disabled);
	}

	public void sendInSocialButtons(JungleMainMenuScreen jungleMainMenuScreen) {
		//
		float duration = 0.6f;
		//
		jungleMainMenuScreen.btnSocialFacebook
				.addAction(Actions.moveTo(
						jungleMainMenuScreen.btnSocialFacebook.getX(),
						AppSettings.SCREEN_H
								- (jungleMainMenuScreen.btnSocialFacebook
										.getHeight() / 1.25f), duration));
		jungleMainMenuScreen.btnSocialTwitter
				.addAction(Actions.moveTo(
						jungleMainMenuScreen.btnSocialTwitter.getX(),
						AppSettings.SCREEN_H
								- (jungleMainMenuScreen.btnSocialTwitter
										.getHeight() / 1.25f), duration + 0.2f));

		jungleMainMenuScreen.btnSocialGoogle
				.addAction(Actions.moveTo(
						jungleMainMenuScreen.btnSocialGoogle.getX(),
						AppSettings.SCREEN_H
								- (jungleMainMenuScreen.btnSocialGoogle
										.getHeight() / 1.25f), duration + 0.4f));
		//
		jungleMainMenuScreen.btnSocialFacebook.setTouchable(Touchable.enabled);
		jungleMainMenuScreen.btnSocialGoogle.setTouchable(Touchable.enabled);
		jungleMainMenuScreen.btnSocialTwitter.setTouchable(Touchable.enabled);

	}

	public void sendAwaySocialButtons(JungleMainMenuScreen jungleMainMenuScreen) {
		//
		float duration = 0.6f;
		//
		jungleMainMenuScreen.btnSocialFacebook.addAction(Actions.moveTo(
				jungleMainMenuScreen.btnSocialFacebook.getX(),
				AppSettings.SCREEN_H, duration));
		jungleMainMenuScreen.btnSocialTwitter.addAction(Actions.moveTo(
				jungleMainMenuScreen.btnSocialTwitter.getX(),
				AppSettings.SCREEN_H, duration + 0.2f));

		jungleMainMenuScreen.btnSocialGoogle.addAction(Actions.moveTo(
				jungleMainMenuScreen.btnSocialGoogle.getX(),
				AppSettings.SCREEN_H, duration + 0.4f));
		//
		jungleMainMenuScreen.btnSocialFacebook.setTouchable(Touchable.disabled);
		jungleMainMenuScreen.btnSocialGoogle.setTouchable(Touchable.disabled);
		jungleMainMenuScreen.btnSocialTwitter.setTouchable(Touchable.disabled);
	}

	public void setUpSwipeButtons(
			final JungleMainMenuScreen jungleMainMenuScreen) {
		Random rnd = new Random();
		float btnWidth = 220f / 1.2f;
		float btnHeight = 140f / 1.2f;

		//
		// Btn Start
		// #################################################################
		jungleMainMenuScreen.btnSwipeForMenu = new JungleGameButton(btnWidth,
				btnHeight, rnd, true);
		jungleMainMenuScreen.btnSwipeForMenu.setTextureRegion(
				Assets.img_obj_swipe_down_menu, true);
		jungleMainMenuScreen.btnSwipeForMenu.setOrigin(
				jungleMainMenuScreen.btnSwipeForMenu.getWidth(), 0);

		jungleMainMenuScreen.btnSwipeForMenu.setPosition(AppSettings.SCREEN_W
				- jungleMainMenuScreen.btnSwipeForMenu.getWidth(), 0);

		//
		// Btn Start
		// #################################################################
		jungleMainMenuScreen.btnSwipeForInstructions = new JungleGameButton(
				btnWidth, btnHeight, rnd, true);
		jungleMainMenuScreen.btnSwipeForInstructions.setTextureRegion(
				Assets.img_obj_swipe_up_instructions, true);
		jungleMainMenuScreen.btnSwipeForInstructions.setOrigin(
				jungleMainMenuScreen.btnSwipeForInstructions.getWidth(), 0);

		jungleMainMenuScreen.btnSwipeForInstructions.setPosition(
				AppSettings.SCREEN_W
						- jungleMainMenuScreen.btnSwipeForInstructions
								.getWidth(), 0);

		//
		// Add
		// #################################################################
		jungleMainMenuScreen.btnSwipeForMenu.setScale(0f);
		jungleMainMenuScreen.btnSwipeForInstructions.setScale(0f);

		//
		// Add
		// #################################################################
		jungleMainMenuScreen.getStage().addActor(
				jungleMainMenuScreen.btnSwipeForMenu);
		jungleMainMenuScreen.getStage().addActor(
				jungleMainMenuScreen.btnSwipeForInstructions);
	}

	public void sendInSwipeForMenu(
			final JungleMainMenuScreen jungleMainMenuScreen) {
		EffectCreator.create_SC(jungleMainMenuScreen.btnSwipeForMenu, 1f, 1f,
				0.5f, null, false);
	}

	public void sendAwaySwipeForMenu(
			final JungleMainMenuScreen jungleMainMenuScreen) {
		EffectCreator.create_SC(jungleMainMenuScreen.btnSwipeForMenu, 0f, 0f,
				0.5f, null, false);
	}

	public void sendInSwipeForInstruction(
			final JungleMainMenuScreen jungleMainMenuScreen) {
		EffectCreator.create_SC(jungleMainMenuScreen.btnSwipeForInstructions,
				1f, 1f, 0.5f, null, false);
	}

	public void sendAwaySwipeForInstructions(
			final JungleMainMenuScreen jungleMainMenuScreen) {
		EffectCreator.create_SC(jungleMainMenuScreen.btnSwipeForInstructions,
				0f, 0f, 0.5f, null, false);
	}
}
