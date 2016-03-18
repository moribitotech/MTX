package com.moribitotech.mtx.utils;

import java.util.Random;

public class UtilsRandomizer {
	/**
	 * Get random number in a range (inclusive)
	 * */
	public static int getRandomInclusive(Random rnd, int min, int max) {
		return (rnd.nextInt(max + 1 - min) + min);
	}

	/**
	 * Get random number in a range (exclusive)
	 * */
	public static int getRandomExclusive(Random rnd, int min, int max) {
		int rndNumber = rnd.nextInt(max - min);
		if (rndNumber != 0)
			return rndNumber + min;
		else
			return rndNumber + min + 1;
	}
}
