package com.mygdx.game;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CoreGame extends ApplicationAdapter {
	private static final int PREFFERED_WIDTH = 1240;
	private static final int PREFFERED_HEIGHT = 800;
	private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private CreditScreen creditScreen;
    private GameScreen gameScreen;
    private PauseScreen pauseScreen;
    private TitleScreen titleScreen;
    
    @Override
    public void create() {
    	creditScreen = new CreditScreen();
    	gameScreen= new GameScreen();
    	pauseScreen = new PauseScreen();
    	titleScreen = new TitleScreen();
    	
    	
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("perspective.png"));
        sprite = new Sprite(texture);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    @Override
    public void render() {
    	if(Gdx.input.isKeyPressed(Input.Keys.UP)){
    		sprite.translateY(10.0f);
        }
    	if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
    		sprite.translateY(-10.0f);
        }
    	
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    	
//    	stage.setViewport(PREFFERED_WIDTH, PREFFERED_HEIGHT, true);
//        stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
//        if (table != null){
//            table.setSize(PREFFERED_WIDTH, PREFFERED_HEIGHT);
//        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
