package com.moribitotech.samples.jungle.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
	private final static String FILE_IMAGE_ATLAS = "data/mtx/samples/jungle/imageatlas.txt";
	private final static String FILE_UI_SKIN = "skin/uiskin.json";
	public static TextureAtlas imageAtlas;
	public static Skin skin;

	//
	public static TextureRegion img_bg_1_;
	public static TextureRegion img_obj_btn_play;
	public static TextureRegion img_obj_btn_scores;
	public static TextureRegion img_obj_btn_settings;
	public static TextureRegion img_obj_circle;
	public static TextureRegion img_obj_flower_1_;
	public static TextureRegion img_obj_flower_2_;
	public static TextureRegion img_obj_loading;
	public static TextureRegion img_obj_mountains;
	public static TextureRegion img_obj_rectangle;
	public static TextureRegion img_obj_social_facebook;
	public static TextureRegion img_obj_social_google;
	public static TextureRegion img_obj_social_twitter;
	public static TextureRegion img_obj_sound_off;
	public static TextureRegion img_obj_sound_on;
	public static TextureRegion img_obj_swipe_down_menu;
	public static TextureRegion img_obj_swipe_up_instructions;
	public static TextureRegion img_obj_text_junglegamemenu;

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
		img_obj_btn_play = getAtlas().findRegion("img_obj_btn_play");
		img_obj_btn_scores = getAtlas().findRegion("img_obj_btn_scores");
		img_obj_btn_settings = getAtlas().findRegion("img_obj_btn_settings");
		img_obj_circle = getAtlas().findRegion("img_obj_circle");
		img_obj_flower_1_ = getAtlas().findRegion("img_obj_flower_1_");
		img_obj_flower_2_ = getAtlas().findRegion("img_obj_flower_2_");
		img_obj_loading = getAtlas().findRegion("img_obj_loading");
		img_obj_mountains = getAtlas().findRegion("img_obj_mountains");
		img_obj_rectangle = getAtlas().findRegion("img_obj_rectangle");
		img_obj_social_facebook = getAtlas().findRegion(
				"img_obj_social_facebook");
		img_obj_social_google = getAtlas().findRegion("img_obj_social_google");
		img_obj_social_twitter = getAtlas()
				.findRegion("img_obj_social_twitter");
		img_obj_sound_off = getAtlas().findRegion("img_obj_sound_off");
		img_obj_sound_on = getAtlas().findRegion("img_obj_sound_on");
		img_obj_swipe_down_menu = getAtlas().findRegion(
				"img_obj_swipe_down_menu");
		img_obj_swipe_up_instructions = getAtlas().findRegion(
				"img_obj_swipe_up_instructions");
		img_obj_text_junglegamemenu = getAtlas().findRegion(
				"img_obj_text_junglegamemenu");
	}

	public static void loadButtons() {

	}

	public static void loadFonts() {

	}

	public static void loadAnimations() {

	}

	public static void loadSoundsAndMusics() {
	}
}
