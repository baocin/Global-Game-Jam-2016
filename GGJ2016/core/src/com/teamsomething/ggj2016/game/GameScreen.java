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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.ScreenManager;
import com.teamsomething.ggj2016.game.gamelogic.Footstep;
import com.teamsomething.ggj2016.game.gamelogic.FootstepType;
import com.teamsomething.ggj2016.game.gamelogic.Level;

public class GameScreen extends Game implements Screen {
	public static int deathCounter = 0;
	private static final float FOOTSTEP_LINE = 100;
	private Stage stage;
	static int WIDTH;
	static int HEIGHT;
	private SpriteBatch batch;
	private Texture rightFootprintTexture;
	private Texture leftWall;
	private Texture rightWall;
	private Texture perspectiveTexture;
	private double HIT_THRESHOLD = 0.3;
	private double LIT_THRESHOLD = 0.3;
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
	private TextureAtlas pentagramAtlas;
	private Animation stairsAnimation;
	private boolean endingLevel = false;
	private TextureAtlas stairsAtlas;

	public GameScreen() {
		leftWallAnimation = new Animation(1 / 15f, leftWallTextureAtlas.getRegions());
		rightWallAnimation = new Animation(1 / 15f, rightWallTextureAtlas.getRegions());
		pentagramAtlas = new TextureAtlas(Gdx.files.internal("data/pentagram.atlas"));
		stairsAtlas = new TextureAtlas(Gdx.files.internal("data/stairs.atlas"));
		stairsAnimation = new Animation(1, stairsAtlas.getRegions());

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
		startLevel("theme1.txt");
	}

	private void startLevel(String filename) {
		level = Level.loadLevel(filename);
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
				if (!endingLevel) {
					if (nextMusicName != null) {
						Music nextMusic = Gdx.audio.newMusic(Gdx.files.internal(nextMusicName));
						nextMusic.play();
						nextMusic.setOnCompletionListener(this);
						thisGameScreen.music = nextMusic;
					} else {
						// End of level!
						// Play end of level sound
						// After it ends, load level 2
						Music endSound = Gdx.audio.newMusic(Gdx.files.internal("endOfLevel.ogg"));
						endSound.play();
						// final OnCompletionListener thisCompletionListener =
						// this;
						// this;
						endSound.setOnCompletionListener(new OnCompletionListener() {

							@Override
							public void onCompletion(Music music) {
								music.dispose();
								startLevel("theme2.txt");
							}
						});
					}
				}
			}
		});
	}

	double totalGameTimer = 0;
	// boolean levelStarted = false;

	@Override
	public void render(float delta) {
		super.render();

		totalGameTimer += delta;
		// if ((totalGameTimer >= 5) && (levelStarted == false)) {
		// startLevel("theme1.txt");
		// levelStarted = true;
		// return;
		// }

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
		batch.draw(smokeTexture, 0, 0, WIDTH, HEIGHT);

		// System.out.println(level.getSkipped());
		int damage = (level.getSkipped() + level.getMisses()) / 4;

		System.out.println(damage);
		if (damage >= 6) {
			deathCounter++;
			ScreenManager.getInstance().show(ScreenManager.Screens.TITLE);
			endingLevel = true;
			music.stop();
			music.dispose();
		} else {
			batch.draw(pentagramAtlas.findRegion("000" + Integer.toString((damage % 7) + 1)), WIDTH / 2 - (72 / 2),
					HEIGHT - 72, 72, 72);

		}
		// batch.draw(stairTexture, 0, (float) -(level.getCurrPos() * 10),
		// WIDTH, HEIGHT);
		batch.draw(stairsAnimation.getKeyFrame(level.getFootstepsBetween(-level.getCurrPos(), 0).size(), true),
				(float) 0, 0, WIDTH, HEIGHT / 2);
		batch.draw(leftWallAnimation.getKeyFrame(elapsedTime, true), 0, 0, 225, HEIGHT);
		batch.draw(rightWallAnimation.getKeyFrame(elapsedTime, true), WIDTH - 225, 0, 225, HEIGHT);
		batch.end();

		// Advance in level
		// level.addCurrPos(delta);
		// TODO:
		level.syncWithMusic(music);

		// // Announce next footstep, if one has been passed
		// if (level.getFootsteps().get(nextFootstep).getTime() <
		// level.getCurrPos()) {
		// // "Next" footstep is in the past
		// nextFootstep++;
		//
		// // Announce
		// System.out.println("Next footstep is now: " +
		// level.getFootsteps().get(nextFootstep).toString());
		// }

		// TODO: Look for input
		boolean leftPressed = Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT);
		boolean rightPressed = Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_RIGHT);
		if (leftPressed || rightPressed) {
			// TODO: If pressed BEFORE a footstep, miss the next footstep

			// If pressed DURING a footstep, mark footstep as hit if it is left.
			// Otherwise miss.
			for (Footstep f : level.getActiveFootsteps(HIT_THRESHOLD)) {
				if (f != null) {
					Footstep active = f;
					if ((!active.isDidHit()) && (!active.isDidMiss())) {
						if (((active.getType() == FootstepType.LEFT) && leftPressed)
								|| ((active.getType() == FootstepType.RIGHT) && rightPressed)) {
							// HIT!
							active.setDidHit(true);
							// System.out.println("HIT FOOTSTEP: " +
							// active.toString());
						} else {
							// Miss.
							active.setDidMiss(true);
							// System.out.println("MISSED FOOTSTEP: " +
							// active.toString());
						}
					}
				}
			}
		}

		// Render visible footsteps
		batch.begin();
		{
			double startTime = -2;
			double farthestTime = 5.0f;
			for (Footstep f : level.getFootstepsBetween(startTime, farthestTime)) {
				float distanceOnRoad = (float) ((f.getTime() - level.getCurrPos()) / (farthestTime - startTime));
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
				if (-distanceTo > LIT_THRESHOLD && (!(f.isDidHit() || f.isDidMiss()))) {
					sprite.setRotation((int) (level.getCurrPos() * 2000));
					sprite.setColor(Color.FIREBRICK);
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
				float x = WIDTH / 2 + (leftFactor * (WIDTH / 11) * (1 - distanceOnRoad));
				float y = (distanceOnRoad * (HEIGHT / 2 + 50 - FOOTSTEP_LINE)) + FOOTSTEP_LINE;

				// Celebrate hits!
				if (f.isDidHit()) {
					y += -distanceTo * 100;
				}

				sprite.setCenter(x, y);
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
