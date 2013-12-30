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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.moribitotech.mtx.settings.AppSettings;

public abstract class AbstractActorLight extends Actor {
	//
	protected final String logTag = "MtxAbstractActorLightLog";
	public static boolean logActive = true;

	// Texture Region for actor (Not mandatory)
	private TextureRegion textureRegion;
	private boolean isTextureRegionActive = false;

	// Original position
	private float Xoriginal;
	private float Yoriginal;

	public AbstractActorLight(TextureRegion textureRegion,
			boolean isTextureRegionActive, float posX, float posY, float orgnX,
			float orgnY, float width, float height) {
		super();
		this.textureRegion = textureRegion;
		this.isTextureRegionActive = isTextureRegionActive;
		setBounds(posX, posY, width, height);
		setPosition(posX, posY);
		setSize(width, height);
		setOrigin(orgnX, orgnY);
	}

	public AbstractActorLight(TextureRegion textureRegion,
			boolean isTextureRegionActive, float posX, float posY, float width,
			float height) {
		super();
		this.textureRegion = textureRegion;
		this.isTextureRegionActive = isTextureRegionActive;
		setBounds(posX, posY, width, height);
		setPosition(posX, posY);
		setSize(width, height);
	}

	public AbstractActorLight(float posX, float posY, float width, float height) {
		super();
		setBounds(posX, posY, width, height);
		setPosition(posX, posY);
		setSize(width, height);
	}

	public AbstractActorLight(float width, float height, boolean DIPActive) {
		super();
		if (DIPActive) {
			float ratioSize = AppSettings.getWorldSizeRatio();
			setSize(width * ratioSize, height * ratioSize);
		} else {
			setSize(width, height);
		}

	}

	public AbstractActorLight() {
		super();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		// For fade out/in effect
		batch.setColor(this.getColor().r, this.getColor().g, this.getColor().b,
				parentAlpha * this.getColor().a);

		// DRAW TEXTURE REGION (Draw only if set active and not null)
		// ##################################################################
		if (isTextureRegionActive && textureRegion != null) {
			// Draw it due to actors' settings
			batch.draw(textureRegion, getX(), getY(), getOriginX(),
					getOriginY(), getWidth(), getHeight(), getScaleX(),
					getScaleY(), getRotation());
		}
	}

	public void actionMoveTo(float x, float y, float duration) {
		// Move to a specific position by time
		MoveToAction action = new MoveToAction();
		action.setPosition(x, y);
		if (duration > 0) {
			action.setDuration(duration);
		}
		addAction(action);
	}

	public void actionMoveBy(float x, float y, float duration) {
		// Move towards a direction during given time (NON-STOP)
		MoveByAction action = new MoveByAction();
		action.setAmount(x, y);
		if (duration > 0) {
			action.setDuration(duration);
		}
		addAction(action);
	}

	/**
	 * Translate actor in a direction of speed without stopping. Actor moves in
	 * constants speed set without acceleration
	 * 
	 * @param speedX
	 *            axis-X speed
	 * @param speedY
	 *            axis-Y speed
	 * @param delta
	 *            the delta time for accurate speed
	 * */
	public void translateWithoutAcc(float speedX, float speedY, float delta) {
		setPosition(getX() + (speedX * delta), getY() + (speedY * delta));
	}

	/**
	 * Get textureRegion of the actor
	 * 
	 * @return TextureRegion
	 * 
	 * */
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}

	/**
	 * Set texture region for the actor, it will be drawn only if texture region
	 * is set and active
	 * 
	 * @param textureRegion
	 *            texture region of the actor
	 * @param isTextureRegionActive
	 *            set texture region active to be drawn or not
	 * */
	public void setTextureRegion(TextureRegion textureRegion,
			boolean isTextureRegionActive) {
		this.textureRegion = textureRegion;
		this.isTextureRegionActive = isTextureRegionActive;
	}

	public boolean isTextureRegionActive() {
		return isTextureRegionActive;
	}

	public void setTextureRegionActive(boolean isTextureRegionActive) {
		this.isTextureRegionActive = isTextureRegionActive;
	}

	public float getXoriginal() {
		return Xoriginal;
	}

	public void setXoriginal(float xoriginal) {
		Xoriginal = xoriginal;
	}

	public float getYoriginal() {
		return Yoriginal;
	}

	public void setYoriginal(float yoriginal) {
		Yoriginal = yoriginal;
	}

	public void setOriginalPosition() {
		Xoriginal = getX();
		Yoriginal = getY();
	}
}
