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

public interface IScreen {
	/**
	 * Set up screen elements. Call this method in constructor first
	 * <p>
	 * SUCH AS:<br>
	 * - Load assets<br>
	 * - Set back button active<br>
	 * - Set background texture<br>
	 * */
	public void setUpScreenElements();

	/**
	 * Set up screen menu
	 * */
	public void setUpMenu();
}
