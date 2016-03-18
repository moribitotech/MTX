package com.moribitotech.samples.core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.moribitotech.mtx.MainStarter;
import com.moribitotech.samples.core.testmain.TestMtxMainGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "A_MtxFramework";
		cfg.width = 960;
		cfg.height = 540;

		new LwjglApplication(new TestMtxMainGame(), cfg);
	}
}
