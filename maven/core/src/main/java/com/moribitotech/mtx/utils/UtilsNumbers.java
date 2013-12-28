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

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class UtilsNumbers {
	//
	protected final static String logTag = "MtxUtilsNumbersLog";
	public static boolean logActive = true;

	private static Pattern doublePattern = Pattern.compile("-?\\d+(\\.\\d*)?");

	/**
	 * Check if a string is parseable to integer/double
	 * */
	public static boolean isIntDob(String string) {
		return doublePattern.matcher(string).matches();
	}

	/**
	 * Shuffle an array for integers
	 * <p>
	 * EXAMPLE: <br>
	 * Array [1,2,3,4,5,6] getting shuffled <br>
	 * After shuffle [2,1,6,3,5,4] or any other combination
	 * 
	 * @param a
	 *            array to shuffle
	 * */
	public static void shuffleArray(int[] a) {
		int n = a.length;
		Random random = new Random();
		random.nextInt();
		for (int i = 0; i < n; i++) {
			int change = i + random.nextInt(n - i);
			swap(a, i, change);
		}
	}

	/**
	 * Shuffle helper
	 * */
	public static void swap(int[] a, int i, int change) {
		int helper = a[i];
		a[i] = a[change];
		a[change] = helper;
	}

	/**
	 * Calculate average number of float list
	 * */
	public static float calculateAverage(ArrayList<Float> numbersList) {
		Float sum = 0f;
		for (Float number : numbersList) {
			sum += number;
		}
		return sum.floatValue() / numbersList.size();
	}

	/**
	 * Find the closets integer of a given integer among integer array list
	 * 
	 * @return the value found else -1
	 * */
	public static int findClosestNumber(ArrayList<Integer> list, int value) {
		int closestMatch = -1;
		boolean isFirstNumberTaken = false;
		for (int i = 0; i < list.size(); i++) {
			if (!isFirstNumberTaken) {
				closestMatch = list.get(i);
				isFirstNumberTaken = true;
			} else {
				int current = Math.abs(value - closestMatch);
				int next = Math.abs(value - list.get(i));
				if (current > next) {
					closestMatch = list.get(i);
				}
			}
		}
		return closestMatch;
	}
}
