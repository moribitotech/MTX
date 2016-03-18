package com.moribitotech.samples.jungle;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "MtxJungleGameMenu";
		cfg.width = 800;
		cfg.height = 480;

		new LwjglApplication(new MainStarter(), cfg);
	}
}
