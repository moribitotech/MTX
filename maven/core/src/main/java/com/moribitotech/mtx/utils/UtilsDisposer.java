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

package com.moribitotech.mtx.utils;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.scene2d.AbstractActor;
import com.moribitotech.mtx.scene2d.AbstractActorLight;
import com.moribitotech.mtx.scene2d.models.EmptyActor;
import com.moribitotech.mtx.scene2d.models.EmptyActorLight;
import com.moribitotech.mtx.scene2d.models.SmartActor;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.mtx.settings.MtxLogger;

public class UtilsDisposer {
	protected final static String logTag = "MtxUtilsDisposerLog";
	public static boolean logActive = true;

	/**
	 * Dispose actor from a group
	 * */
	public static void disposeActor(Group group, Actor actorToBeDisposed) {
		if (group != null && actorToBeDisposed != null) {
			try {
				actorToBeDisposed.clear();
				if (group.removeActor(actorToBeDisposed))
					log("Actor disposed", actorToBeDisposed);
			} catch (Exception e) {
				log("Actor dispose FAIL!", actorToBeDisposed);
			}
		}
	}

	/**
	 * Dispose group from a group
	 * */
	public static void disposeGroup(Group group, Group groupToBeDisposed) {
		if (group != null && groupToBeDisposed != null) {
			try {
				groupToBeDisposed.clear();
				if (group.removeActor(groupToBeDisposed))
					log("Actor disposed", groupToBeDisposed);
			} catch (Exception e) {
				log("Actor dispose FAIL!", groupToBeDisposed);
			}
		}
	}

	/**
	 * Dispose empty actor from a group
	 * */
	public static void disposeEmptyActor(Group group, EmptyActor emptyActor) {
		if (group != null && emptyActor != null) {
			try {
				emptyActor.clear();
				if (group.removeActor(emptyActor))
					log("Actor disposed", emptyActor);
			} catch (Exception e) {
				log("Actor dispose FAIL!", emptyActor);
			}
		}
	}

	/**
	 * Dispose empty actor light from a group
	 * */
	public static void disposeEmptyActorLight(Group group,
			EmptyActorLight emptyActorLight) {
		if (group != null && emptyActorLight != null) {
			try {
				emptyActorLight.clear();
				if (group.removeActor(emptyActorLight))
					log("Group disposed", emptyActorLight);
			} catch (Exception e) {
				log("Group dispose FAIL!", emptyActorLight);
			}
		}
	}

	/**
	 * Dispose abstract actor list from a group
	 * */
	public static void disposeAbstractActorList(Group group,
			ArrayList<AbstractActor> list) {
		if (group != null && list != null) {
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					AbstractActor abstractActor = list.get(i);
					try {
						abstractActor.clear();
						if (group.removeActor(abstractActor))
							log("Actor disposed", abstractActor);
					} catch (Exception e) {
						log("Actor dispose FAIL!", abstractActor);
					}
				}
			}
		}
	}

	/**
	 * Dispose abstract actor light list from a group
	 * */
	public static void disposeAbstractActorLightList(Group group,
			ArrayList<AbstractActorLight> list) {
		if (group != null && list != null) {
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					AbstractActorLight abstractActorLight = list.get(i);
					try {
						abstractActorLight.clear();
						if (group.removeActor(abstractActorLight))
							log("Actor disposed", abstractActorLight);
					} catch (Exception e) {
						log("Actor dispose FAIL!", abstractActorLight);
					}
				}
			}
		}
	}

	/**
	 * Dispose smart model list from a group
	 * */
	public static void disposeSmartModelList(Group group,
			ArrayList<SmartActor> list) {
		if (group != null && list != null) {
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					SmartActor sm = list.get(i);
					try {
						sm.clear();
						if (group.removeActor(sm))
							log("Smart Model disposed", sm);
					} catch (Exception e) {
						log("Smart Model dispose FAIL!", sm);
					}
				}
			}
		}
	}

	/**
	 * Dispose screen, at the end sets screen as "null"
	 * <p>
	 * - Disposing stage<br>
	 * */
	public static void disposeScreen(AbstractScreen screen) {
		if (screen.getStage() != null) {
			screen.getStage().dispose();
			MtxLogger.log(logActive, true, logTag,
					"Disposed Stage: " + screen.getScreenName());
			screen = null;
		}
	}

	/**
	 * Dispose game
	 * <p>
	 * - Disposing asset manager<br>
	 * - Disposing skin<br>
	 * */
	public static void disposeGame(AbstractGame game) {
		if (game.getAssets().getAssetManager() != null) {
			game.getAssets().getAssetManager().dispose();
			MtxLogger.log(logActive, true, logTag, "Disposed AssetManager");
		}

		if (game.getAssets().getSkin() != null) {
			game.getAssets().getSkin().dispose();
			MtxLogger.log(logActive, true, logTag, "Disposed Skin");
		}
	}

	/** Easy logger */
	private static void log(String msj, Actor actor) {
		MtxLogger.log(logActive, true, logTag,
				msj + " (Actor Name: " + actor.getName() + ")");
	}
}
