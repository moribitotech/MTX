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

package com.moribitotech.mtx.managers;

import com.badlogic.gdx.Gdx;

public class VibrationManager {
	// Milliseconds (Mostly from MORSE CODE)
	public static final int BUZZ = 50;
	public static final int DOT = 100;
	public static final int DASH = 500;
	public static final int SHORT_GAP = 200;
	public static final int MEDIUM_GAP = 500;
	public static final int LONG_GAP = 1000;

	public VibrationManager() {
	}

	/**
	 * Vibrate, it only vibrates if SettingsManager.isVibrationOn(), 1000ms is 1
	 * second, also requires "android.permission.VIBRATE" in android manifest
	 * */
	public void vibrate(int milliseconds) {
		if (SettingsManager.isVibrationOn()) {
			Gdx.input.vibrate(milliseconds);
		}
	}

	/**
	 * Vibrate with a pattern, it only vibrates if
	 * SettingsManager.isVibrationOn(), 1000ms is 1 second, also requires
	 * "android.permission.VIBRATE" in android manifest
	 * */
	public void vibrate(long[] pattern, int repeat) {
		if (SettingsManager.isVibrationOn()) {
			Gdx.input.vibrate(pattern, repeat);
		}
	}

	/**
	 * Cancel vibration
	 * */
	public void cancel() {
		Gdx.input.cancelVibrate();
	}
}
