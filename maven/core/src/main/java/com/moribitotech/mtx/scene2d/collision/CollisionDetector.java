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

package com.moribitotech.mtx.scene2d.collision;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.moribitotech.mtx.settings.MtxLogger;
import com.moribitotech.mtx.utils.UtilsActor;

public class CollisionDetector {
	protected static final String logTag = "MtxCollisionDetectorLog";
	public static boolean logActive = true;

	/**
	 * Check collision from actor's rectangles
	 * */
	public static boolean isActorsCollide(Actor actor1, Actor actor2) {
		if (Intersector.overlaps(UtilsActor.getRectangleOfActor(actor1),
				UtilsActor.getRectangleOfActor(actor2))) {
			logCollision1(actor1, actor2);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Very precise point detection in a box, think as virtual box in the actual
	 * box with padding as precision amount
	 * */
	public static boolean isTouchPointCollide(float touchX, float touchY,
			float posX, float posY, float width, float height,
			float precisionAmount) {
		if (touchX > (posX + precisionAmount)
				&& touchX < (posX + width - precisionAmount)
				&& touchY > (posY + precisionAmount)
				&& touchY < (posY + height - precisionAmount)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Very precise point detection in an actor, think as virtual box in the
	 * actual box with margin as precision amount
	 * */
	public static boolean isTouchPointCollide(float touchX, float touchY,
			Actor actor, float precisionAmount) {
		if (touchX > (actor.getX() + precisionAmount)
				&& touchX < (actor.getX() + actor.getWidth() - precisionAmount)
				&& touchY > (actor.getY() + precisionAmount)
				&& touchY < (actor.getY() + actor.getHeight() - precisionAmount)) {
			logCollision2(actor);
			return true;
		} else {
			return false;
		}
	}

	private static void logCollision1(Actor a1, Actor a2) {
		MtxLogger.log(logActive, true, logTag,
				"Collision detected: Actor (Name: " + a1.getName()
						+ ") and Actor (Name: " + a2.getName() + ")");
	}

	private static void logCollision2(Actor a1) {
		MtxLogger.log(
				logActive,
				true,
				logTag,
				"Collision detected on touch point: Actor (Name: "
						+ a1.getName() + ")");
	}
}
