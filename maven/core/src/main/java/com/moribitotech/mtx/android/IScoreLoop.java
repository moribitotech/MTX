package com.moribitotech.mtx.android;

public interface IScoreLoop {
	public void bootstrap();

	public void showScoreloop();

	public void submitScore(int mode, int score);

	public void refreshScores();
}
