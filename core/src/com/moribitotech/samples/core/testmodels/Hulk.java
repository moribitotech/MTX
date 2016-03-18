package com.moribitotech.samples.core.testmodels;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.moribitotech.mtx.scene2d.AbstractActor;

public class Hulk extends AbstractActor {
	public final int HULK_SIZE = 70;
	public final int SPEED = 75;
	//


	public Hulk(TextureRegion textureRegion, boolean isTextureRegionActive,
			float posX, float posY, float width, float height) {
		super(textureRegion, isTextureRegionActive, posX, posY, width, height);
	}

	public Hulk(float posX, float posY, float width, float height) {
		super(posX, posY, width, height);
	}
}
