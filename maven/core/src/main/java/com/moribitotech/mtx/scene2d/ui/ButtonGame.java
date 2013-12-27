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


import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ButtonGame extends AbstractButton {

	public ButtonGame(BitmapFont bitMapFont, Drawable up, Drawable down) {
		super(bitMapFont, up, down);
	}

	public ButtonGame(BitmapFont bitMapFont, Drawable up, Drawable down,
			float width, float height, boolean DIPActive) {
		super(bitMapFont, up, down, width, height, DIPActive);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// If button locked
		// ##################################################################
		if (isLockActive && textureLocked != null) {
			drawLocked(batch);
		}

		// If text set and intented to be used
		// ##################################################################
		else if (text != "" && bitMapFont != null && isTextActive) {
			super.draw(batch, parentAlpha);
			drawText(batch);
			drawExternalTexture(batch);
		}

		// Draw default
		// ##################################################################
		else {
			super.draw(batch, parentAlpha);
			drawExternalTexture(batch);
		}
	}

	private void drawExternalTexture(SpriteBatch batch) {
		if(isExternalTextureActive && textureExternal != null){
			batch.draw(textureExternal, getX() + externalTexturePosX, getY() + externalTexturePosY, externalTextureSizeW, externalTextureSizeH);
		}
	}

	private void drawLocked(SpriteBatch batch) {
		batch.draw(textureLocked, getX(), getY(), getWidth(), getHeight());
	}
	
	private void drawText(SpriteBatch batch) {
		bitMapFont.draw(batch, text, getX() +  textPosX, getY() + textPosY);
	}
}
