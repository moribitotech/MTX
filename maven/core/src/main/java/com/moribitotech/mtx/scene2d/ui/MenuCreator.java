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

package com.moribitotech.mtx.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuCreator {
	public static Table createTable(boolean fillParent, Skin skin) {
		Table table = new Table(skin);
		table.setFillParent(fillParent);
		return table;
	}

	public static ButtonGame createCustomGameButton(BitmapFont bitmapFont,
			TextureRegion up, TextureRegion down, float width, float height,
			boolean DIPActive) {
		Drawable dUp = new TextureRegionDrawable(up);
		Drawable dDown = new TextureRegionDrawable(down);
		return new ButtonGame(bitmapFont, dUp, dDown, width, height, true);
	}

	public static ButtonGame createCustomGameButton(BitmapFont bitmapFont,
			TextureRegion up, TextureRegion down) {
		Drawable dUp = new TextureRegionDrawable(up);
		Drawable dDown = new TextureRegionDrawable(down);
		return new ButtonGame(bitmapFont, dUp, dDown);
	}

	public static ButtonToggle createCustomToggleButton(BitmapFont bitmapFont,
			TextureRegion tOn, TextureRegion tOff, boolean isToggleActive) {
		Drawable dUp = new TextureRegionDrawable(tOn);
		Drawable dDown = new TextureRegionDrawable(tOff);
		return new ButtonToggle(bitmapFont, dUp, dDown, tOn, tOff,
				isToggleActive);
	}

	public static ButtonToggle createCustomToggleButton(BitmapFont bitmapFont,
			TextureRegion tOn, TextureRegion tOff, boolean isToggleActive,
			float width, float height, boolean DIPActive) {
		Drawable dUp = new TextureRegionDrawable(tOn);
		Drawable dDown = new TextureRegionDrawable(tOff);
		return new ButtonToggle(bitmapFont, dUp, dDown, tOn, tOff,
				isToggleActive, width, height, DIPActive);
	}

	public static ButtonSlider createCustomSlider(TextureRegion tBackground,
			TextureRegion tKnob, boolean isVertical, float min, float max,
			float stepSize, float width, float height, boolean DIPActive) {
		//
		Drawable dBg = new TextureRegionDrawable(tBackground);
		Drawable dKnob = new TextureRegionDrawable(tKnob);
		SliderStyle sliderStyle = new SliderStyle(dBg, dKnob);
		return new ButtonSlider(min, max, stepSize, isVertical, sliderStyle,
				width, height, DIPActive);
	}

}
