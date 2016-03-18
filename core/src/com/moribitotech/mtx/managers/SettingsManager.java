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
import com.badlogic.gdx.Preferences;
import com.moribitotech.mtx.settings.MtxLogger;

public class SettingsManager {
	//
	private static final String logTag = "MtxSettingsManagerLog";
	public static boolean logActive = true;

	// Public values
	public static final String PREFS_FILE_NAME = "MyPreferences";
	public static final Preferences prefs = Gdx.app
			.getPreferences(PREFS_FILE_NAME);

	// Sound, Music, Vibration
	private static boolean isSoundOn = false;
	private static boolean isMusicOn = false;
	private static boolean isVibrationOn = false;

	// First Launch
	private static final String KEY_FIRST_LAUNCH_DONE = "firstLaunch";

	// General Settings
	private static final String KEY_MUSIC = "musicSetting";
	private static final String KEY_SOUND = "soundEffectSetting";
	private static final String KEY_VIBRATION = "vibrationSetting";

	/**
	 * Set music on/off, it sets to android preferences and isMusicOn in
	 * SettingManager
	 * */
	public static void setMusic(boolean isMusicActive) {
		if (isMusicActive) {
			setBooleanPrefValue(KEY_MUSIC, true);
			setSettings();
		} else {
			setBooleanPrefValue(KEY_MUSIC, false);
			setSettings();
		}
	}

	/**
	 * Get music if on/off
	 * */
	public static boolean isMusicOn() {
		return isMusicOn;
	}

	/**
	 * Get music if on/off from preferences, do not use this in game, you would
	 * not want to access android preferences many times during the game
	 * */
	public static boolean isMusicOnFromPreferences() {
		return getBooleanPrefValue(KEY_MUSIC, false);
	}

	/**
	 * Set sound on/off, it sets to android preferences and isSoundOn in
	 * SettingManager
	 * */
	public static void setSound(boolean isSoundActive) {
		if (isSoundActive) {
			setBooleanPrefValue(KEY_SOUND, true);
			setSettings();
		} else {
			setBooleanPrefValue(KEY_SOUND, false);
			setSettings();
		}
	}

	/**
	 * Get sound if on/off
	 * */
	public static boolean isSoundOn() {
		return isSoundOn;
	}

	/**
	 * Get sound if on/off from preferences, do not use this in game, you would
	 * not want to access android preferences many times during the game
	 * */
	public static boolean isSoundOnFromPreferences() {
		return getBooleanPrefValue(KEY_SOUND, false);
	}

	/**
	 * Set vibration on/off, it sets to android preferences and isVibrationOn in
	 * SettingManager
	 * */
	public static void setVibration(boolean isVibrationActive) {
		if (isVibrationActive) {
			setBooleanPrefValue(KEY_VIBRATION, true);
			setSettings();
		} else {
			setBooleanPrefValue(KEY_VIBRATION, false);
			setSettings();
		}
	}

	/**
	 * Get vibration if on/off
	 * */
	public static boolean isVibrationOn() {
		return isVibrationOn;
	}

	/**
	 * Get vibration if on/off from preferences, do not use this in game, you
	 * would not want to access android preferences many times during the game
	 * */
	public static boolean isVibrationOnFromPreferences() {
		return getBooleanPrefValue(KEY_VIBRATION, false);
	}

	/**
	 * Set static variables due to android preferences (isSoundOn, isMusicOn,
	 * isVibrationOn) This method also called in resume() of "AbstractGame" for
	 * %100 guarantee of static variables are set correctly after pause/resume
	 * of the game
	 * */
	public static void setSettings() {
		if (isMusicOnFromPreferences()) {
			isMusicOn = true;
		} else {
			isMusicOn = false;
		}
		//
		if (isSoundOnFromPreferences()) {
			isSoundOn = true;
		} else {
			isSoundOn = false;
		}
		//
		if (isVibrationOnFromPreferences()) {
			isVibrationOn = true;
		} else {
			isVibrationOn = false;
		}
	}

	/**
	 * Set as first launch for the app. It saves to Android Preferences.
	 * 
	 * @param isFirstLaunchDone
	 *            value to set as first launch
	 * */
	public static void setFirstLaunchDone(boolean isFirstLaunchDone) {
		if (isFirstLaunchDone) {
			setBooleanPrefValue(KEY_FIRST_LAUNCH_DONE, true);
			MtxLogger.log(logActive, true, logTag, "SETTED AS FIRST LAUNCH");
		} else {
			setBooleanPrefValue(KEY_FIRST_LAUNCH_DONE, false);
			MtxLogger
					.log(logActive, true, logTag,
							"REMOVED FIRST LAUNCH (Probably overridden the previous first launch)");
		}
	}

	/**
	 * Check the game if it is first launch or not, it is helpful to create
	 * first launch files which should be created once. It can be checked in
	 * every game launch. To set as first launch use the "setFirstLaunchDone"
	 * method
	 * 
	 * @see setFirstLaunchDone(boolean isFirstLaunchDone);
	 * */
	public static boolean isFirstLaunchDone() {
		boolean isFirstLaunchDone = getBooleanPrefValue(KEY_FIRST_LAUNCH_DONE,
				false);
		if (isFirstLaunchDone) {
			MtxLogger.log(logActive, true, logTag,
					"NOT FIRST LAUNCH OF THE APP (First launch set before)");
			return true;
		} else {
			MtxLogger.log(logActive, true, logTag, "FIRST LAUNCH OF THE APP");
			return false;
		}
	}

	/**
	 * Get a android preferences, if it is not set it returns def value
	 * */
	public static String getStringPrefValue(String key, String defValue) {
		String value = prefs.getString(key, defValue);
		MtxLogger.log(logActive, true, logTag, "Pref (Key: " + key + "): "
				+ value);
		return value;

	}

	/**
	 * Get a android preferences, if it is not set it returns def value
	 * */
	public static Boolean getBooleanPrefValue(String key, boolean defValue) {
		boolean value = prefs.getBoolean(key, defValue);
		MtxLogger.log(logActive, true, logTag, "Pref (Key: " + key + "): "
				+ value);
		return value;
	}

	/**
	 * Get a android preferences, if it is not set it returns def value
	 * */
	public static int getIntegerPrefValue(String key, int defValue) {
		int value = prefs.getInteger(key, defValue);
		MtxLogger.log(logActive, true, logTag, "Pref (Key: " + key + "): "
				+ value);
		return value;
	}

	/**
	 * Set an android preference (boolean)
	 * */
	public static void setBooleanPrefValue(String key, boolean value) {
		prefs.putBoolean(key, value);
		prefs.flush();
	}

	/**
	 * Set an android preference (String)
	 * */
	public static void setStringPrefValue(String key, String value) {
		prefs.putString(key, value);
		prefs.flush();
	}

	/**
	 * Set an android preference (Integer)
	 * */
	public static void setIntegerPrefValue(String key, int value) {
		prefs.putInteger(key, value);
		prefs.flush();
	}
}
