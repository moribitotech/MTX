package com.moribitotech.samples.core;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.moribitotech.mtx.scene2d.ui.AbstractButton;
import com.moribitotech.samples.core.testassets.Assets;

public class ButtonGame extends AbstractButton {

	public ButtonGame(Drawable up, Drawable down) {
		super(Assets.font2, up, down);
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
