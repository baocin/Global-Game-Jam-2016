package com.teamsomething.ggj2016.game.gamelogic;

import java.io.BufferedReader;
import java.util.LinkedList;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;

public class Level {
	/**
	 * Player position within a level
	 */
	private double currPos;
	
	/**
	 * List of segments comprising level
	 */
	private LinkedList<LevelSegment> segments = new LinkedList<LevelSegment>();
	
	private enum LoadState {
		NONE, ORDER, SEGMENT_FILENAME, SEGMENT_MOVES
	}
	
	/**
	 * Reads a theme file, and generates a level from it by combining segments.
	 * @param themeFilename
	 * @return
	 */
	public static Level loadLevel (String themeFilename) {
		// Load plaintext theme file
		BufferedReader themeIn = Gdx.files.getFileHandle(themeFilename, FileType.Internal).reader(2048);
		
		// Load segments of plaintext theme file
		LoadState state = 
		while (true) {
			String lineIn = themeIn.readLine();
			if (lineIn == null) {
				break;
			}
			
			
		}
		
		return null;
	}
	
}
