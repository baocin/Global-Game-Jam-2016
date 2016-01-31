package com.teamsomething.ggj2016.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamsomething.ggj2016.game.gamelogic.Footstep;
import com.teamsomething.ggj2016.game.gamelogic.FootstepType;
import com.teamsomething.ggj2016.game.gamelogic.Level;

public class GameScreen implements Screen {
	private Stage stage = new Stage();

	private Level level;
	private int nextFootstep;

	public GameScreen() {
		// batch = new SpriteBatch();

		// TODO Auto-generated constructor stub
		stage = new Stage(new StretchViewport(CoreGame.WIDTH, CoreGame.HEIGHT));
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void show() {
		level = Level.loadLevel("theme1.txt");
		nextFootstep = 0;
		Music music = Gdx.audio.newMusic(Gdx.files.internal(level.getMusicFiles().poll()));
		music.play();
		music.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(Music music) {
				music.dispose();

				String nextMusicName = level.getMusicFiles().poll();
				if (nextMusicName != null) {
					Music nextMusic = Gdx.audio.newMusic(Gdx.files.internal(nextMusicName));
					nextMusic.play();
					nextMusic.setOnCompletionListener(this);
				}
			}
		});
	}

	private double HIT_THRESHOLD = 0.2;

	@Override
	public void render(float delta) {
		// if(Gdx.input.isKeyPressed(Input.Keys.UP)){
		// hallActor.sprite.translateY(10.0f);
		// }
		// if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
		// hallActor.sprite.translateY(-10.0f);
		//
		// }

		// Advance in level
		level.addCurrPos(delta);

		// Announce next footstep, if one has been passed
		if (level.getFootsteps().get(nextFootstep).getTime() < level.getCurrPos()) {
			// "Next" footstep is in the past
			nextFootstep++;

			// Announce
			System.out.println("Next footstep is now: " + level.getFootsteps().get(nextFootstep).toString());
		}

		// TODO: Look for input
		boolean leftPressed = Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT);
		boolean rightPressed = Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_RIGHT);
		if (leftPressed || rightPressed) {
			// TODO: If pressed BEFORE a footstep, miss the next footstep

			// If pressed DURING a footstep, mark footstep as hit if it is left.
			// Otherwise miss.
			if (level.getActiveFootstep(HIT_THRESHOLD) != null) {
				Footstep active = level.getActiveFootstep(HIT_THRESHOLD);
				if ((!active.isDidHit()) && (!active.isDidMiss())) {
					if (((active.getType() == FootstepType.LEFT) && leftPressed)
							|| ((active.getType() == FootstepType.RIGHT) && rightPressed)) {
						// HIT!
						active.setDidHit(true);
						System.out.println("HIT FOOTSTEP: " + active.toString());
					} else {
						// Miss.
						active.setDidMiss(true);
						System.out.println("MISSED FOOTSTEP: " + active.toString());
					}
				}
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {

		// batch.dispose();
		// texture.dispose();

	}

}
