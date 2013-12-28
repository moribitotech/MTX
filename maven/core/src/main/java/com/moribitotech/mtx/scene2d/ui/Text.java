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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.moribitotech.mtx.scene2d.AbstractActorLight;
import com.moribitotech.mtx.settings.AppSettings;

public class Text extends AbstractActorLight {
	private String text = "";
	private BitmapFont bitMapFont;
	//
	private float originalPosY;

	public Text(BitmapFont bitMapFont, float width, float height,
			boolean DIPActive) {
		super(width, height, DIPActive);
		this.bitMapFont = bitMapFont;
		//
		if (DIPActive) {
			bitMapFont.setScale(AppSettings.getWorldSizeRatio());
		}
	}

	public void setBitMapFont(BitmapFont bitMapFont, boolean DIPActive) {
		this.bitMapFont = bitMapFont;
		//
		if (DIPActive) {
			bitMapFont.setScale(AppSettings.getWorldSizeRatio());
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		//
		drawText(batch);
	}

	private void drawText(SpriteBatch batch) {
		if (bitMapFont != null) {
			bitMapFont.draw(batch, text, getX(), getY());
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BitmapFont getBitMapFont() {
		return bitMapFont;
	}

	public float getOriginalPosY() {
		return originalPosY;
	}

	public void setOriginalPosY(float originalPosY) {
		this.originalPosY = originalPosY;
	}
}
