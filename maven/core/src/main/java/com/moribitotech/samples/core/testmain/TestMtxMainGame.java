package com.moribitotech.samples.core.testmain;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.mtx.settings.MtxLogger;
import com.moribitotech.samples.core.testassets.Assets;
import com.moribitotech.samples.core.tests.Test0_AllTestsScreen;

public class TestMtxMainGame extends AbstractGame implements ApplicationListener {

	@Override
	public void create() {
        super.create();
		setScreen(new Test0_AllTestsScreen(this, "All Test Launchers Screen"));
	}

    @Override
    public void setUpAppSettings() {
        AppSettings.setUp(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 960, 540);

        // Set log true/false (Very informative logs during development)
        // When you publish the game set as false for performance
        // ##########################################
        MtxLogger.setLogs(true);
    }

    @Override
    public void setUpAssets() {
        Assets assets = new Assets();
        assets.loadAll();
        setAssets(assets);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setUpLoadingScreen() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
