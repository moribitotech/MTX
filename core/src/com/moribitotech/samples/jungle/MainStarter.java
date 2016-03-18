package com.moribitotech.samples.jungle;

import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.samples.jungle.assets.Assets;
import com.moribitotech.samples.jungle.screens.JungleMainMenuScreen;

public class MainStarter extends AbstractGame {

	@Override
	public void create() {
        super.create();
		//
		// Set the tests screen
		// #####################################
		setScreen(new JungleMainMenuScreen(this, "Main Menu"));
	}

    @Override
    public void setUpAppSettings() {
        AppSettings.setUp();
    }

    @Override
    public void setUpAssets() {
        Assets.loadAll();
    }

    @Override
    public void setUpLoadingScreen() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
