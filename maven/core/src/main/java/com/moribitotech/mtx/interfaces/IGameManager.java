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

package com.moribitotech.mtx.interfaces;

public interface IGameManager {

	/**
	 * Set up data that gathered from database (Server/TextFiles/SQLDB/Json/Xml
	 * etc...)
	 * */
	public void setUpData();

	/**
	 * Set up pre-sub-managers that needs to be constructed before world
	 * creation
	 * */
	public void setUpPreManagers();

	/**
	 * Set up post-sub-managers that needs to be constructed after world
	 * creation
	 * */
	public void setUpPostManagers();

	/**
	 * Set up world and layers
	 * */
	public void setUpWorld();

	/**
	 * Start a level
	 * **/
	public void startLevel(int levelNumber);

	/**
	 * Use this in GameManager's updater(), check the game state for win, lose,
	 * idle, over etc...
	 * */
	public void checkGameCondition();

	/**
	 * Use this method in SCREEN's render(). This can be used updating world,
	 * game condition, chracters and many others, it can be also used for MVC
	 * style development
	 * */
	public void update(float delta);

	/**
	 * Save game, suggested use in Hide() and Pause() methods of Screen class
	 * */
	public void saveGame();
}
