package com.teamsomething.ggj2016.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.ScreenManager;

public class GameScreen implements Screen {
    private Stage stage;
	private Texture texture;
	private SpriteBatch batch;
	Sprite sprite;

	public GameScreen() {
		batch = new SpriteBatch();
		
		// TODO Auto-generated constructor stub
		stage = new Stage(new StretchViewport(CoreGame.WIDTH, CoreGame.HEIGHT));
    	Gdx.input.setInputProcessor(stage);
    	
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		System.out.println(delta);
		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
			batch.draw(rightFootprintTexture, WIDTH / 2 + footSpacing - 50, 0);
			batch.draw(leftFootprintTexture, WIDTH / 2 - footSpacing - 50, 0);
			batch.draw(rightWall, 0, 0, WIDTH, HEIGHT);
			batch.draw(leftWall, 0, 0, WIDTH, HEIGHT);
			batch.draw(perspectiveTexture2, 0, 0, WIDTH, HEIGHT);
		batch.end();
		
		batch.begin();
		stage.act(delta);
		stage.draw();
		batch.end();
	
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			hallActor.sprite.translateY(10.0f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			hallActor.sprite.translateY(-10.0f);
			
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
		ScreenManager.getInstance().dispose();
//        batch.dispose();
//        texture.dispose();

	}

}
