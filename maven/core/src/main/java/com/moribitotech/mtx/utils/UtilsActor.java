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

package com.moribitotech.mtx.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class UtilsActor {
	/**
	 * Get the rectangle of an actor from its current position and size
	 * */
	public static Rectangle getRectangleOfActor(Actor actor) {
		return new Rectangle(actor.getX(), actor.getY(), actor.getWidth(),
				actor.getHeight());
	}

	/**
	 * Set touchable for multiple actors at once
	 * */
	public static void setTouchable(Touchable touchable, Actor... actors) {
		for (Actor a : actors) {
			a.setTouchable(touchable);
		}
	}

	/**
	 * Set visible for multiple actors at once
	 * */
	public static void setVisible(boolean isVisible, Actor... actors) {
		for (Actor a : actors) {
			a.setVisible(isVisible);
		}
	}

	/**
	 * Set scale of multiple actors at once
	 * */
	public static void setScale(float sx, float sy, Actor... actors) {
		for (Actor a : actors) {
			a.setScale(sx, sy);
		}
	}

	/**
	 * Set size for multiple actors at once
	 * */
	public static void setSize(float w, float h, Actor... actors) {
		for (Actor a : actors) {
			a.setSize(w, h);
		}
	}
}
