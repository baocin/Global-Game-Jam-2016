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
//		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
//			hallActor.sprite.translateY(10.0f);
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//			hallActor.sprite.translateY(-10.0f);
//			
//		}

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		stage.act(delta);
		stage.draw();
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

//        batch.dispose();
//        texture.dispose();

	}

}
