package com.teamsomething.ggj2016.game.gamelogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

public class Level {
	/**
	 * Player position within a level
	 */
	private double currPos;

	/**
	 * List of footsteps in level
	 */
	private LinkedList<Footstep> footsteps = new LinkedList<Footstep>();

	/**
	 * List of music files in a level
	 * 
	 *
	 */
	private LinkedList<String> musicFiles = new LinkedList<String>();

	private enum LoadState {
		NONE, ORDER, SEGMENT_FILENAME, SEGMENT_MOVES
	}

	private static final String LOAD_LEVEL_TAG = "Level.loadLevel";

	/**
	 * Reads a theme file, and generates a level from it by combining segments.
	 * 
	 * @param themeFilename
	 * @return
	 */
	public static Level loadLevel(String themeFilename) {

		Gdx.app.debug(LOAD_LEVEL_TAG, "Loading theme: " + themeFilename);

		// Load plaintext theme file
		BufferedReader themeIn = Gdx.files.getFileHandle(themeFilename, FileType.Internal).reader(2048);

		LinkedList<LinkedList<Integer>> segmentOrder = new LinkedList<LinkedList<Integer>>();
		LinkedList<String> segmentMusicFilenames = new LinkedList<String>();
		LinkedList<LinkedList<String>> segmentFootsteps = new LinkedList<LinkedList<String>>();
		LinkedList<Double> segmentMusicDurations = new LinkedList<Double>();

		try {
			// Load segments of plaintext theme file
			LoadState state = LoadState.NONE;
			String lineIn = themeIn.readLine();
			if (lineIn == null) {
				return null;
			}
			while (true) {
				if (lineIn == null) {
					break;
				}
				// Load a section of the theme file
				String sectionTitle = lineIn.trim();
				Gdx.app.debug(LOAD_LEVEL_TAG, "Loading section with title: " + sectionTitle);
				if (sectionTitle.equalsIgnoreCase("Segment Order:")) {
					// Segment order section
					Gdx.app.debug(LOAD_LEVEL_TAG, "Loading segment order section.");
					do {
						// Keep reading lines while they match (digits)......
						lineIn = themeIn.readLine();
						if (lineIn == null) {
							break;
						}

						if (Character.isDigit(lineIn.trim().charAt(0))) {
							Gdx.app.debug(LOAD_LEVEL_TAG, "Loading segment order line: " + lineIn);
							String[] parts = lineIn.split(",");
							LinkedList<Integer> ll = new LinkedList<Integer>();
							for (String s : parts) {
								ll.add(Integer.parseInt(s.trim()));
							}
							segmentOrder.add(ll);
						} else {
							// break, and read next section
							break;
						}
					} while (true);
					// } else if
					// (sectionTitle.toLowerCase().matches("Segment\\s*\\d+.*"))
					// {
				} else if (sectionTitle.equalsIgnoreCase("Segment:")) {
					// Segment section
					Gdx.app.debug(LOAD_LEVEL_TAG, "Loading segment section.");
					// Load music duration
					double musicDuration = Double.parseDouble(themeIn.readLine());
					segmentMusicDurations.add(musicDuration);
					// Load music filename
					String musicFilename = themeIn.readLine();
					segmentMusicFilenames.add(musicFilename);
					Gdx.app.debug(LOAD_LEVEL_TAG, "Segment file name: " + musicFilename);

					// Load footsteps
					LinkedList<String> footsteps = new LinkedList<String>();
					do {
						lineIn = themeIn.readLine();
						if (lineIn == null) {
							break;
						} else if (lineIn.toLowerCase().startsWith("segment")) {
							// End of footsteps data
							break;
						} else {
							footsteps.add(lineIn.trim());
						}
					} while (true);
					segmentFootsteps.add(footsteps);
					Gdx.app.debug(LOAD_LEVEL_TAG, "Loaded segment foosteps: " + segmentFootsteps.toString());
				} else {
					// INVALID SECTION TITLE
					Gdx.app.error(LOAD_LEVEL_TAG, "Invalid section title in Theme file: " + lineIn);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		System.out.println(segmentOrder);
		System.out.println(segmentMusicFilenames);
		System.out.println(segmentMusicDurations);
		System.out.println(segmentFootsteps);

		Gdx.app.debug(LOAD_LEVEL_TAG, "Generating level:");

		// Choose a set of segments randomly according to the segment order
		LinkedList<Integer> chosenSegments = new LinkedList<Integer>();
		for (LinkedList<Integer> options : segmentOrder) {
			chosenSegments.add(options.get(MathUtils.random(options.size() - 1)) - 1);
		}

		Gdx.app.debug(LOAD_LEVEL_TAG, "Selected segment order:" + chosenSegments.toString());

		Level level = new Level();
		// Place foosteps according to position in each segment
		double startTime = 0;
		for (Integer chosenSegmentIndex : chosenSegments) {
			// Calculate the time interval between each footstep
			double interval = segmentMusicDurations.get(chosenSegmentIndex)
					/ segmentFootsteps.get(chosenSegmentIndex).size();
			for (int i = 0; i < segmentFootsteps.get(chosenSegmentIndex).size(); i++) {
				String typeString = segmentFootsteps.get(chosenSegmentIndex).get(i);
				if (typeString.length() > 0) {
					FootstepType type = FootstepType.LEFT;
					if (typeString.toLowerCase().startsWith("r")) {
						type = FootstepType.RIGHT;
					}
					// Place new footstep on level's timeline
					level.footsteps.add(new Footstep(type, startTime + i * interval));
				}
			}

			startTime += segmentMusicDurations.get(chosenSegmentIndex);
		}

		Gdx.app.debug(LOAD_LEVEL_TAG, level.footsteps.toString());

		// Queue up music files according to segment order
		for (Integer segment : chosenSegments) {
			level.musicFiles.add(segmentMusicFilenames.get(segment - 1));
		}

		return level;
	}

}
