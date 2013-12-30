/*******************************************************************************
 * Copyright 2012-Present, MoribitoTech
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.moribitotech.mtx.scene2d.effects;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.moribitotech.mtx.settings.MtxLogger;

public class EffectCreator {
	private static final String logTag = "MtxEffectCreator";
	public static boolean logActive = true;

	//
	// COMMAND SHORTCUTS
	// ################################################################
	//
	// SC - Scale
	// BTN - Back To Normal
	// FI - Fade In
	// FO - Fade Out
	// SHK - Shake
	// MT - Move To

	//
	// SINGLE EFFECTS
	// ################################################################
	/**
	 * Scale effect (SC)
	 * */
	public static void create_SC(Actor actor, float scaleRatioX,
			float scaleRatioY, float duration, final Group group,
			final boolean removeActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					new Action() {
						@Override
						public boolean act(float delta) {
							if (removeActor) {
								removeActor(group, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	/**
	 * Fade Out (FO)
	 * */
	public static void create_FO(Actor actor, float duration, float delay,
			final Group group, final boolean removeActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(Actions.delay(delay),
					Actions.fadeOut(duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (removeActor) {
								removeActor(group, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	/**
	 * Fade In (FI)
	 * */
	public static void create_FI(Actor actor, float duration, float delay,
			final Group group, final boolean removeActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(Actions.delay(delay),
					Actions.fadeIn(duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (removeActor) {
								removeActor(group, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	/**
	 * Move to a position
	 * */
	public static void create_MT(Actor actor, float duration,
			float delayBefore, float posX, float posY, final Group group,
			final boolean removeActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(Actions.delay(delayBefore),
					Actions.moveTo(posX, posY, duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (removeActor) {
								removeActor(group, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	/**
	 * Shake effect (SHK)
	 * */
	public static void create_SHK(Actor actor, float shakeAngle,
			float originalAngle, float duration, final Group group,
			final boolean removeActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.rotateTo(shakeAngle, duration),
					Actions.rotateTo(-shakeAngle, duration),
					Actions.rotateTo(originalAngle, duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (removeActor) {
								removeActor(group, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	//
	// DOUBLE EFFECTS (Sequence - Chain reaction)
	// ################################################################
	/**
	 * Scale effect and Back to previous scale (SC, BTN)
	 * */
	public static void create_SC_BTN(Actor actor, float scaleRatioX,
			float scaleRatioY, float duration, final Group group,
			final boolean removeActor) {
		if (actor != null) {
			float originalScaleX = actor.getScaleX();
			float originalScaleY = actor.getScaleY();
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					Actions.scaleTo(originalScaleX, originalScaleY, duration),
					new Action() {
						@Override
						public boolean act(float delta) {
							if (removeActor) {
								removeActor(group, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	/**
	 * Scale effect and Back to original (1.0f)
	 * */
	public static void create_SC_BTO(Actor actor, float scaleRatioX,
			float scaleRatioY, float duration, final Group group,
			final boolean removeActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					Actions.scaleTo(1.0f, 1.0f, duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (removeActor) {
								removeActor(group, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	/**
	 * Scale effect, Fade Out (SC, FO)
	 * */
	public static void create_SC_FO(Actor actor, float scaleRatioX,
			float scaleRatioY, float duration, float delayBeforeFadeOut,
			final Group group, final boolean removeActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					Actions.delay(delayBeforeFadeOut),
					Actions.fadeOut(duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (removeActor) {
								removeActor(group, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	/**
	 * Scale effect, Shake effect (SC, SHK)
	 * */
	public static void create_SC_SHK(Actor actor, float scaleRatioX,
			float scaleRatioY, float shakeAngle, float originalAngle,
			float duration, final Group group, final boolean removeActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					Actions.rotateTo(shakeAngle, duration),
					Actions.rotateTo(-shakeAngle, duration),
					Actions.rotateTo(originalAngle, duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (removeActor) {
								removeActor(group, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	//
	// TRIPLE EFFECTS (Sequence - Chain reaction)
	// ################################################################
	/**
	 * Scale effect, Back To Normal, Fade Out (SC, BTN, FO)
	 * */
	public static void create_SC_BTN_FO(Actor actor, float scaleRatioX,
			float scaleRatioY, float duration, float delayBeforeFadeOut,
			final Group group, final boolean removeActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					Actions.scaleTo(1, 1, duration),
					Actions.delay(delayBeforeFadeOut),
					Actions.fadeOut(duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (removeActor) {
								removeActor(group, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	/**
	 * Scale effect, Shake effect, Back To Normal (SC, SHK, BTN)
	 * */
	public static void create_SC_SHK_BTN(Actor actor, float scaleRatioX,
			float scaleRatioY, float shakeAngle, float originalAngle,
			float duration, final Group group, final boolean removeActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					Actions.rotateTo(shakeAngle, duration),
					Actions.rotateTo(-shakeAngle, duration),
					Actions.rotateTo(originalAngle, duration),
					Actions.scaleTo(1, 1, duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (removeActor) {
								removeActor(group, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	private static void removeActor(Group group, Actor actor) {
		if (group != null && actor != null) {
			actor.clearActions();
			String actorName = actor.getName();
			if (group.removeActor(actor)) {
				MtxLogger.log(logActive, true, logTag, "Actor removed! (Name: "
						+ actorName + ")");
			} else {
				MtxLogger.log(logActive, true, logTag,
						"Actor not removed! (Name: " + actorName + ")");
			}
		}
	}
}
