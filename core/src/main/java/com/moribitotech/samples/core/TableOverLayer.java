package com.moribitotech.samples.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class TableOverLayer extends Table {
	public TextureRegion textureBackground;
	public boolean isBackgroundTextureActive;
	
	public TableOverLayer(TextureRegion textureBackground, float x, float y, float width, float height){
		isBackgroundTextureActive = true;
		this.textureBackground = textureBackground;
		setBounds(x, y, width, height);
		setPosition(x, y);
		setHeight(height);
		setWidth(width);
	}
	
	public TableOverLayer(TextureRegion textureBackground, float width, float height){
		isBackgroundTextureActive = true;
		this.textureBackground = textureBackground;
		setHeight(height);
		setWidth(width);
	}
	
	public TableOverLayer(TextureRegion textureBackground){
		isBackgroundTextureActive = true;
		this.textureBackground = textureBackground;
	}
	
	public TableOverLayer(Skin skin) {
		super(skin);
	}
		
	public TableOverLayer() {
		super();
	}

	public TextureRegion getTextureBackground() {
		return textureBackground;
	}

	public void setTextureBackground(TextureRegion textureBackground, boolean isBackgroundTextureActive) {
		this.textureBackground = textureBackground;
		this.isBackgroundTextureActive = isBackgroundTextureActive;
	}

	public void setBackgroundTextureActive(boolean isBackgroundTextureActive){
		this.isBackgroundTextureActive = isBackgroundTextureActive;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// First draw bg
		if(textureBackground != null && isBackgroundTextureActive){
			batch.draw(textureBackground, getX(), getY(), getWidth(), getHeight());
		}
		
		// Then draw child actors over bg
		super.draw(batch, parentAlpha);
	}
}
