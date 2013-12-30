package com.moribitotech.samples.core;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.moribitotech.mtx.scene2d.ui.AbstractButton;
import com.moribitotech.samples.core.testassets.Assets;

public class ButtonLevel extends AbstractButton {
	// Level stars (Not mandatory)
	private TextureRegion textureStarHolder;
	private TextureRegion textureStar;
	private int numberOfTotalStars = 1;
	private  int numberOfEarnedStars = 1;
	private float starSizeWidth; 
	private float starSizeHeight;
	private float starPosXStart = 0;
	private float starPosYStart = 0;
	private float starSizeRatio = 5;

	// Level Number (Not mandatory)
	private int levelNumber = -999;

	// Font (For writings and level number)
	private BitmapFont bitMapFont;

	public ButtonLevel(Drawable up, Drawable down) {
		super(Assets.font2, up, down);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// If level locked
		// ##################################################################
		if (isLockActive && textureLocked != null) {
			drawLocked(batch);
		}

		// If level number set
		// ##################################################################
		else if (levelNumber != -999 && bitMapFont != null) {
			super.draw(batch, parentAlpha);
			drawLevelNumber(batch);
			drawText(batch);
			drawStars(batch);
			drawExternalTexture(batch);
		}

		// Draw default
		// ##################################################################
		else {
			super.draw(batch, parentAlpha);
			drawText(batch);
			drawStars(batch);
			drawExternalTexture(batch);
		}
	}

	private void drawStars(SpriteBatch batch) {
		if(textureStarHolder != null && textureStar != null){
			// Updated start positions
			float activePosXStart = (getX() + getWidth() / 2) - ((starSizeWidth * numberOfTotalStars) / 2);
			float activePoxYStart = (getY() + starSizeHeight / 1.3f);	
			
			// Stars align together next by next (increase for each star)
			// S as Star
			// SSS (together aligned due to their widths)
			float currentPosX = getX();
			
			for(int i = 0; i < numberOfTotalStars; i++){
				// Update new star holder positions (for multiple stars)
				currentPosX = activePosXStart + (starSizeWidth * i);
				batch.draw(textureStarHolder, currentPosX + starPosXStart, activePoxYStart + starPosYStart,starSizeWidth, starSizeHeight);	
			}
			
			for(int j = 0; j < numberOfEarnedStars; j++){
				// Update new star position (for multiple stars)
				currentPosX = activePosXStart + (starSizeWidth * j);
				batch.draw(textureStar, currentPosX + starPosXStart, activePoxYStart + starPosYStart,starSizeWidth, starSizeHeight);		
			}
		}
	}
	
	private void drawText(SpriteBatch batch) {
		if(isTextActive && bitMapFont != null){
			bitMapFont.draw(batch, text, getX() +  textPosX, getY() + textPosY);
		}
	}

	private void drawLocked(SpriteBatch batch) {
		batch.draw(textureLocked, getX(), getY(), getWidth(), getHeight());
	}
	
	private void drawExternalTexture(SpriteBatch batch) {
		if(isExternalTextureActive && textureExternal != null){
			batch.draw(textureExternal, getX() + externalTexturePosX, getY() + externalTexturePosY, externalTextureSizeW, externalTextureSizeH);
		}
	}
	
	private void drawLevelNumber(SpriteBatch batch) {
		// TODO Set precise position of level number for each game
		// there i single numbers ( < 10) 
		// there is double numbers (9 >)
		float singePositionArranger = 2.5f;
		float doublePositionArranger = 2.8f;
		
		if (levelNumber < 10) {
			bitMapFont.draw(batch, "" + levelNumber, getX() + getWidth() / singePositionArranger,
					getY() + getHeight() / 1.4f);
		} else {
			bitMapFont.draw(batch, "" + levelNumber, getX() + getWidth() / doublePositionArranger,
					getY() + getHeight() / 1.4f);
		}
	}
	
	/**
	 * Set level stars (It can be any other object) for level achievements
	 * <p>
	 * EXAMPLE<br>
	 * There are 5 stars maximum can be achieved, and user earned 3 stars. It auto calculates the position and sizes of stars due to
	 * button sizes. However further adjustment can be done (optinal) with "setLevelStarSizeRatio", "setLevelStarPosXStart" and "setLevelStarPosYStart".
	 * !!starHolderTexture and starTexture should be in same sizes!!
	 * 
	 * @param starHolderTexture the holder texture for star or other achievement object.
	 * @param starTexture the texture for star or other achievement object.
	 * @param numberOfTotalStars number of total stars
	 * @param numberOfEarnedStars number of earned/achieved stars
	 * */
	public void setLevelStars(TextureRegion starHolderTexture, TextureRegion starTexture, int numberOfTotalStars, int numberOfEarnedStars){
		textureStarHolder = starHolderTexture;
		textureStar = starTexture;
		this.numberOfTotalStars = numberOfTotalStars;
		this.numberOfEarnedStars = numberOfEarnedStars;
					
		//
		float btnSizeW = getWidth() - (getWidth() / starSizeRatio);
		float btnSizeH = getHeight() - (getHeight() / starSizeRatio);
		starSizeWidth = btnSizeW / numberOfTotalStars;
		starSizeHeight = btnSizeH / numberOfTotalStars;
	}
	
	 
	/**
	 * Get level starts X position
	 * */
	public float getLevelStarPosXStart() {
		return starPosXStart;
	}

	/**
	 * Set level stars X position. This is being calculated automaticly and centered. Do not suggest to play 
	 * width.
	 * */
	public void setLevelStarPosXStart(float starPosXStart) {
		this.starPosXStart = starPosXStart;
	}

	/**
	 * Get level starts Y position
	 * */
	public float getLevelStarPosYStart() {
		return starPosYStart;
	}
	/**
	 * Set level stars Y position. This can be used to lift all stars (holders and stars) up or down for 
	 * adjusments
	 * */
	public void setLevelStarPosYStart(float starPosYStart) {
		this.starPosYStart = starPosYStart;
	}

	/**
	 * Get level stars ratio
	 * */
	public float getLevelStarSizeRatio() {
		return starSizeRatio;
	}
	
	/**
	 * Set level stars size ratio. This makes stars smaller or bigger (Default is 5). Bigger number makes stars/holders
	 * bigger and vice-versa.
	 * */
	public void setLevelStarSizeRatio(float starSizeRatio) {
		this.starSizeRatio = starSizeRatio;
		float btnSizeW = getWidth() - (getWidth() / starSizeRatio);
		float btnSizeH = getHeight() - (getHeight() / starSizeRatio);
		starSizeWidth = btnSizeW / numberOfTotalStars;
		starSizeHeight = btnSizeH / numberOfTotalStars;
	}
	
	/**
	 * Get level number
	 * */
	public int getLevelNumber() {
		return levelNumber;
	}

	/**
	 * Set level number and the font to draw that number
	 * */
	public void setLevelNumber(int levelNumber, BitmapFont font) {
		this.levelNumber = levelNumber;
		bitMapFont = font;
	}
	
	/**
	 * Set level number changes
	 * */
	public void setLevelNumberChange(int levelNumber){
		this.levelNumber = levelNumber;
	}

	/**
	 * Get achievement object holder texture
	 * */
	public TextureRegion getTextureStarHolder() {
		return textureStarHolder;
	}

	/**
	 * Set achievement object holder texture
	 * */
	public void setTextureStarHolder(TextureRegion textureStarHolder) {
		this.textureStarHolder = textureStarHolder;
	}

	/**
	 * Get achievement object 
	 * */
	public TextureRegion getTextureStar() {
		return textureStar;
	}

	/**
	 * Set achievement object texture
	 * */
	public void setTextureStar(TextureRegion textureStar) {
		this.textureStar = textureStar;
	}

	/**
	 * Get number of total achievement objects
	 * */
	public int getNumberOfTotalStars() {
		return numberOfTotalStars;
	}

	/**
	 * Set number of total achievement object
	 * */
	public void setNumberOfTotalStars(int numberOfTotalStars) {
			this.numberOfTotalStars = numberOfTotalStars;
	}

	/**
	 * Get number of achievement object
	 * */
	public int getNumberOfEarnedStars() {
		return numberOfEarnedStars;
	}

	/**
	 * Set number of earned achievement object. This number cannot be higher/lower than numberOfTotalStars, it auto
	 * overrides if is less or more than numberOfTotalStars
	 * */
	public void setNumberOfEarnedStars(int numberOfEarnedStars) {
		if(numberOfEarnedStars > numberOfTotalStars){
			numberOfEarnedStars = numberOfTotalStars;
		} else{
			this.numberOfEarnedStars = numberOfEarnedStars;
		}
	}
}
