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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.moribitotech.mtx.settings.AppSettings;

public abstract class AbstractActor extends Actor {
	//
	protected final String logTag = "MtxAbstractActorLog";
	public static boolean logActive = true;

	// Texture Region for actor (Not mandatory)
	private TextureRegion textureRegion;
	private boolean isTextureRegionActive = false;

	// Animation for actor (Not mandatory)
	private Animation animation;
	private Animation animationMomentary;
	private boolean isAnimationActive = false;
	private boolean isAnimationMomentaryActive = false;
	private boolean isAnimationMomentaryFinished = true;
	private boolean isAnimationLooping = false;
	private boolean killAllAnimations = false;

	// Animation timer
	private float stateTime = 0.0f;

	// Particle
	private ParticleEffect particleEffect;
	private float particlePosX = 0.0f;
	private float particlePosY = 0.0f;
	private boolean isParticleEffectActive;

	// Actor second counter (1 second tick)
	private long startTime = System.nanoTime();
	private long secondsTime = 0L;

	// Original position
	private float Xoriginal;
	private float Yoriginal;

	public AbstractActor(TextureRegion textureRegion,
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

	public AbstractActor(TextureRegion textureRegion,
			boolean isTextureRegionActive, float posX, float posY, float width,
			float height) {
		super();
		this.textureRegion = textureRegion;
		this.isTextureRegionActive = isTextureRegionActive;
		setBounds(posX, posY, width, height);
		setPosition(posX, posY);
		setSize(width, height);
	}

	public AbstractActor(float posX, float posY, float width, float height) {
		super();
		setBounds(posX, posY, width, height);
		setPosition(posX, posY);
		setSize(width, height);
	}

	public AbstractActor(float width, float height, boolean DIPActive) {
		super();
		if (DIPActive) {
			float ratioSize = AppSettings.getWorldSizeRatio();
			setSize(width * ratioSize, height * ratioSize);
		} else {
			setSize(width, height);
		}
	}

	public AbstractActor() {
		super();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		stateTime += delta;

		// Update time (1 second tick)
		// ############################################################
		if (System.nanoTime() - startTime >= 1000000000) {
			secondsTime++;
			startTime = System.nanoTime();
		}
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

		// DRAW ANIMATION (Draw only if set active and not null)
		// ##################################################################
		if (isAnimationActive && animation != null) {
			// Get frame by frame and animate
			TextureRegion keyFrame = animation.getKeyFrame(stateTime,
					isAnimationLooping);

			// Draw it due to actors' settings
			batch.draw(keyFrame, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());

			if (animation.isAnimationFinished(stateTime)) {
				if (killAllAnimations) {
					isAnimationActive = false;
				}
			}
		}

		// DRAW ANIMATION MOMENTARY (Draw only if set active and not null)
		// ##################################################################
		if (isAnimationMomentaryActive && animationMomentary != null) {
			if (animationMomentary.isAnimationFinished(stateTime)) {
				if (!killAllAnimations) {
					isAnimationActive = true;
					isAnimationMomentaryActive = false;
					isAnimationMomentaryFinished = true;
					startTime = 0;
				} else {
					isAnimationActive = false;
					isAnimationMomentaryActive = false;
					isAnimationMomentaryFinished = true;
					startTime = 0;
				}
			}

			if (isAnimationMomentaryActive) {
				// Get frame by frame and animate
				TextureRegion keyFrame = animationMomentary.getKeyFrame(
						stateTime, false);

				// Draw it due to actors' settings
				batch.draw(keyFrame, getX(), getY(), getOriginX(),
						getOriginY(), getWidth(), getHeight(), getScaleX(),
						getScaleY(), getRotation());
			}
		}

		// PARTICLE
		// #################################################################
		if (isParticleEffectActive) {
			particleEffect.draw(batch, Gdx.graphics.getDeltaTime());
			particleEffect.setPosition(getX() + particlePosX, getY()
					+ particlePosY);
		}
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

	/**
	 * Get animation of the actor
	 * 
	 * @return animation
	 * 
	 * */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Set animation of the actor
	 * 
	 * @param animation
	 *            set animation
	 * @param isAnimationActive
	 *            set animation active to be drawn or not
	 * @param isAnimationLooping
	 *            set animation to loop or not
	 * */
	public void setAnimation(Animation animation, boolean isAnimationActive,
			boolean isAnimationLooping) {
		this.animation = animation;
		this.isAnimationActive = isAnimationActive;
		this.isAnimationLooping = isAnimationLooping;
		//
	}

	public Animation getAnimationMomentary() {
		return animationMomentary;
	}

	/**
	 * Set a momentary animation for the actor.
	 * 
	 * <p>
	 * EXAMPLE<br>
	 * Actor has two animations idle and blinkeye. If you set the the momentary
	 * animation as blinkeye, actor will blink eye and it will go its' regular
	 * animation such as idle
	 * <p>
	 * "animationAfterMomentary" For instance, a bat flies and changes into
	 * dracula. Regular animation is flying bat, animation momentary is the
	 * smoke at moment of change, animationAfterMomentary is the dracula
	 * animation
	 * <p>
	 * "isAnimationMomentaryWaitingToBeCompleted" prevents the animation to be
	 * run again and again when this method clicked contineusly, if its "true"
	 * this method wont be active until momentary animation completed
	 * <p>
	 * "killAllAnimations" is for ending animation, like a character dying
	 * animation, then no more animation will be running. It can be also used
	 * invisibility features
	 * 
	 * @param animationMomentary
	 *            set animation momentary
	 * @param isAnimationMomentaryActive
	 *            set animation momentary active to be drawn or not
	 * @param animationAfterMomentary
	 *            change regular animation after momentary animation completed
	 *            otherwise set null
	 * @param isAnimationMomentaryWaitingToBeCompleted
	 *            wait for to be completed, otherwise it will start again when
	 *            this method called
	 * @param killAllAnimations
	 *            do not run any animations after moementary animation completed
	 * */
	public void setAnimationMomentary(Animation animationMomentary,
			boolean isAnimationMomentaryActive,
			Animation animationAfterMomentary,
			boolean isAnimationMomentaryWaitingToBeCompleted,
			boolean killAllAnimations) {
		this.killAllAnimations = killAllAnimations;

		if (animationAfterMomentary != null) {
			animation = animationAfterMomentary;
		}

		if (!isAnimationMomentaryWaitingToBeCompleted) {
			this.animationMomentary = animationMomentary;
			this.animationMomentary.setPlayMode(Animation.NORMAL);
			this.isAnimationMomentaryActive = isAnimationMomentaryActive;
			//
			if (isAnimationMomentaryActive) {
				stateTime = 0;
				isAnimationActive = false;
			}

		} else {
			if (isAnimationMomentaryFinished) {
				this.animationMomentary = animationMomentary;
				this.animationMomentary.setPlayMode(Animation.NORMAL);
				this.isAnimationMomentaryActive = isAnimationMomentaryActive;
				//
				if (isAnimationMomentaryActive) {
					stateTime = 0;
					isAnimationActive = false;
				}

				isAnimationMomentaryFinished = false;
			}
		}
	}

	/**
	 * Get animation is active or not
	 * 
	 * @return boolean value
	 * 
	 * */
	public boolean isAnimationActive() {
		return isAnimationActive;
	}

	/**
	 * Set animation active, animation only be drawn if the animation is setted
	 * and active
	 * 
	 * @param isAnimationActive
	 *            value to set animation active or not
	 * 
	 * */
	public void setAnimationActive(boolean isAnimationActive) {
		this.isAnimationActive = isAnimationActive;
	}

	/**
	 * Get the animation is looping or not
	 * 
	 * @return boolean value
	 * 
	 * */
	public boolean isAnimationLooping() {
		return isAnimationLooping;
	}

	/**
	 * Set animation to loop or not. It will only works is animation set and
	 * active
	 * 
	 * @param isAnimationLooping
	 *            boolean value
	 * 
	 * */
	public void setAnimationLooping(boolean isAnimationLooping) {
		this.isAnimationLooping = isAnimationLooping;
	}

	/**
	 * Returns the state time for this actor, it can be used in animations
	 * 
	 * @return state time (delta added)
	 * 
	 * */
	public float getStateTime() {
		return stateTime;
	}

	/**
	 * Set state time
	 */
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	/**
	 * Get if killAllAnimation active
	 * */
	public boolean isKillAllAnimations() {
		return killAllAnimations;
	}

	/**
	 * Set killAllAnimations. If is true, after animations completed it wont be
	 * visible anymore
	 * */
	public void setKillAllAnimations(boolean killAllAnimations) {
		this.killAllAnimations = killAllAnimations;
	}

	/**
	 * Get seconds from the moment this actor created
	 * */
	public long getSecondsTime() {
		return secondsTime;
	}

	/**
	 * Set seconds, it can be used the reset seconds for this actor
	 * */
	public void setSecondsTime(long secondsTime) {
		this.secondsTime = secondsTime;
	}

	/**
	 * Get particle for this actor
	 * */
	public ParticleEffect getParticleEffect() {
		return particleEffect;
	}

	/**
	 * Set particle for this actor, centerPosition is used to center the
	 * particle on this actor sizes
	 * */
	public void setParticleEffect(ParticleEffect particleEffect,
			boolean isParticleEffectActive, boolean isStart,
			boolean centerPosition) {
		this.particleEffect = particleEffect;
		this.isParticleEffectActive = isParticleEffectActive;
		if (!centerPosition) {
			this.particleEffect.setPosition(getX(), getY());
		} else {
			particlePosX = getWidth() / 2.0f;
			particlePosY = getHeight() / 2.0f;
			this.particleEffect.setPosition(getX() + particlePosX, getY()
					+ particlePosY);
		}

		if (isStart) {
			this.particleEffect.start();
		}
	}

	/**
	 * Set particle position
	 * */
	public void setParticlePositionXY(float x, float y) {
		particlePosX = x;
		particlePosY = y;
	}

	/**
	 * Check if particle active
	 * */
	public boolean isParticleEffectActive() {
		return isParticleEffectActive;
	}

	/**
	 * Set particle active to draw or not
	 * */
	public void setParticleEffectActive(boolean isParticleEffectActive) {
		this.isParticleEffectActive = isParticleEffectActive;
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
