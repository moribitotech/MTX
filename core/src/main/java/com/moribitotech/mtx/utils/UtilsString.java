package com.moribitotech.mtx.utils;

public class UtilsString {

	/**
	 * Remove last chracter of a string
	 * */
	public static String removeLastChar(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		return s.substring(0, s.length() - 1);
	}
}
