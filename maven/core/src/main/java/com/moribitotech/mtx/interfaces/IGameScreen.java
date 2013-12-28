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

public interface IGameScreen {
	/**
	 * Set up game manager, add "GameManager.Update()" method to render() method of Screen,
	 * game manager also handles World creation and management
	 * */
	public void setUpGameManager();
	
	/**
	 * Set up the menu for the game, game buttons, power ups, pause, play, etc...
	 * */
	public void setUpGameMenu();
}
