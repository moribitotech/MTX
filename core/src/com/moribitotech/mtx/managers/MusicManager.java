package com.moribitotech.mtx.managers;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.audio.Music;

public class MusicManager {
	//
	private Random rnd;
	private ArrayList<Music> musicShuffleList;
	private Music currentMusic;
	private AudioManager audioManager;
	private boolean isShuffling = false;
	private float shuffleVolume = 0.5f;

	public MusicManager() {
		audioManager = new AudioManager();
		rnd = new Random();
		musicShuffleList = new ArrayList<Music>();
		currentMusic = null;
	}

	public void addMusicToShuffleList(Music music) {
		musicShuffleList.add(music);
	}

	public void playShuffle(float volume) {
		clearCurrentMusic();
		clearLoops();
		isShuffling = true;
		startShuffling(volume);
	}

	public void playMusic(Music music, boolean isLooping, float volume) {
		clearCurrentMusic();
		isShuffling = false;
		currentMusic = music;
		shuffleVolume = volume;
		audioManager.playMusic(currentMusic, isLooping, shuffleVolume);
	}

	public void stopMusic() {
		if (currentMusic != null) {
			currentMusic.stop();
		}
	}

	public void pauseMusic() {
		if (currentMusic != null) {
			currentMusic.pause();
		}
	}

	private void clearLoops() {
		for (int i = 0; i < musicShuffleList.size(); i++) {
			Music m = musicShuffleList.get(i);
			m.setLooping(false);
		}
	}

	private void clearCurrentMusic() {
		if (currentMusic != null) {
			currentMusic.stop();
			currentMusic = null;
		}
	}

	private void startShuffling(float volume) {
		if (musicShuffleList.size() > 0) {
			int rndNumber = rnd.nextInt(musicShuffleList.size());
			currentMusic = musicShuffleList.get(rndNumber);
			audioManager.playMusic(currentMusic, false, volume);
		}
	}

	/**
	 * This method must be checked in render() or similar all the time, for
	 * shuffle list
	 * */
	public void checkShuffleMusicFinished() {
		if (isShuffling) {
			if (currentMusic != null) {
				if (!currentMusic.isPlaying()) {
					startShuffling(shuffleVolume);
				}
			}
		}
	}

	public ArrayList<Music> getMusicShuffleList() {
		return musicShuffleList;
	}

	public AudioManager getAudioManager() {
		return audioManager;
	}

	public Music getCurrentMusic() {
		return currentMusic;
	}
}
