/*******************************************************************************
 * Copyright 2012-Present, MoribitoTech
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.moribitotech.mtx.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.moribitotech.mtx.settings.MtxLogger;

public abstract class AbstractAssets implements AssetErrorListener {
	//
	protected final String logTag = "MtxAssetsLog";
	public static boolean logActive = true;
	//
	private AssetManager assetManager;
	private TextureAtlas textureAtlas;
	private Skin skin;

	public AbstractAssets() {
		assetManager = new AssetManager();
		skin = new Skin();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}

	public void setTextureAtlas(TextureAtlas textureAtlas) {
		this.textureAtlas = textureAtlas;
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	@Override
    public void error (AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error("AssetManager", "couldn't load asset '" + asset.fileName);
	}

	public void logAssetManagerProgress() {
		MtxLogger.log(logActive, true, logTag, "Assets Loading: "
				+ getAssetManager().getProgress());
	}
}
