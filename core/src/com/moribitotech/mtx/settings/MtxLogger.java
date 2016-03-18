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

package com.moribitotech.mtx.settings;

import com.badlogic.gdx.Gdx;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.game.AbstractGameManager;
import com.moribitotech.mtx.input.InputIntent;
import com.moribitotech.mtx.scene2d.AbstractActor;
import com.moribitotech.mtx.scene2d.AbstractActorLight;
import com.moribitotech.mtx.scene2d.AbstractGroup;
import com.moribitotech.mtx.scene2d.AbstractGroupLight;
import com.moribitotech.mtx.scene2d.AbstractWorldScene2d;
import com.moribitotech.mtx.scene2d.collision.CollisionDetector;
import com.moribitotech.mtx.scene2d.effects.EffectCreator;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.mtx.utils.UtilsDisposer;

public class MtxLogger {
	private static boolean isMasterLoggerActive = false;

	/**
	 * Set logs that you want to see, or kill all logs with master logger,
	 * default masterlog is false
	 * */
	public static void setLogs(boolean isMasterLoggerActive) {
		// Master log (To show or not to show any log)
		MtxLogger.isMasterLoggerActive = isMasterLoggerActive;

		// AppSettings
		AppSettings.Log_Active = true;

		// Game
		AbstractGame.logActive = true;
		AbstractScreen.logActive = true;
		AbstractGameManager.logActive = true;

		// Scene 2D
		AbstractActor.logActive = true;
		AbstractActorLight.logActive = true;
		AbstractGroup.logActive = true;
		AbstractGroupLight.logActive = true;
		AbstractWorldScene2d.logActive = true;

		// Input
		InputIntent.logActive = false;

		// Helpers
		EffectCreator.logActive = false;
		CollisionDetector.logActive = false;

		// Utilizers
		UtilsDisposer.logActive = true;
	}

	/**
	 * Log something
	 * */
	public static void log(boolean objectLoggerActive,
			boolean methodLoggerActive, String tag, String log) {
		// Log
		if (isMasterLoggerActive && objectLoggerActive && methodLoggerActive) {
			Gdx.app.log(tag, log);
		}
	}
}
