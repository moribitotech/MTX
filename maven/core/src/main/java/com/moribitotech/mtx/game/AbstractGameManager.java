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

package com.moribitotech.mtx.game;

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.mtx.settings.MtxLogger;

public abstract class AbstractGameManager {
	//
	protected final String logTag = "MtxGameManagerLog";
	public static boolean logActive = true;

	// Game manager helpers
	private GameState gameState;
	private Stage stage;
	private AbstractScreen screen;
	private Random random;
	private Timer timer;

	public AbstractGameManager(Stage stage, AbstractScreen screen) {
		this.stage = stage;
		this.screen = screen;
		random = new Random();
		timer = new Timer();
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
		MtxLogger.log(logActive, true, logTag,
				"Game State: " + gameState.toString());
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public AbstractScreen getScreen() {
		return screen;
	}

	public void setScreen(AbstractScreen screen) {
		this.screen = screen;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
}
