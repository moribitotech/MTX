package com.moribitotech.samples.core.testmodels;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.moribitotech.mtx.scene2d.AbstractActor;

public class Bat extends AbstractActor {

	public Bat(TextureRegion textureRegion, boolean isTextureRegionActive,
			float posX, float posY, float width, float height) {
		super(textureRegion, isTextureRegionActive, posX, posY, width, height);
	}

	public Bat(float posX, float posY, float width, float height) {
		super(posX, posY, width, height);
	}
}
