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

package com.moribitotech.mtx.managers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.moribitotech.mtx.utils.UtilsNumbers;

public class TiltManager {
	private boolean isSensorActive;
	private ArrayList<Float> accelListForAccuracy;

	public enum Accel {
		X, Y, Z
	}

	public TiltManager() {
		isSensorActive = true;
		accelListForAccuracy = new ArrayList<Float>();
	}

	public float getAccelX() {
		if (isSensorActive) {
			return Gdx.input.getAccelerometerX();
		} else {
			return 0.0f;
		}
	}

	public float getAccelY() {
		if (isSensorActive) {
			return Gdx.input.getAccelerometerY();
		} else {
			return 0.0f;
		}
	}

	public float getAccelZ() {
		if (isSensorActive) {
			return Gdx.input.getAccelerometerZ();
		} else {
			return 0.0f;
		}
	}

	public float getAccurateAccel(Accel accel, int numberOfAvarage) {
		// FIXME
		// Not works fine

		float result = 0.0f;

		if (accel == Accel.X) {
			result = getAccelX();
		} else if (accel == Accel.Y) {
			result = getAccelY();
		} else if (accel == Accel.Z) {
			result = getAccelZ();
		} else {
			return result;
		}

		accelListForAccuracy.add(result);
		//
		if (accelListForAccuracy.size() >= numberOfAvarage) {
			result = UtilsNumbers.calculateAverage(accelListForAccuracy);
			accelListForAccuracy.clear();
			return result;
		} else {
			return 0.0f;
		}
	}

	public boolean isSensorActive() {
		return isSensorActive;
	}

	public void setSensorActive(boolean isSensorActive) {
		this.isSensorActive = isSensorActive;
	}
}
