package com.teamsomething.ggj2016.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.ScreenManager;

public class TitleScreen implements Screen {
	private SpriteBatch batch;
	private BitmapFont font;
	private Stage stage;
	private Texture evilTexture;
	private Sound evilLaughSound;

	public TitleScreen() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);
		evilTexture = new Texture(Gdx.files.internal("evil.png"));

		stage = new Stage();
	}

	@Override
	public void show() {
		if (GameScreen.deathCounter > 0) {
			evilLaughSound = Gdx.audio.newSound(Gdx.files.internal("evilLaugh.ogg"));
			// evilLaughSound.setLooping(666, true);
			evilLaughSound.play();
		}
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(evilTexture, (float) (200 + Math.floor(GameScreen.deathCounter * Math.random() * 20)),
				(float) (Math.floor(GameScreen.deathCounter * Math.random())), 500, Gdx.graphics.getHeight() / 2);
		font.draw(batch, "Dance Outta Hell", 100, Gdx.graphics.getHeight() / 2 + 120);
        font.draw(batch, "William Karnavas, Kevin Heu, Michael Pedersen", 100, Gdx.graphics.getHeight()/2+75);

		batch.end();

		boolean mouseLeftDown = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		boolean leftPressed = Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT);
		boolean rightPressed = Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_RIGHT);

		if (mouseLeftDown || leftPressed || rightPressed) {
			ScreenManager.getInstance().show(ScreenManager.Screens.GAME);
		} else {
			// ScreenManager.getInstance().show(ScreenManager.Screens.);
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
		batch.dispose();
		font.dispose();
		evilLaughSound.dispose();
	}

}
