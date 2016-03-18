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

package com.moribitotech.mtx.scene2d.models;

import com.moribitotech.mtx.scene2d.AbstractActor;

public class EmptyActor extends AbstractActor {
	
	public EmptyActor(float posX, float posY, float width, float height) {
		super(posX, posY, width, height);
	}

	public EmptyActor(float width, float height, boolean DIPActive) {
		super(width, height, DIPActive);
	}
}
