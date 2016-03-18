package com.moribitotech.samples.gameworld.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.moribitotech.mtx.animation.AnimationCreator;

public class Assets {
	private final static String FILE_IMAGE_ATLAS = "data/mtx/samples/gameworld/imageatlas.txt";
	private final static String FILE_UI_SKIN = "skin/uiskin.json";
	public static TextureAtlas imageAtlas;
	public static Skin skin;

	//
	public static TextureRegion img_bg_1_;
	public static TextureRegion img_btn_pause;
	public static TextureRegion img_btn_play;
	public static TextureRegion img_obj_soil_1_;
	public static TextureRegion img_obj_cloud;
	public static TextureRegion img_obj_snowflake;

	//
	public static Animation anim_bats;
	public static Animation anim_black_ghost;
	public static Animation anim_green_santa;

	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static TextureAtlas getAtlas() {
		if (imageAtlas == null) {
			imageAtlas = new TextureAtlas(Gdx.files.internal(FILE_IMAGE_ATLAS));
		}
		return imageAtlas;
	}

	public static Skin getSkin() {
		if (skin == null) {
			FileHandle skinFile = Gdx.files.internal(FILE_UI_SKIN);
			skin = new Skin(skinFile);
		}
		return skin;
	}

	public static void loadAll() {
		relaseResources();
		loadImages();
		loadButtons();
		loadFonts();
		loadAnimations();
		loadSoundsAndMusics();
	}

	private static void relaseResources() {
		skin = null;
		imageAtlas = null;
	}

	public static void loadImages() {
		img_bg_1_ = getAtlas().findRegion("img_bg_1_");
		img_btn_pause = getAtlas().findRegion("img_btn_pause");
		img_btn_play = getAtlas().findRegion("img_btn_play");
		img_obj_soil_1_ = getAtlas().findRegion("img_obj_soil_1_");
		img_obj_cloud = getAtlas().findRegion("img_obj_cloud");
		img_obj_snowflake = getAtlas().findRegion("img_obj_snowflake");
	}

	public static void loadButtons() {
	}

	public static void loadFonts() {
	}

	public static void loadAnimations() {
		anim_bats = AnimationCreator.getAnimationFromSingleTexture(getAtlas(),
				"anim_bats", 6, 0.06f);
		anim_black_ghost = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "anim_black_ghost", 5, 0.06f);
		anim_green_santa = AnimationCreator.getAnimationFromSingleTexture(
                getAtlas(), "anim_green_santa", 5, 0.06f);
	}

	public static void loadSoundsAndMusics() {
	}
}
