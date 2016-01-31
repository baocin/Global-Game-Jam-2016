package com.teamsomething.ggj2016.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.ScreenManager;
import com.teamsomething.ggj2016.game.gamelogic.Level;

public class CoreGame extends Game {
	static int WIDTH;
	static int HEIGHT;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Rectangle footprint;
	private Texture leftFootprintTexture;
	private Texture rightFootprintTexture;
	private Texture leftWall;
	private Texture rightWall;
	private ArrayList<Level> levels;
	private int footSpacing = 65;
	private Texture perspectiveTexture2;

	@Override
	public void create() {
		levels = new ArrayList<Level>();
		Level testLevel = new Level();
		try {
			Level.loadLevel("theme1.txt");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Could not load music for the given theme file");
			Gdx.app.debug("CoreGame", "Could not load music for the given theme file");
		}
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		// cam = new OrthographicCamera(WIDTH, HEIGHT);
		// cam.setToOrtho(false,WIDTH, HEIGHT);
		// cam.translate(WIDTH/2, HEIGHT/2);
		// cam.update();

		batch = new SpriteBatch();

		rightFootprintTexture = new Texture(Gdx.files.internal("rightShoePrintPerspectiveSmall.png"));
		leftFootprintTexture = new Texture(Gdx.files.internal("leftShoePrintPerspectiveSmall.png"));
		rightWall = new Texture(Gdx.files.internal("rightWall.png"));
		leftWall = new Texture(Gdx.files.internal("leftWall.png"));
		perspectiveTexture2 = new Texture(Gdx.files.internal("perspective2.png"));

		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().show(ScreenManager.Screens.GAME);
	}

	@Override
	public void dispose() {
		ScreenManager.getInstance().dispose();

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// cam.update();

		// batch.setProjectionMatrix(cam.combined);

		batch.begin();
		batch.draw(rightFootprintTexture, WIDTH / 2 + footSpacing - 50, 0);
		batch.draw(leftFootprintTexture, WIDTH / 2 - footSpacing - 50, 0);
		batch.draw(rightWall, 0, 0, WIDTH, HEIGHT);
		batch.draw(leftWall, 0, 0, WIDTH, HEIGHT);
		batch.draw(perspectiveTexture2, 0, 0, WIDTH, HEIGHT);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
