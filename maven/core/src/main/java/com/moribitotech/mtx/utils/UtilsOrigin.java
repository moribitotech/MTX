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

import com.badlogic.gdx.scenes.scene2d.Actor;

public class UtilsOrigin {
	public enum Origin {
		CENTER, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, LEFT_CENTER, TOP_CENTER, BOTTOM_CENTER, RIGHT_CENTER
	}

	/**
	 * Set origin of an actor, since actors are complex objects, its variables
	 * pointing same reference with copies, so the origin will be set in
	 * original object
	 * 
	 * @param actor
	 *            actor to set the origin
	 * @param origin
	 *            position of the origin, comes from Origin enum class
	 * */
	public static void setActorOrigin(Actor actor, Origin origin) {
		setOrigin(actor, origin);
	}

	/**
	 * Set multiple actors origin at once
	 * 
	 * @param origin
	 *            position of the origin, comes from Origin enum class
	 * @param actors
	 *            multiple actors to set the origin
	 * 
	 * */
	public static void setActorsOrigin(Origin origin, Actor... actors) {
		for (Actor a : actors) {
			setOrigin(a, origin);
		}
	}

	private static void setOrigin(Actor actor, Origin origin) {
		switch (origin) {
		case CENTER:
			actor.setOrigin(actor.getWidth() / 2.0f, actor.getHeight() / 2.0f);
			break;
		case TOP_LEFT:
			actor.setOrigin(0.0f, actor.getHeight());
			break;
		case TOP_RIGHT:
			actor.setOrigin(actor.getWidth(), actor.getHeight());
			break;
		case BOTTOM_LEFT:
			actor.setOrigin(0.0f, 0.0f);
			break;
		case BOTTOM_RIGHT:
			actor.setOrigin(actor.getWidth(), 0.0f);
			break;
		case LEFT_CENTER:
			actor.setOrigin(0.0f, actor.getHeight() / 2.0f);
			break;
		case TOP_CENTER:
			actor.setOrigin(actor.getWidth() / 2.0f, actor.getHeight());
			break;
		case BOTTOM_CENTER:
			actor.setOrigin(actor.getWidth() / 2.0f, 0.0f);
			break;
		case RIGHT_CENTER:
			actor.setOrigin(actor.getWidth(), actor.getHeight() / 2.0f);
			break;
		default:
			actor.setOrigin(actor.getOriginX(), actor.getOriginY());
			break;
		}
	}
}
