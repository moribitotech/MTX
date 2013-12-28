package com.moribitotech.samples.core.testassets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.moribitotech.mtx.animation.AnimationCreator;
import com.moribitotech.mtx.asset.AbstractAssets;
import com.moribitotech.mtx.interfaces.IAssets;

public class Assets extends AbstractAssets implements IAssets {
	private final static String ASSETS_FOLDER = "data/mtx/samples/core";
	private final static String FILE_IMAGE_ATLAS = ASSETS_FOLDER + "/imageatlas.txt";
	private final static String FILE_UI_SKIN = "skin/uiskin.json";
	public static TextureAtlas imageAtlas;
	public static Skin skin;
	
	
	//
	public static TextureRegion imgMtxBg;
	public static TextureRegion imgBat;
	public static TextureRegion imgStar;
	public static TextureRegion imgStarHolder;
	public static TextureRegion imgTopApps;
	public static TextureRegion imgTransparentBlack;
	
	// 
	public static Animation animBatFlyRight;
	public static Animation animBatCircleRight;
	public static Animation animBatBlackFlyRight;
	public static Animation animLoadingSkull;
	
	//
	public static BitmapFont font2;
	
	//
	public static TextureRegion btnBatLocked;
	public static TextureRegion btnBatCircle;
	public static TextureRegion btnBatCirclePressed;
	public static TextureRegion btnBlackBat;
	public static TextureRegion btnBalckBatPressed;
	public static TextureRegion btnLevel;
	public static TextureRegion btnLevelPressed;
	public static TextureRegion btnAllMenu;
	public static TextureRegion btnAllMenuPressed;
	
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static TextureAtlas getAtlas(){
        if( imageAtlas == null ) {
        	imageAtlas = new TextureAtlas( Gdx.files.internal(FILE_IMAGE_ATLAS));
        }
        return imageAtlas;
    }
	
    public Skin getSkin(){
        if( skin == null ) {
            FileHandle skinFile = Gdx.files.internal(FILE_UI_SKIN);
            skin = new Skin( skinFile );
        }
        return skin;
    }
	
	public void loadAll(){
		relaseResources();
		loadImages();
		loadButtons();
		loadFonts();
		loadAnimations();
	}
	
	
	private static void relaseResources() {
		skin = null;
		imageAtlas = null;
	}

	public void loadImages(){
		imgMtxBg = new TextureRegion(loadTexture(ASSETS_FOLDER + "/bgmtx.jpg"), 0, 0, 800, 480);
		imgBat = getAtlas().findRegion("imgbat");
		imgStar = getAtlas().findRegion("imgstar");
		imgStarHolder = getAtlas().findRegion("imgstarholder");
		imgTopApps = getAtlas().findRegion("imgtopapps");
		imgTransparentBlack = getAtlas().findRegion("transparentblack");
	}
	
	public void loadButtons(){
		btnBatLocked = getAtlas().findRegion("btnbatlocked");
		btnBatCircle = getAtlas().findRegion("btnbatcircle");
		btnBatCirclePressed = getAtlas().findRegion("btnbatcirclepressed");
		btnBlackBat = getAtlas().findRegion("btnblackbat");
		btnBalckBatPressed = getAtlas().findRegion("btnblackbatpressed");
		btnLevel = getAtlas().findRegion("btnlevel");
		btnLevelPressed = getAtlas().findRegion("btnlevelpressed");
		btnAllMenu = getAtlas().findRegion("btnallmenu");
		btnAllMenuPressed = getAtlas().findRegion("btnallmenupressed");
	}
	
	public void loadFonts(){
		font2 = new BitmapFont(Gdx.files.internal(ASSETS_FOLDER + "/font2.fnt"), Gdx.files.internal(ASSETS_FOLDER + "/font2.png"), false);
	}
	
	public void loadAnimations(){
		animBatFlyRight = AnimationCreator.getAnimationFromSingleTexture(getAtlas(), "animbatflyright", 6, 0.065f);
		animBatBlackFlyRight  = AnimationCreator.getAnimationFromSingleTexture(getAtlas(), "animbatblackflyright", 6, 0.065f);
		animBatCircleRight = AnimationCreator.getAnimationFromSingleTexture(getAtlas(), "animbatcircleright", 6, 0.065f);
		animLoadingSkull = AnimationCreator.getAnimationFromSingleTexture(getAtlas(), "loadinganim", 6, 0.065f);

	}

    @Override
    public void loadSkin() {
    }

    @Override
    public void loadTextureAtlas() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void loadSoundsAndMusics() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
