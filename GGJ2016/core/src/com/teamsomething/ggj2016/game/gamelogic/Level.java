package com.teamsomething.ggj2016.game.gamelogic;

import java.io.BufferedReader;
import java.io.IOException;
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
	 * 
	 * @param themeFilename
	 * @return
	 */
	public static Level loadLevel(String themeFilename) {
		// Load plaintext theme file
		BufferedReader themeIn = Gdx.files.getFileHandle(themeFilename, FileType.Internal).reader(2048);

		LinkedList<LinkedList<Integer>> segmentOrder = new LinkedList<LinkedList<Integer>>();
		LinkedList<String> segmentMusicFilenames = new LinkedList<String>();
		LinkedList<LinkedList<String>> segmentFootsteps = new LinkedList<LinkedList<String>>();

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
				if (sectionTitle.equalsIgnoreCase("Segment Order:")) {
					// Segment order section
					do {
						// Keep reading lines while they match (digits)......
						lineIn = themeIn.readLine();
						if (lineIn == null) {
							break;
						}
						if (lineIn.matches("\\d+\\.*")) {
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

					// TODO: Load music filename
					String musicFilename = lineIn = themeIn.readLine();
					segmentMusicFilenames.add(musicFilename);

					// TODO: Load footsteps
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
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		System.out.println(segmentOrder);
		System.out.println(segmentMusicFilenames);
		System.out.println(segmentFootsteps);

		// TODO: Choose a set of segments randomly according to the segment
		// order
		
		// TODO: Create level object

		return null;
	}

}
