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

package com.moribitotech.mtx.scene2d;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.moribitotech.mtx.settings.MtxLogger;

public class AbstractWorldScene2d extends Group {
	//
	protected final String logTag = "MtxWorldLog";
	public static boolean logActive = true;

	// World background image
	private Image imageBackground;

	// World second counter (1 second tick)
	private long startTime = System.nanoTime();
	private long secondsTime = 0L;

	public AbstractWorldScene2d(float posX, float posY, float worldWidth,
			float worldHeight) {
		super();
		setSize(worldWidth, worldHeight);
		setPosition(posX, posY);
		//
		MtxLogger.log(logActive, true, logTag, "World Set: W: " + worldWidth
				+ " - " + "H: " + worldHeight);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		// Update world clock (1 second tick)
		// ############################################################
		if (System.nanoTime() - startTime >= 1000000000) {
			secondsTime++;
			startTime = System.nanoTime();
		}
	}

	public void setBackgroundTexture(TextureRegion textureBackground,
			Scaling scaling, boolean fillParent, boolean touchable) {
		Drawable tBg = new TextureRegionDrawable(textureBackground);
		Image imgbg = new Image(tBg, scaling);
		this.imageBackground = imgbg;
		imgbg.setFillParent(fillParent);

		if (!touchable) {
			imgbg.setTouchable(Touchable.disabled);
		}

		addActor(imgbg);
		//
		MtxLogger.log(logActive, true, logTag, "World bacground image set");
	}

	/**
	 * Render the world for MVC style development, otherwise use act & draw of
	 * stage.
	 * */
	public void render() {

	}

	public Image getImageBackground() {
		return imageBackground;
	}

	public void setImageBackground(Image imageBackground) {
		this.imageBackground = imageBackground;
	}

	public long getSecondsTime() {
		return secondsTime;
	}

	public void setSecondsTime(long secondsTime) {
		this.secondsTime = secondsTime;
	}
}
