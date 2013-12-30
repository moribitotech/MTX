package com.moribitotech.mtx.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class BenchmarkManager {

	// Stage and font
	private Stage stage;
	private BitmapFont font;

	// Run time
	private long startTime = System.nanoTime();
	private long secondsTime = 0L;

	// Table and labels
	private Table table;
	private Label labelRunTime;
	private Label labelResolution;
	private Label labelFps;

	// Helpers
	private boolean isBenchmarkManagerActive = true;
	private boolean isFirstDataTaken = false;
	private int fpsBest = 0;
	private int fpsWorst = 0;

	// Label strings
	private static final String LABEL_RUNTIME = "Run Time: ";
	private static final String LABEL_RESOLUTION = "Resolution: ";
	private static final String LABEL_FPS = "Fps: ";
	private static final String LABEL_FPS_BEST = "Best: ";
	private static final String LABEL_FPS_WORST = "Worst: ";

	// Benchmark Table positioner
	public enum BenchmarkPosition {
		CENTER, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT;
	}

	public BenchmarkManager(Stage stage, BitmapFont font,
			BenchmarkPosition benchmarkPosition) {
		//
		this.stage = stage;
		this.font = font;
		//
		setUp(benchmarkPosition);
	}

	/**
	 * Set up benchmark table and the rest
	 * */
	private void setUp(BenchmarkPosition benchmarkPosition) {
		table = new Table();
		table.setFillParent(true);
		table.setTouchable(Touchable.disabled);
		stage.addActor(table);
		//
		setUpBencmarks();
		setUpPosition(benchmarkPosition);
	}

	/**
	 * Set up benchmark labels
	 * */
	private void setUpBencmarks() {
		LabelStyle style = new LabelStyle(font, null);
		//
		labelResolution = new Label(getResolution(), style);
		labelRunTime = new Label(getRunTime(), style);
		labelFps = new Label(getFPS(), style);
		//
		table.add().row().left();
		table.add(labelResolution).row().left();
		table.add(labelRunTime).row().left();
		table.add(labelFps).row().left();
		table.add().left();
	}

	/**
	 * Update benchmarking. Must be used in render (or act or draw etc...)
	 * */
	public void update() {
		if (isBenchmarkManagerActive) {

			if (System.nanoTime() - startTime >= 1000000000) {
				secondsTime++;
				startTime = System.nanoTime();
			}

			if (!isFirstDataTaken) {
				if (secondsTime > 4f) {
					fpsBest = Gdx.graphics.getFramesPerSecond();
					fpsWorst = Gdx.graphics.getFramesPerSecond();
					isFirstDataTaken = true;
				}
			} else {
				if (Gdx.graphics.getFramesPerSecond() > fpsBest)
					fpsBest = Gdx.graphics.getFramesPerSecond();
				if (Gdx.graphics.getFramesPerSecond() < fpsWorst)
					fpsWorst = Gdx.graphics.getFramesPerSecond();
			}

			//
			labelRunTime.setText(getRunTime());
			labelFps.setText(getFPS());
		}
	}

	/**
	 * Get run time since BenchmarkManager constructed
	 * */
	public String getRunTime() {
		int seconds = (int) (secondsTime % 60);
		int minutes = (int) ((secondsTime / 60) % 60);
		int hours = (int) ((secondsTime / 3600) % 24);
		String secondsStr = (seconds < 10 ? "0" : "") + seconds;
		String minutesStr = (minutes < 10 ? "0" : "") + minutes;
		String hoursStr = (hours < 10 ? "0" : "") + hours;
		return LABEL_RUNTIME + hoursStr + ":" + minutesStr + ":" + secondsStr;
	}

	/**
	 * Get resolution
	 * */
	public String getResolution() {
		return LABEL_RESOLUTION + Gdx.graphics.getWidth() + "x"
				+ Gdx.graphics.getHeight();
	}

	/**
	 * Get current FPS (frame per seconds) and Best/Worst FPS. (Best/Worst fps
	 * starts after 5 seconds to achieve best accuracy). LibGDX renders at 60
	 * FPS
	 * */
	public String getFPS() {
		return LABEL_FPS + Gdx.graphics.getFramesPerSecond()
				+ getFPSBestWorst();
	}

	/**
	 * Get Best/Worst FPS. (Best/Worst fps starts after 5 seconds to achieve
	 * best accuracy)
	 * */
	public String getFPSBestWorst() {
		return "   (" + LABEL_FPS_BEST + fpsBest + " - " + LABEL_FPS_WORST
				+ fpsWorst + ")";
	}

	public void setBenchmarkManagerActive(boolean isActive) {
		if (isActive) {
			isBenchmarkManagerActive = true;
			table.setVisible(true);
		} else {
			isBenchmarkManagerActive = false;
			table.setVisible(false);
		}
	}

	/**
	 * Set benchmark table position
	 * */
	public void setUpPosition(BenchmarkPosition benchmarkPosition) {
		switch (benchmarkPosition) {
		case CENTER:
			table.center();
			break;
		case TOP_LEFT:
			table.top().left();
			break;
		case TOP_RIGHT:
			table.top().right();
			break;
		case BOTTOM_LEFT:
			table.bottom().left();
			break;
		case BOTTOM_RIGHT:
			table.bottom().right();
			break;
		default:
			table.top().left();
			break;
		}
	}
}
