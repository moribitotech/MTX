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

package com.moribitotech.mtx.input;

public class InputIntent {
	//
	protected final static String logTag = "MtxInputIntentLog";
	public static boolean logActive = true;
	//
	private float touchInitialX = 0.0f, touchInitialY = 0.0f;
	private float touchCurrentX = 0.0f, touchCurrentY = 0.0f;
	//
	private float touchDragIntervalRange = 0.0f;
	//
	private float difX;
	private float difY;
	private float prevDifX;
	private float prevDifY;
	//
	private float touchDifX;
	private float touchDifY;

	public enum DirectionIntent {
		TOUCH_IDLE, TOUCH_D_UP, TOUCH_D_DOWN, TOUCH_D_LEFT, TOUCH_D_RIGHT
	}

	public enum InputAreaTopBottom {
		TOP_HALF, BOTTOM_HALF
	}

	public enum InputAreaLeftRight {
		LEFT_HALF, RIGHT_HALF
	}

	public InputIntent() {
		reset();
	}

	/**
	 * if touch drag interval is set, by using this check method, a range of
	 * drag can be detected
	 * <p>
	 * 
	 * EXAMPLE:<br>
	 * touchDragIntervalRange is 100px, user puts finger on screen and drags his
	 * finger, after 100px dragging, this method will return true. This can be
	 * very handy for real swipe intentions. For example, user swipe down to get
	 * notification center, but you would not want to get notification center,
	 * for very little swipe downs like 2, 3px
	 * */
	public boolean isTouchDragInterval() {
		difX = Math.abs(touchInitialX - touchCurrentX);
		difY = Math.abs(touchInitialY - touchCurrentY);
		//
		if (difX > touchDragIntervalRange || difY > touchDragIntervalRange) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get drag intention
	 * <p>
	 * 
	 * TOUCH_IDLE, TOUCH_D_UP, TOUCH_D_DOWN, TOUCH_D_LEFT, TOUCH_D_RIGHT
	 * */
	public DirectionIntent getDirectionIntent() {
		storePrevDiffs();
		//
		difX = Math.abs(touchInitialX - touchCurrentX);
		difY = Math.abs(touchInitialY - touchCurrentY);
		//
		checkMomentumChange();
		//
		if (touchInitialY < touchCurrentY && difY > difX) {
			return DirectionIntent.TOUCH_D_UP;
		} else if (touchInitialY > touchCurrentY && difY > difX) {
			return DirectionIntent.TOUCH_D_DOWN;
		} else if (touchInitialX < touchCurrentX && difY < difX) {
			return DirectionIntent.TOUCH_D_RIGHT;
		} else if (touchInitialX > touchCurrentX && difY < difX) {
			return DirectionIntent.TOUCH_D_LEFT;
		} else {
			return DirectionIntent.TOUCH_IDLE;
		}
	}

	/**
	 * Store for momentum change
	 * */
	private void storePrevDiffs() {
		prevDifX = difX;
		prevDifY = difY;
	}

	/**
	 * Get minimal changes on drag
	 * <p>
	 * 
	 * EXAMPLE<br>
	 * User drags finger to left, suddenly dragging to right without removing
	 * his finger from the screen
	 * */
	private void checkMomentumChange() {
		if (prevDifX > difX || prevDifY > difY) {
			touchInitialX = touchCurrentX;
			touchInitialY = touchCurrentY;
			//
			difX = 0.0f;
			difY = 0.0f;
			prevDifX = 0.0f;
			prevDifY = 0.0f;
			//
			setTouchDifferences();
		}
	}

	/**
	 * Reset everything. USE THIS: on touchUp methods
	 * 
	 * */
	public void reset() {
		difX = 0.0f;
		difY = 0.0f;
		touchInitialX = 0.0f;
		touchInitialY = 0.0f;
		touchCurrentX = 0.0f;
		touchCurrentY = 0.0f;
		touchDifX = 0.0f;
		touchDifY = 0.0f;
		prevDifX = 0.0f;
		prevDifY = 0.0f;
	}

	/**
	 * Set initial touches. USE THIS: on touchDown method
	 * 
	 * */
	public void setTouchInitials(float x, float y) {
		touchInitialX = x;
		touchInitialY = y;
	}

	/**
	 * Set current touches, draggings. USE THIS: on touchDrag, pan, or similar
	 * methods
	 * 
	 * */
	public void setTouchCurrents(float x, float y) {
		touchCurrentX = x;
		touchCurrentY = y;
	}

	public float getTouchInitialX() {
		return touchInitialX;
	}

	public void setTouchInitialX(float touchInitialX) {
		this.touchInitialX = touchInitialX;
	}

	public float getTouchInitialY() {
		return touchInitialY;
	}

	public void setTouchInitialY(float touchInitialY) {
		this.touchInitialY = touchInitialY;
	}

	public float getTouchCurrentX() {
		return touchCurrentX;
	}

	public void setTouchCurrentX(float touchCurrentX) {
		this.touchCurrentX = touchCurrentX;
	}

	public float getTouchCurrentY() {
		return touchCurrentY;
	}

	public void setTouchCurrentY(float touchCurrentY) {
		this.touchCurrentY = touchCurrentY;
	}

	/**
	 * Get tuch drag interval range
	 * */
	public float getTouchDragIntervalRange() {
		return touchDragIntervalRange;
	}

	/**
	 * if touch drag interval is set, by using this check method, a range of
	 * drag can be detected
	 * <p>
	 * 
	 * EXAMPLE:<br>
	 * User swipe down to get notification center, but you would not want to get
	 * notification center, for very little swipe downs like 2, 3px. Set this as
	 * 100px, so by using isTouchDragInterval() method, you can confirm swipe
	 * down, after 100px.
	 * */
	public void setTouchDragIntervalRange(float touchDragIntervalRange) {
		this.touchDragIntervalRange = touchDragIntervalRange;
	}

	/**
	 * Set touch differences, optional, if you need amount of change from
	 * initial touch to drag, USE THIS: on touchDrag, pan or similar mthods
	 * */
	public void setTouchDifferences() {
		touchDifX = Math.abs(touchInitialX - touchCurrentX);
		touchDifY = Math.abs(touchInitialY - touchCurrentY);
	}

	/**
	 * Get touch difference on X-axis from initial point, if you change your
	 * drag direction without removing finger from screen, inital touch will be
	 * the point of direction change
	 * */
	public float getTouchDifX() {
		return touchDifX;
	}

	/**
	 * Set touch diff X
	 * */
	public void setTouchDifX(float touchDifX) {
		this.touchDifX = touchDifX;
	}

	/**
	 * Get touch difference on Y-axis from initial point, if you change your
	 * drag direction without removing finger from screen, inital touch will be
	 * the point of direction change
	 * */
	public float getTouchDifY() {
		return touchDifY;
	}

	/**
	 * Set touch diff Y
	 * */
	public void setTouchDifY(float touchDifY) {
		this.touchDifY = touchDifY;
	}

	/**
	 * Check which top or bottom part of screen currently being touched
	 * */
	public InputAreaTopBottom getInputAreaTopBottom(float screenHeight) {
		if (touchCurrentY > screenHeight / 2.0f) {
			return InputAreaTopBottom.TOP_HALF;
		} else {
			return InputAreaTopBottom.BOTTOM_HALF;
		}
	}

	/**
	 * Check which left or right part of screen currently being touched
	 * */
	public InputAreaLeftRight getInputAreaLeftRight(float screenWidth) {
		if (touchCurrentX > screenWidth / 2.0f) {
			return InputAreaLeftRight.RIGHT_HALF;
		} else {
			return InputAreaLeftRight.LEFT_HALF;
		}
	}
}
