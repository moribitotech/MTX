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

public class AppSettings {
	public final static String LOG_TAG = "MtxAppSettings";
	public static boolean Log_Active = true;

	//
	public static float SCREEN_W = 0.0f;
	public static float SCREEN_H = 0.0f;
	public static float WORLD_TARGET_WIDTH = 0.0f;
	public static float WORLD_TARGET_HEIGHT = 0.0f;
	private static float WT_Width_Portrait = 0.0f;
	private static float WT_Height_Portrait = 0.0f;
	private static float WT_Width_Landscape = 0.0f;
	private static float WT_Height_Landscape = 0.0f;
	public static float WORLD_WIDTH = 0.0f;
	public static float WORLD_HEIGHT = 0.0f;

	//
	public static boolean isAppSettingSet = false;

	public enum Orientation {
		LANDSCAPE, PORTRAIT
	}

	/**
	 * Manual app settings, not using DIPActive
	 * 
	 * @param screenWidth
	 *            sets SCREEN_W for AbstractScreen Stage width
	 * @param screenHeight
	 *            sets SCREEN_H for AbstractScreen Stage height
	 * @param worldWidth
	 *            sets WORLD_WIDTH
	 * @param worldHeight
	 *            sets WORLD_HEIGHT
	 * 
	 * */
	public static void setUp(float screenWidth, float screenHeight,
			float worldWidth, float worldHeight) {
		reset();
		//
		SCREEN_W = screenWidth;
		SCREEN_H = screenHeight;
		WORLD_WIDTH = worldWidth;
		WORLD_HEIGHT = worldHeight;
		WORLD_TARGET_WIDTH = worldWidth;
		WORLD_TARGET_HEIGHT = worldHeight;
		//
		WT_Width_Portrait = worldWidth;
		WT_Height_Portrait = worldHeight;
		WT_Width_Landscape = worldWidth;
		WT_Height_Landscape = worldHeight;
		//
		isAppSettingSet = true;
	}

	/**
	 * Manual app settings, using DIPActive
	 * 
	 * @param screenWidth
	 *            sets SCREEN_W for AbstractScreen Stage width
	 * @param screenHeight
	 *            sets SCREEN_H for AbstractScreen Stage height
	 * @param worldWidth
	 *            sets WORLD_WIDTH
	 * @param worldHeight
	 *            sets WORLD_HEIGHT
	 * @param worldTargetWidth
	 *            sets WORLD_TARGET_WIDTH for DIPActive calculations
	 * @param worldHeight
	 *            sets WORLD_TARGET_HEIGHT for DIPActive calculations
	 * 
	 * */
	public static void setUp(float screenWidth, float screenHeight,
			float worldWidth, float worldHeight, float worldTargetWidth,
			float worldTargetHeight) {
		reset();
		//
		SCREEN_W = screenWidth;
		SCREEN_H = screenHeight;
		WORLD_WIDTH = worldWidth;
		WORLD_HEIGHT = worldHeight;
		WORLD_TARGET_WIDTH = worldTargetWidth;
		WORLD_TARGET_HEIGHT = worldTargetHeight;
		//
		WT_Width_Portrait = worldWidth;
		WT_Height_Portrait = worldHeight;
		WT_Width_Landscape = worldWidth;
		WT_Height_Landscape = worldHeight;
		//
		isAppSettingSet = true;
	}

	/**
	 * Default set up for DIPActive, default values are:
	 * <p>
	 * SCREEN_W = Gdx.graphics.getWidth(); <br>
	 * SCREEN_H = Gdx.graphics.getHeight(); <br>
	 * WORLD_WIDTH = Gdx.graphics.getWidth(); <br>
	 * WORLD_HEIGHT = Gdx.graphics.getHeight(); <br>
	 * WORLD_TARGET_WIDTH = 960; <br>
	 * WORLD_TARGET_HEIGHT = 540; <br>
	 * 
	 * */
	public static void setUp() {
		// Reset before setUp
		reset();

		// ####################### ONLY CHANGE THIS PART ######################
		//
		// DEVICE SCREEN RESOLUTION
		//
		// - This mainly used for Scene 2D stage creation
		// - Think this is as ViewPort
		// - If you want fixed size (Not using DIPActive) set as fixed size
		//
		
		SCREEN_W = Gdx.graphics.getWidth();
		SCREEN_H = Gdx.graphics.getHeight();

		// WORLD TARGET WIDTH & HEIGHT (Virtual)
		//
		// - This is for actor/texture resizing
		// - This will help the prevent stretching for different resolutions
		// - Create your textures in PhotoShop for this dimensions (this world
		// size), then it will be scaled for other resolutions
		//
		WORLD_TARGET_WIDTH = 960;
		WORLD_TARGET_HEIGHT = 540;

		// For swaping values (Orientation Changes), generally only used for
		// Live Wallpapers
		WT_Width_Portrait = 540;
		WT_Height_Portrait = 960;
		WT_Width_Landscape = 960;
		WT_Height_Landscape = 540;

		// WORLD WIDTH & HEIGHT (Real)
		//
		// - Our real world size
		// - Generally same with device dimensions
		// - If you want fixed size (Not using DIPActive) set as fixed size
		// - if you need bigger world than ViewPort like Angry Birds, give some
		// bigger values then you can swipe world just like in Angry Birds
		//
		WORLD_WIDTH = Gdx.graphics.getWidth();
		WORLD_HEIGHT = Gdx.graphics.getHeight();
		//
		// ####################### END OF CHANGE PART ######################
		isAppSettingSet = true;
		MtxLogger.log(Log_Active, true, LOG_TAG, "AppSettings is set");
	}

	// SCREEN ORIENTATION
	// - Check if screen portrait or landscape
	public static boolean IS_PORTRAIT;

	/**
	 * Check and set screen orientation, sets WORLD SIZES and WORLD TARGET SIZES
	 * <p>
	 * - Switch world width and height
	 * */
	public static void setUpOrientaion() {
		// Useful for live wallpaper
		if (Gdx.graphics.getWidth() <= Gdx.graphics.getHeight()) {
			// ReGet values
			SCREEN_W = Gdx.graphics.getWidth();
			SCREEN_H = Gdx.graphics.getHeight();
			//
			WORLD_WIDTH = Gdx.graphics.getWidth();
			WORLD_HEIGHT = Gdx.graphics.getHeight();

			// Switch target for portrait
			WORLD_TARGET_WIDTH = WT_Width_Portrait;
			WORLD_TARGET_HEIGHT = WT_Height_Portrait;
			//

			IS_PORTRAIT = true;

			//
			MtxLogger.log(Log_Active, true, LOG_TAG, "Screen is portrait");
		} else {
			// ReGet values
			SCREEN_W = Gdx.graphics.getWidth();
			SCREEN_H = Gdx.graphics.getHeight();
			//
			WORLD_WIDTH = Gdx.graphics.getWidth();
			WORLD_HEIGHT = Gdx.graphics.getHeight();

			// Switch target for landscape
			WORLD_TARGET_WIDTH = WT_Width_Landscape;
			WORLD_TARGET_HEIGHT = WT_Height_Landscape;

			//
			IS_PORTRAIT = false;

			//
			MtxLogger.log(Log_Active, true, LOG_TAG, "Screen is landscape");
		}

	}

	/**
	 * Check orientation
	 * */
	public static boolean isOrientationPortrait() {
		if (Gdx.graphics.getWidth() <= Gdx.graphics.getHeight()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get size ratio to scale actors (only for DIPactive true)
	 * <p>
	 * EXAMPLE: <br>
	 * if WORLD_TARGET_WIDTH = 480, and WORLD_WIDTH = 480, there wont be scaling
	 * for DIPactive actors, but if WORLD_WIDTH = 960, so actors will be scaled
	 * by 2.0f to get best fitting for different resolution devices
	 * */
	public static float getWorldSizeRatio() {
		
		//Return the more restrictive ratio
		if(getWorldPositionXRatio() < getWorldPositionYRatio())
			return getWorldPositionXRatio();
		else
			return getWorldPositionYRatio();
		
		//float ratioSize = 1.0f;
		
		//Deliberations
		// Always use width maybe ??
		//ratioSize = WORLD_WIDTH / WORLD_TARGET_WIDTH;

		// FIXME
		// Unknown ??
		// if (WORLD_WIDTH <= WORLD_HEIGHT) {
		// // Portrait
		// ratioSize = WORLD_WIDTH / WORLD_TARGET_WIDTH;
		// } else {
		// // Landscape
		// // FIXME previously (ratioSize = WORLD_HEIGHT / WORLD_TARGET_WIDTH;)
		// ratioSize = WORLD_HEIGHT / WORLD_TARGET_HEIGHT;
		// }

		//return ratioSize;
	}

	/**
	 * Get position X ratio to re-position actors (only for DIPactive true)
	 * <p>
	 * EXAMPLE: <br>
	 * if WORLD_TARGET_WIDTH = 480, and we set x position 20 for actor. We
	 * designed this for 480 WORLD_WIDTH, but a device with 960 width,
	 * WORLD_WIDTH will be 960, so new position should be 40 in this world, so
	 * position ratio is 2.0f
	 * */
	public static float getWorldPositionXRatio() {
		float ratioX = 0.0f;

		if (WORLD_WIDTH <= WORLD_HEIGHT) {
			// Portrait
			// FIXME
			// Now it does do the samething with landscape
			ratioX = WORLD_WIDTH / WORLD_TARGET_WIDTH;
		} else {
			// Landscape
			ratioX = WORLD_WIDTH / WORLD_TARGET_WIDTH;
		}

		return ratioX;
	}

	/**
	 * Get position Y ratio to re-position actors (only for DIPactive true)
	 * <p>
	 * EXAMPLE: <br>
	 * if WORLD_TARGET_HEIGHT = 480, and we set y position 20 for actor. We
	 * designed this for 480 WORLD_HEIGHT, but a device with 960 height,
	 * WORLD_HEIGHT will be 960, so new position should be 40 in this world, so
	 * position ratio is 2.0f
	 * */
	public static float getWorldPositionYRatio() {
		float ratioY = 0.0f;

		if (WORLD_WIDTH <= WORLD_HEIGHT) {
			// Portrait
			// FIXME
			// Now it does do the samething with landscape
			ratioY = WORLD_HEIGHT / WORLD_TARGET_HEIGHT;
		} else {
			// Landscape
			ratioY = WORLD_HEIGHT / WORLD_TARGET_HEIGHT;
		}

		return ratioY;
	}

	private static void reset() {
		SCREEN_W = 0.0f;
		SCREEN_H = 0.0f;
		WORLD_TARGET_WIDTH = 0.0f;
		WORLD_TARGET_HEIGHT = 0.0f;
		WT_Width_Portrait = 0.0f;
		WT_Height_Portrait = 0.0f;
		WT_Width_Landscape = 0.0f;
		WT_Height_Landscape = 0.0f;
		WORLD_WIDTH = 0.0f;
		WORLD_HEIGHT = 0.0f;
		isAppSettingSet = false;
	}
}
