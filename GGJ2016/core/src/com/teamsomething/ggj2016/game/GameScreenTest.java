package com.teamsomething.ggj2016.game;

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
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.ScreenManager;
import com.badlogic.gdx.Game;

public class GameScreenTest extends Game {
	static int WIDTH;
	static int HEIGHT;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Rectangle footprint;
	private Texture leftFootprintTexture;
	private Texture rightFootprintTexture;
	
	private int footSpacing = 80;
	
    @Override
    public void create() {
    	
    	
    	WIDTH = Gdx.graphics.getWidth();
    	HEIGHT = Gdx.graphics.getHeight();
    	
    	cam = new OrthographicCamera(WIDTH, HEIGHT);
//    	cam.setToOrtho(false,WIDTH, HEIGHT);
    	cam.translate(WIDTH/2, HEIGHT/2);
    	cam.update();
    	
    	batch = new SpriteBatch();
    	
//    	rightFootprintTexture = new Texture(Gdx.files.internal("shoePrintPerspectiveSmall.png"));
//    	leftFootprintTexture = new Texture(Gdx.files.internal("leftShoePrintPerspectiveSmall.png"));
//    	ScreenManager.getInstance().initialize(this);
//        ScreenManager.getInstance().show(ScreenManager.Screens.GAME);
        
		Gdx.app.setLogLevel(Logger.DEBUG);
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
    	ScreenManager.getInstance().dispose();
    	
    }

    @Override
    public void render() {
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
