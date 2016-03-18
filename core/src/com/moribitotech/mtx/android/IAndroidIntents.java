package com.moribitotech.mtx.android;

public interface IAndroidIntents {
	/**
	 * Start a uri intent. For example opening Google Play market link
	 * */
	public void startUriIntent(String uri);

	/**
	 * Start share intent
	 * */
	public void startShareIntent(String header, String subject, String body);
}
