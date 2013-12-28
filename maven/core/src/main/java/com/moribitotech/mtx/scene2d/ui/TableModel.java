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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.esotericsoftware.tablelayout.Cell;
import com.moribitotech.mtx.settings.AppSettings;

public class TableModel extends Table {
	public TextureRegion textureBackground;
	public boolean isBackgroundTextureActive;

	public TableModel(TextureRegion textureBackground, float width,
			float height, boolean DIPActive) {
		isBackgroundTextureActive = true;
		this.textureBackground = textureBackground;
		//
		if (DIPActive) {
			setSize(width * AppSettings.getWorldSizeRatio(), height
					* AppSettings.getWorldPositionYRatio());
		} else {
			setHeight(height);
			setWidth(width);
		}
	}

	public TableModel(TextureRegion textureBackground, float x, float y,
			float width, float height) {
		isBackgroundTextureActive = true;
		this.textureBackground = textureBackground;
		setBounds(x, y, width, height);
		setPosition(x, y);
		setHeight(height);
		setWidth(width);
	}

	public TableModel(TextureRegion textureBackground, float width, float height) {
		isBackgroundTextureActive = true;
		this.textureBackground = textureBackground;
		setHeight(height);
		setWidth(width);
	}

	public TableModel(TextureRegion textureBackground) {
		isBackgroundTextureActive = true;
		this.textureBackground = textureBackground;
	}

	public TableModel(Skin skin) {
		super(skin);
	}

	public TableModel(float width, float height) {
		setHeight(height);
		setWidth(width);
	}

	public TableModel() {
		super();
	}

	public TextureRegion getTextureBackground() {
		return textureBackground;
	}

	public void setTextureBackground(TextureRegion textureBackground,
			boolean isBackgroundTextureActive) {
		this.textureBackground = textureBackground;
		this.isBackgroundTextureActive = isBackgroundTextureActive;
	}

	public void setBackgroundTextureActive(boolean isBackgroundTextureActive) {
		this.isBackgroundTextureActive = isBackgroundTextureActive;
	}

	@SuppressWarnings("rawtypes")
	public Cell add(Actor actor, float width, float height, boolean DIPActive) {
		if (DIPActive) {
			return super.add(actor).size(
					width * AppSettings.getWorldSizeRatio(),
					height * AppSettings.getWorldSizeRatio());
		} else {
			return super.add(actor);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// Then draw child actors over bg
		super.draw(batch, parentAlpha);

		// First draw bg
		if (textureBackground != null && isBackgroundTextureActive) {
			batch.draw(textureBackground, getX(), getY(), getOriginX(),
					getOriginY(), getWidth(), getHeight(), getScaleX(),
					getScaleY(), getRotation());
		}
	}
}
