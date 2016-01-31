package com.teamsomething.ggj2016.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamsomething.ggj2016.game.gamelogic.Footstep;
import com.teamsomething.ggj2016.game.gamelogic.FootstepType;
import com.teamsomething.ggj2016.game.gamelogic.Level;

public class GameScreen extends Game implements Screen {
	private static final float FOOTSTEP_LINE = 25;
	private Stage stage;
	static int WIDTH;
	static int HEIGHT;
	private SpriteBatch batch;
	private Texture rightFootprintTexture;
	private Texture leftWall;
	private Texture rightWall;
	private Texture perspectiveTexture;
	private double HIT_THRESHOLD = 0.6;
	private double LIT_THRESHOLD = 0.2;
	private int footSpacing = 65;
	private TextureAtlas leftWallTextureAtlas = new TextureAtlas(Gdx.files.internal("data/leftWall.atlas"));
	private TextureAtlas rightWallTextureAtlas = new TextureAtlas(Gdx.files.internal("data/rightWall.atlas"));
	private Animation leftWallAnimation;
	private Animation rightWallAnimation;
	private float elapsedTime = 0;
	private Level level;
	private int nextFootstep;
	private Music music;
	private Texture stairTexture;
	private Texture frontTexture;
	private Texture smokeTexture;
	
	
	public GameScreen() {
		leftWallAnimation = new Animation(1 / 15f, leftWallTextureAtlas.getRegions());
		rightWallAnimation = new Animation(1 / 15f, rightWallTextureAtlas.getRegions());

		// batch = new SpriteBatch();
		stage = new Stage();
		// TODO Auto-generated constructor stub
		stage = new Stage(new StretchViewport(WIDTH, HEIGHT));
		Gdx.input.setInputProcessor(stage);

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		batch = new SpriteBatch();

		// rightWall = new Texture(Gdx.files.internal("rightWall1.png"));
		// leftWall = new Texture(Gdx.files.internal("leftWall1.png"));
		perspectiveTexture = new Texture(Gdx.files.internal("perspective2.png"));
		rightFootprintTexture = new Texture(Gdx.files.internal("footprintWhite.png"));
		stairTexture = new Texture(Gdx.files.internal("stairs.png"));
		frontTexture = new Texture(Gdx.files.internal("whiteGradient.png"));
		smokeTexture = new Texture(Gdx.files.internal("smoke.jpg"));

	}
	

	@Override
	public void show() {
		level = Level.loadLevel("theme1.txt");
		nextFootstep = 0;
		music = Gdx.audio.newMusic(Gdx.files.internal(level.getMusicFiles().poll()));
		music.play();
		final GameScreen thisGameScreen = this;
		music.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(Music music) {
				music.dispose();
				level.incrementCurrentSection();

				String nextMusicName = level.getMusicFiles().poll();
				if (nextMusicName != null) {
					Music nextMusic = Gdx.audio.newMusic(Gdx.files.internal(nextMusicName));
					nextMusic.play();
					nextMusic.setOnCompletionListener(this);
					thisGameScreen.music = nextMusic;
				}
			}
		});
	}

	@Override
	public void render(float delta) {
		super.render();

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		{
			// batch.draw(rightFootprintTexture, WIDTH / 2 + footSpacing - 50,
			// 0);
			// batch.draw(leftFootprintTexture, WIDTH / 2 - footSpacing - 50,
			// 0);
			// batch.draw(rightWall, 0, 0, WIDTH, HEIGHT);
			// batch.draw(leftWall, 0, 0, WIDTH, HEIGHT);
			// batch.draw(perspectiveTexture, 0, 0, WIDTH, HEIGHT);

		}
		batch.end();

		batch.begin();
		// sprite.draw(batch);
		elapsedTime += Gdx.graphics.getDeltaTime();
		// batch.draw(), x, y, originX, originY, width, height, scaleX, scaleY,
		// rotation);
		batch.draw(smokeTexture, 0,0,WIDTH, HEIGHT);
		batch.draw(stairTexture, 0,0,WIDTH, HEIGHT);
		batch.draw(leftWallAnimation.getKeyFrame(elapsedTime, true), 0, 0, 225, HEIGHT);
		batch.draw(rightWallAnimation.getKeyFrame(elapsedTime, true), WIDTH - 225, 0, 225, HEIGHT);
		batch.end();

		// Advance in level
		// level.addCurrPos(delta);
		// TODO:
		level.syncWithMusic(music);

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

		// Render visible footsteps
		batch.begin();
		{
			double farthestTime = 12.0f;
			for (Footstep f : level.getFootstepsBetween(-2, farthestTime)) {
				float distanceOnRoad = (float) ((f.getTime() - level.getCurrPos()) / farthestTime);
				distanceOnRoad -= 1;
				distanceOnRoad = -(-Math.abs(distanceOnRoad * distanceOnRoad * distanceOnRoad) - 1);
				distanceOnRoad = 2 - (distanceOnRoad);
				Sprite sprite = new Sprite(rightFootprintTexture);
				// Flip for left foot
				sprite.flip(f.getType() == FootstepType.LEFT, false);
				// Add alpha
				if (distanceOnRoad > 0) {
					sprite.setAlpha((1f - distanceOnRoad) * 0.9f + 0.1f);
				}
				// Add scaling
				sprite.setScale((1f - distanceOnRoad) * 0.6f);
				// Light footsteps that are "active"
				double distanceTo = f.getTime() - level.getCurrPos();
				if (Math.abs(distanceTo) < LIT_THRESHOLD) {
					sprite.setColor(Color.BLUE);
				}

				// Mark hits or misses
				if (f.isDidHit()) {
					sprite.setColor(Color.GREEN);
				} else if (f.isDidMiss()) {
					sprite.setColor(Color.RED);
				}

				float leftFactor = 1;
				if (f.getType() == FootstepType.LEFT) {
					leftFactor = -1;
				}
				sprite.setCenter(WIDTH / 2 + (leftFactor * (WIDTH / 8) * (1 - distanceOnRoad)),
						distanceOnRoad * (HEIGHT / 2 + 100) + FOOTSTEP_LINE);

				sprite.draw(batch);
			}
		}
		batch.end();
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
		batch.dispose();
		leftWallTextureAtlas.dispose();
		rightWallTextureAtlas.dispose();

		// batch.dispose();
		// texture.dispose();

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

}
