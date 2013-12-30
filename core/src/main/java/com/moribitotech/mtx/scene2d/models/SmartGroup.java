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

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.moribitotech.mtx.scene2d.AbstractGroup;
import com.moribitotech.mtx.scene2d.effects.EffectCreator;

public class SmartGroup extends AbstractGroup {
	private Random rnd;
	private boolean isFound;

	// Smart model actions
	private boolean isActionMoveFreely;
	private boolean isActionMoveToDirection;
	private boolean isActionFadeInOut;
	private boolean isActionRotate;
	private boolean isActionScale;
	private boolean isActionMoveSideToSide;

	// Action move freely
	private int timerRangeNewMoveDecision;
	private int timeLeftForNewMoveDecision;
	private int moveRangeX;
	private int moveRangeY;
	private float durationSpeedMoveFreely;

	// Action fadeInOut
	private int timerRangeNewFadeInOutDecision;
	private int timeLeftForNewFadeInOutDecision;
	private float durationSpeedFadeInOut;
	private boolean isFadeOut;
	private boolean isActionFadeInOuUsingRandomTimeRange;

	// Action move to direction
	private boolean isMTDRandomX;
	private int topLeftX;
	private int topRightX;
	private int bottomLeftX;
	private int bottomRightX;
	private int bottomLeftY;
	private int speedMTD;
	private boolean isTopDown;
	private int speedMTDMinimum;
	private int topY;
	private int bottomY;

	// Action rotate
	private int rotateAngleRange;
	private int durationSpeedRotate;
	private int timerRangeNewRotateDecision;
	private int timeLeftForNewRotateDecision;
	private boolean isActionRotateRandomTime;

	// Action scale
	private int timerRangeForSclae;
	private int timeLeftForNewRScaleDecision;
	private float scaleRatioWidth;
	private float scaleRatioHeight;
	private float durationSpeedScale;
	private boolean isActionScaleRandomTime;

	// Action move side to side
	private int timerRangeNewMoveSideToSideDecision;
	private int timeLeftForNewMoveSTSDecision;
	private float speedMoveSTSFreely;
	private int rndSTSY;
	private int moveSTSStartX;
	private int moveSTSEndX;

	public SmartGroup(float posX, float posY, float width, float height,
			Random rnd) {
		super(posX, posY, width, height);
		// Reset settings;
		reset();

		//
		this.rnd = rnd;
	}

	public SmartGroup(float width, float height, Random rnd,
			boolean DIPActive) {
		super(width, height, DIPActive);
		// Reset settings;
		reset();

		//
		this.rnd = rnd;
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (isActionMoveFreely) {
			if (getSecondsTime() > timeLeftForNewMoveDecision) {
				makeNewMoveFreelyDecision();
			}
		}

		if (isActionMoveSideToSide) {
			if (getSecondsTime() > timeLeftForNewMoveSTSDecision) {
				makeNewMoveSTSDecision();
			}
		}

		if (isActionFadeInOut) {
			if (getSecondsTime() > timeLeftForNewFadeInOutDecision) {
				if (isFadeOut) {
					fadeIn();
				} else {
					fadeOut();
				}
			}
		}

		if (isActionMoveToDirection) {
			if (isActionMoveToDirectionCompleted()) {
				makeNewMoveToDirectionDecision();
			}
		}

		if (isActionRotate) {
			if (getSecondsTime() > timeLeftForNewRotateDecision) {
				makeNewRotateDecision();
			}
		}

		if (isActionScale) {
			if (getSecondsTime() > timeLeftForNewRScaleDecision) {
				makeNewScaleDecision();
			}
		}
	}

	// ACTION SCALE
	// ###################################################################################
	/**
	 * Start Action Scale
	 * <p>
	 * 
	 * EXAMPLE:
	 * <p>
	 * startActionScale(10,1.5f,1.5f,3.0f,true)<br>
	 * Every random number of seconds (10), scale actor by 1.5f (WidthxHeight)
	 * and back to normal, scale will be done in 3.0f seconds
	 * */
	public void startActionScale(int timerRangeForScale, float scaleRatioWidth,
			float scaleRatioHeight, float durationSpeedScale,
			boolean isActionScaleRandomTime) {
		//
		isActionScale = true;
		//
		this.timerRangeForSclae = timerRangeForScale;
		this.scaleRatioWidth = scaleRatioWidth;
		this.scaleRatioHeight = scaleRatioHeight;
		this.durationSpeedScale = durationSpeedScale;
		this.isActionScaleRandomTime = isActionScaleRandomTime;
		//
		makeNewScaleDecision();
	}

	/**
	 * Force actor to make action decision, Normally done after this action's
	 * time is up with paradox/looping
	 * */
	public void makeNewScaleDecision() {
		if (isActionScaleRandomTime) {
			timeLeftForNewRScaleDecision = rnd.nextInt(timerRangeForSclae);
		} else {
			timeLeftForNewRScaleDecision = timerRangeForSclae;
		}
		timeLeftForNewRScaleDecision = (int) ((int) getSecondsTime()
				+ timeLeftForNewRScaleDecision + durationSpeedScale);

		EffectCreator.create_SC_BTN(this, scaleRatioWidth, scaleRatioHeight,
				durationSpeedScale, null, false);
	}

	// ACTION ROTATE
	// ###################################################################################
	/**
	 * Start Action Rotate
	 * <p>
	 * 
	 * EXAMPLE:
	 * <p>
	 * startActionRotate(15,360,2.0f,true)<br>
	 * Every random number of seconds (15), rotate actor by randomly in range
	 * 360 degrees, rotate will be done in 2.0f seconds
	 * */
	public void startActionRotate(int timerRangeNewRotateDecision,
			int rotateAngleRange, int durationSpeedRotate,
			boolean isActionRotateRandomTime) {
		//
		isActionRotate = true;
		//
		this.timerRangeNewRotateDecision = timerRangeNewRotateDecision;
		this.rotateAngleRange = rotateAngleRange;
		this.durationSpeedRotate = durationSpeedRotate;
		this.isActionRotateRandomTime = isActionRotateRandomTime;
		//
		makeNewRotateDecision();
	}

	/**
	 * Force actor to make action decision, Normally done after this action's
	 * time is up with paradox/looping
	 * */
	public void makeNewRotateDecision() {
		if (isActionRotateRandomTime) {
			timeLeftForNewRotateDecision = rnd
					.nextInt(timerRangeNewRotateDecision);
		} else {
			timeLeftForNewRotateDecision = timerRangeNewRotateDecision;
		}
		timeLeftForNewRotateDecision = (int) getSecondsTime()
				+ timeLeftForNewRotateDecision + durationSpeedRotate;

		// FIXME
		// RANDOM FUCKS THE EFFECT
		// int rndDuration = rnd.nextInt(durationSpeedRotate) +
		// durationSpeedRotateMinimum;
		int rndAngele = rnd.nextInt(rotateAngleRange);
		addAction(Actions.rotateTo(rndAngele, durationSpeedRotate));
	}

	// ACTION MOVE TO DIRECTION
	// ###################################################################################
	/**
	 * Start Action Move To Direction (NEGATIVE RANGE IS USABLE)
	 * <p>
	 * EXAMPLES <br>
	 * - startActionMoveToDirection(int[0,800], int[0,400], 900, -100, 10, 5,
	 * false, true): start from in a range (0-800) X-axis from 900 Y-axis and
	 * move to in a range (0,400) X-axis and -100 Y-axis, when direction
	 * complete repeat again (paradox/looping), speed duration 10 seconds and
	 * minimum speed duration 5 seconds, move top down (snow effect), use X-axis
	 * randomizer
	 * */
	public void startActionMoveToDirection(int[] topRangeX, int[] bottomRangeX,
			int topY, int bottomY, int speedMTD, int speedMTDMinimum,
			boolean isTopDown, boolean isMTDRandomX) {
		//
		isActionMoveToDirection = true;
		//
		this.topLeftX = topRangeX[0];
		this.topRightX = topRangeX[1];
		this.bottomLeftX = bottomRangeX[0];
		this.bottomRightX = bottomRangeX[1];
		//
		this.topY = topY;
		this.bottomY = bottomY;
		//
		this.speedMTD = speedMTD;
		this.speedMTDMinimum = speedMTDMinimum;
		this.isTopDown = isTopDown;
		this.isMTDRandomX = isMTDRandomX;
		// =
		makeNewMoveToDirectionDecision();
	}

	/**
	 * Force actor to make action decision, Normally done after this action's
	 * time is up with paradox/looping
	 * */
	private void makeNewMoveToDirectionDecision() {
		int topX;
		int bottomX;

		if (isMTDRandomX) {
			topX = getRandomNumber(rnd, topLeftX, topRightX);
			bottomX = getRandomNumber(rnd, bottomLeftX, bottomRightX);
		} else {
			topX = topLeftX;
			bottomX = bottomLeftY;
		}

		int rndSpeed = rnd.nextInt(speedMTD) + speedMTDMinimum;
		if (isTopDown) {
			setPosition(topX, topY);
			addAction(Actions.moveTo(bottomX, bottomY, rndSpeed));
		} else {
			setPosition(bottomX, bottomY);
			addAction(Actions.moveTo(topX, topY, rndSpeed));
		}

	}

	private int getRandomNumber(Random rndGen, int min, int max) {
		if (min < 0 && max <= 0) {
			min *= -1;
			max *= -1;
			int rndNumber = rnd.nextInt(min - max + 1) + max;
			return rndNumber * -1;
		} else if (min < 0 && max >= 0) {
			min *= -1;
			int totalRange = min + max;
			int rndNumber = rnd.nextInt(totalRange) + (min * -1);
			return rndNumber;
		} else if (min > 0 && max <= 0) {
			// IMPOSSIBLE CALCULATION
			min *= -1;
			max *= -1;
			int rndNumber = rnd.nextInt(min - max + 1) + max;
			return rndNumber * -1;
		} else {
			int rndNumber = rnd.nextInt(max - min + 1) + min;
			return rndNumber;
		}
	}

	private boolean isActionMoveToDirectionCompleted() {
		if (isTopDown) {
			if (getY() <= bottomY) {
				return true;
			} else {
				return false;
			}
		} else {
			if (getY() >= topY) {
				return true;
			} else {
				return false;
			}
		}
	}

	// ACTION FADE IN OUT
	// ###################################################################################
	/**
	 * Start Fade IN/OUT action
	 * <p>
	 * EXAMPLES <br>
	 * - startActionFadeInOut(1,1,false): fading in and out every second without
	 * random time<br>
	 * - startActionFadeInOut(10,1,false): fading in and out every 10 seconds
	 * without random time<br>
	 * - startActionFadeInOut(10,1,true): fading in and out randomly in 10
	 * seconds range<br>
	 * */
	public void startActionFadeInOut(int timerRangeNewFadeInOutDecision,
			int durationSpeedFadeInOut,
			boolean isActionFadeInOuUsingRandomTimeRange) {
		isActionFadeInOut = true;
		//
		this.timerRangeNewFadeInOutDecision = timerRangeNewFadeInOutDecision;
		this.durationSpeedFadeInOut = durationSpeedFadeInOut;
		this.isActionFadeInOuUsingRandomTimeRange = isActionFadeInOuUsingRandomTimeRange;
		//
		fadeOut();
	}

	public void fadeOut() {
		if (isActionFadeInOuUsingRandomTimeRange) {
			timeLeftForNewFadeInOutDecision = (int) (rnd
					.nextInt(timerRangeNewFadeInOutDecision) + durationSpeedFadeInOut);
		} else {
			timeLeftForNewFadeInOutDecision = (int) (timerRangeNewFadeInOutDecision + durationSpeedFadeInOut);
		}
		timeLeftForNewFadeInOutDecision = (int) getSecondsTime()
				+ timeLeftForNewFadeInOutDecision;
		addAction(Actions.fadeOut(durationSpeedFadeInOut));
		isFadeOut = true;
	}

	public void fadeIn() {
		if (isActionFadeInOuUsingRandomTimeRange) {
			timeLeftForNewFadeInOutDecision = rnd
					.nextInt(timerRangeNewFadeInOutDecision);
		} else {
			timeLeftForNewFadeInOutDecision = timerRangeNewFadeInOutDecision;
		}
		timeLeftForNewFadeInOutDecision = rnd
				.nextInt(timerRangeNewFadeInOutDecision);
		timeLeftForNewFadeInOutDecision = (int) getSecondsTime()
				+ timeLeftForNewFadeInOutDecision;
		addAction(Actions.fadeIn(durationSpeedFadeInOut));
		isFadeOut = false;
	}

	// ACTION MOVE FREELY
	// ###################################################################################
	/**
	 * Start Action Move Freely (NEGATIVE RANGE ARE NOT ACCEPTED IN THIS METHOD)
	 * <p>
	 * EXAMPLES <br>
	 * - startActionMoveFreely(10,480,800,10): move freely within 480,800
	 * randomly in 10 seconds range, after time is up actor will make new
	 * decision <br>
	 * */
	public void startActionMoveFreely(int timerRangeNewMoveDecision,
			int moveRangeX, int moveRangeY, float durationSpeedMoveFreely) {
		isActionMoveFreely = true;
		//
		this.timerRangeNewMoveDecision = timerRangeNewMoveDecision;
		this.moveRangeX = moveRangeX;
		this.moveRangeY = moveRangeY;
		this.durationSpeedMoveFreely = durationSpeedMoveFreely;
		//
		makeNewMoveFreelyDecision();
	}

	/**
	 * Force actor to make action decision, Normally done after this action's
	 * time is up with paradox/looping
	 * */
	private void makeNewMoveFreelyDecision() {
		timeLeftForNewMoveDecision = rnd.nextInt(timerRangeNewMoveDecision);
		timeLeftForNewMoveDecision = (int) ((int) getSecondsTime()
				+ timeLeftForNewMoveDecision + durationSpeedMoveFreely);
		//
		if (moveRangeX > 0 && moveRangeY > 0) {
			addAction(Actions.moveTo(rnd.nextInt(moveRangeX),
					rnd.nextInt(moveRangeY), durationSpeedMoveFreely));
		} else if (moveRangeX > 0 && moveRangeY <= 0) {
			addAction(Actions.moveTo(rnd.nextInt(moveRangeX), 0,
					durationSpeedMoveFreely));
		} else if (moveRangeX <= 0 && moveRangeY > 0) {
			addAction(Actions.moveTo(0, rnd.nextInt(moveRangeY),
					durationSpeedMoveFreely));
		} else {
			// NOT MOVING
		}
	}

	// ACTION MOVE SIDE TO SIDE
	// ###################################################################################
	/**
	 * Start Action Side To Side
	 * 
	 * <p>
	 * EXAMPLES <br>
	 * - startActionMoveSiteToSide(10,0,800,500,10): Move one side and then move
	 * to other side in 10 seconds range, start form 0 X-axis, move to an X-axis
	 * in (0,800) range Y-axis is stable, 500. <br>
	 * */
	public void startActionMoveSideToSideFreely(
			int timerRangeNewMoveSideToSideDecision, int moveSTSStartX,
			int moveSTSEndX, int rndSTSY, float speedMoveSTSFreely) {
		isActionMoveSideToSide = true;
		//
		this.rndSTSY = rndSTSY;
		this.timerRangeNewMoveSideToSideDecision = timerRangeNewMoveSideToSideDecision;
		this.moveSTSStartX = moveSTSStartX;
		this.moveSTSEndX = moveSTSEndX;
		this.speedMoveSTSFreely = speedMoveSTSFreely;
		//
		makeNewMoveSTSDecision();
	}

	/**
	 * Force actor to make action decision, Normally done after this action's
	 * time is up with paradox/looping
	 * */
	private void makeNewMoveSTSDecision() {
		timeLeftForNewMoveSTSDecision = rnd
				.nextInt(timerRangeNewMoveSideToSideDecision);
		timeLeftForNewMoveSTSDecision = (int) ((int) getSecondsTime()
				+ timeLeftForNewMoveSTSDecision + speedMoveSTSFreely);
		//
		int rndNumber = rnd.nextInt(10);
		int finalSTSX = 0;
		//
		if (rndNumber <= 5) {
			// minus condition
			if (moveSTSStartX < 0) {
				moveSTSStartX *= -1;
				finalSTSX = rnd.nextInt(moveSTSStartX);
				finalSTSX *= -1;
			} else {
				finalSTSX = rnd.nextInt(moveSTSStartX);
			}
		} else {
			finalSTSX = rnd.nextInt(moveSTSEndX);
		}

		addAction(Actions.moveTo(finalSTSX, rndSTSY, speedMoveSTSFreely));
	}

	// SETTER and GETTERS and OTHER HELPERS
	// ###################################################################################
	public void reset() {
		clearActions();
		//
		isActionMoveFreely = false;
		isActionMoveToDirection = false;
		isActionFadeInOut = false;
		isActionRotate = false;
		isActionScale = false;
		isActionMoveSideToSide = false;
		//
		durationSpeedMoveFreely = 10f;
		durationSpeedFadeInOut = 1f;
	}

	public int getTimerRangeNewMoveDecision() {
		return timerRangeNewMoveDecision;
	}

	public void setTimerRangeNewMoveDecision(int timerRangeNewMoveDecision) {
		this.timerRangeNewMoveDecision = timerRangeNewMoveDecision;
	}

	public int getMoveRangeX() {
		return moveRangeX;
	}

	public void setMoveRangeX(int moveRangeX) {
		this.moveRangeX = moveRangeX;
	}

	public int getMoveRangeY() {
		return moveRangeY;
	}

	public void setMoveRangeY(int moveRangeY) {
		this.moveRangeY = moveRangeY;
	}

	public float getdurationSpeedMoveFreely() {
		return durationSpeedMoveFreely;
	}

	public void setdurationSpeedMoveFreely(float durationSpeedMoveFreely) {
		this.durationSpeedMoveFreely = durationSpeedMoveFreely;
	}

	public int getTimeLeftForNewMoveDecision() {
		return timeLeftForNewMoveDecision;
	}

	public void setTimeLeftForNewMoveDecision(int timeLeftForNewMoveDecision) {
		this.timeLeftForNewMoveDecision = timeLeftForNewMoveDecision;
	}

	public boolean isActionMoveFreely() {
		return isActionMoveFreely;
	}

	public void setActionMoveFreely(boolean isActionMoveFreely) {
		this.isActionMoveFreely = isActionMoveFreely;
	}

	public boolean isActionFadeInOut() {
		return isActionFadeInOut;
	}

	public void setActionFadeInOut(boolean isActionFadeInOut) {
		this.isActionFadeInOut = isActionFadeInOut;
	}

	public int getSpeedMTD() {
		return speedMTD;
	}

	public void setSpeedMTD(int speedMTD) {
		this.speedMTD = speedMTD;
	}

	public int getSpeedMTDMinimum() {
		return speedMTDMinimum;
	}

	public void setSpeedMTDMinimum(int speedMTDMinimum) {
		this.speedMTDMinimum = speedMTDMinimum;
	}

	public boolean isFound() {
		return isFound;
	}

	public void setFound(boolean isFound) {
		this.isFound = isFound;
	}

	public float getSpeedMoveSTSFreely() {
		return speedMoveSTSFreely;
	}

	public void setSpeedMoveSTSFreely(float speedMoveSTSFreely) {
		this.speedMoveSTSFreely = speedMoveSTSFreely;
	}
}
