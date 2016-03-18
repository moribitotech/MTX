package com.moribitotech.mtx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class UtilsAssets {

	/**
	 * LinearLinear - Smooth edges (Avoid pixelation as much as possible) </br>
	 * NearestNeatrest - For pixel perfect games </br> MipMapping - requires
	 * power of 2 textures/texture atlas, however it is the best quality at
	 * performace cost </br>
	 * */
	public enum Filter {
		Linear_Linear, Linear_Nearest, Nearest_Nearest, Nearest_Linear, MipMapLN_L
	}

	/**
	 * Load/Get a texture from internal file (Leave filter null if no filter
	 * needed).
	 * 
	 * @param file
	 *            internal file location
	 * @param filter
	 *            quick filter apply (leave null if no filter needed)
	 * */
	public static Texture loadTexture(String file, Filter filter) {
		Texture t = new Texture(Gdx.files.internal(file));
		if (filter != null)
			addFilter(t, filter);
		return t;
	}

	/**
	 * Load a font. Input file path without format type. Works only with ".png"
	 * and ".fnt". (Leave filter null if no filter needed).
	 * <p>
	 * 
	 * EXAMPLE:</br> User input for file: "data/font" </br> Auto fill will
	 * perform "font.png" and "font.fnt" for user.
	 * */
	public static BitmapFont loadFont(String file, boolean flip, Filter filter) {
		BitmapFont f = new BitmapFont(Gdx.files.internal(file + ".fnt"),
				Gdx.files.internal(file + ".png"), flip);
		if (filter != null)
			addFilter(f.getRegion().getTexture(), filter);
		return f;
	}

	/**
	 * Load a particle
	 * */
	public static ParticleEffect loadParticle(String file, String imageMainDir) {
		ParticleEffect pe = new ParticleEffect();
		pe.load(Gdx.files.internal(file + ".p"),
				Gdx.files.internal(imageMainDir));
		return pe;
	}

	/** Apply a filter to a texture */
	public static void addFilter(Texture t, Filter filter) {
		switch (filter) {
		case Linear_Linear:
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			break;
		case Linear_Nearest:
			t.setFilter(TextureFilter.Linear, TextureFilter.Nearest);
			break;
		case Nearest_Nearest:
			t.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			break;
		case Nearest_Linear:
			t.setFilter(TextureFilter.Nearest, TextureFilter.Linear);
			break;
		case MipMapLN_L:
			t.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
			break;
		default:
			t.setFilter(TextureFilter.Linear, TextureFilter.Nearest);
			break;
		}
	}
}
