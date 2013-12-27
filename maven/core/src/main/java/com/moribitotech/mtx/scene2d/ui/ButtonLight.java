package com.moribitotech.mtx.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.moribitotech.mtx.scene2d.AbstractGroupLight;

public class ButtonLight extends AbstractGroupLight {
	
	//
	private boolean isToggleActive;

	public ButtonLight(float width, float height, TextureRegion texture,
			boolean DIPActive) {
		super(width, height, DIPActive);
		//
		setTextureRegion(texture, true);
		this.isToggleActive = false;
	}

	public boolean isToggleActive() {
		return isToggleActive;
	}

	public void setToggleActive(boolean isToggleActive) {
		this.isToggleActive = isToggleActive;
	}

	public void setToggleSwitch() {
		if (isToggleActive) {
			isToggleActive = false;
		} else {
			isToggleActive = true;
		}
	}
}
