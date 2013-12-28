package com.moribitotech.mtx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "A_MtxFramework";
		cfg.useGL20 = true;
		cfg.width = 960;
		cfg.height = 540;
		
		new LwjglApplication(new MainStarter(), cfg);
	}
}
